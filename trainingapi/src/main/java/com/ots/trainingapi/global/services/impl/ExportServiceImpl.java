package com.ots.trainingapi.global.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.ots.trainingapi.global.dto.ExportDataDto;
import com.ots.trainingapi.global.exceptions.ValidationException;
import com.ots.trainingapi.global.services.ExportService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {
    
    private static final Logger logger = LogManager.getLogger(ExportService.class);
    
    private StopWatch stopWatch = new StopWatch();
    
    private static final String COL_NAME = "field";
    private static final String COL_TRANSLATED_NAME = "header";
    private static final String COL_TYPE = "type";
    
    @Override
    public ExportDataDto initializeExportParams(HttpServletRequest request) {
        
        ExportDataDto exportDataDto = new ExportDataDto();
        
        exportDataDto.setTitle(request.getParameter("title"));
        exportDataDto.setModel(request.getParameter("model"));
        exportDataDto.setSortColumn(request.getParameter("sidx"));
        exportDataDto.setSortOrder(request.getParameter("sord"));
        
        return exportDataDto;
    }
    
    @Override
    public Pageable getExportPageable(ExportDataDto exportParams) {
        
        // Μέγιστο μέγεθος σελίδας αποτελεσμάτων
        int maxPageSize = 50000;
        
        // Στοιχεία ταξινόμησης
        Sort sort = null;
        
        String sortColumn = exportParams.getSortColumn();
        String sortOrder = exportParams.getSortOrder();
        
        if (!StringUtils.isEmpty(sortColumn) && !StringUtils.isEmpty(sortOrder)) {
            sort = Sort.by(new Sort.Order(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn));
        }
        
        if (sort == null) {
            sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        }
        
        return PageRequest.of(0, maxPageSize, sort);
    }
    
    @Override
    public void listToExcelFile(String title, List data, String model, OutputStream fos) {
        
        GsonBuilder gsb = new GsonBuilder();
        Gson gson = gsb.disableHtmlEscaping().create();
        
        // Περιγραφή στηλών του αρχείου
        List<LinkedTreeMap<String, Object>> columns = gson.fromJson(model, List.class);
        
        // Δεδομένα (γραμμές) του αρχείου
        List<Object> rows = data;
        
        if (rows == null || rows.size() == 0) {
            throw new ValidationException("Export data must not be empty");
        }
        
        //logger.info("Exporting data {} as Excel file requested by user {}. Data size: {}", title, getUsername(), rows.size());
        
        if (!stopWatch.isRunning()) stopWatch.start("Excel_File_Creation");
        
        // Excel workbook
        XSSFWorkbook workbook;
        
        try {
            
            // Δημιουργία workbook
            workbook = new XSSFWorkbook();
            
            // Δημιουργία νέου φύλλου εργασίας
            XSSFSheet sheet = workbook.createSheet(title);
            
            // Καθορισμός γενικών παραμέτρων εμφάνισης και μορφοποίησης
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 10);
            font.setBold(true);
            style.setFont(font);
            
            XSSFCellStyle style2 = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setFontHeightInPoints((short) 16);
            style2.cloneStyleFrom(style);
            style2.setFont(font2);
            style2.setAlignment(HorizontalAlignment.CENTER);
            
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
            
            // Προεπεξεργασία στηλών
            processColumns(columns, rows.get(0));
            
            // Δημιουργία γραμμή επικεφαλίδας
            XSSFRow row0 = sheet.createRow(0);
            XSSFCell spanCell = row0.createCell(0);
            spanCell.setCellValue(title);
            spanCell.setCellStyle(style2);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.size() - 1));
            
            // Δημιουργία γραμμής για τις στήλες του πίνακα
            XSSFRow row1 = sheet.createRow(1);
            
            for (int c = 0; c < columns.size(); c++) {
                String columnName = columns.get(c).get(COL_TRANSLATED_NAME).toString();
                XSSFCell titleCol = row1.createCell(c);
                titleCol.setCellValue(columnName);
                titleCol.setCellStyle(style);
            }
            
            // Δημιουργία γραμμών για τα δεδομένα του πίνακα
            for (int r = 0; r < rows.size(); r++) {
                
                // Τρέχον αντικείμενο δεδομένων
                BeanWrapperImpl rowData = new BeanWrapperImpl(rows.get(r));
                
                XSSFRow row_x = sheet.createRow(r + 2);
                
                // Μία επανάληψη για κάθε γραμμή και για κάθε στήλη
                for (int c = 0; c < columns.size(); c++) {
                    
                    LinkedTreeMap<String, Object> columnDefinition = columns.get(c);
                    
                    // Στοιχεία στήλης
                    String columnName = columnDefinition.get(COL_NAME).toString();
                    String columnPropertyType = columnDefinition.get(COL_TYPE).toString();
                    
                    // Τιμή πεδίου
                    Object cellObj = rowData.getPropertyValue(columnName);
                    
                    if (cellObj == null) continue;
                    
                    // Δημιουργία κελιού
                    XSSFCell cell = row_x.createCell(c);
                    
                    // Καταχώρηση τιμής στο κελί
                    switch (columnPropertyType) {
                        
                        case "String":
                            cell.setCellValue((String) cellObj);
                            break;
                        
                        case "Long":
                            cell.setCellValue((Long) cellObj);
                            break;
                        
                        case "Integer":
                            cell.setCellValue((Integer) cellObj);
                            break;
                        
                        case "Date":
                            cell.setCellStyle(dateStyle);
                            cell.setCellValue((Date) cellObj);
                            break;
                        
                        default:
                            cell.setCellValue(cellObj.toString());
                            break;
                        
                    }
                    
                }
                
            }
            
            workbook.write(fos);
            fos.flush();
            fos.close();
            
        }
        catch (IOException e) {
            logger.error("Error while creating Excel file", e);
            
        }
        finally {
            
            if (stopWatch.isRunning()) stopWatch.stop();
            ThreadContext.put(stopWatch.getLastTaskName(), String.valueOf(stopWatch.getLastTaskTimeMillis()).concat(" ms"));
            
            //logger.trace("Monitor Excel File Creation: {} requested by user {}. Data size: {}", title, getUsername(), rows.size());
            ThreadContext.clearAll();
        }
        
    }
    
    /**
     * Προεπεξεργασία στηλών
     * Αφαίρεση στηλών που δεν αντιστοιχούν σε πεδία των αντικειμένων των δεδομένων
     * Καταχώρηση τύπου στήλης ως propertyType
     * @param columns Στήλες
     * @param row     Γραμμή δεδομένων που γίνεται wrapped
     */
    private void processColumns(List<LinkedTreeMap<String, Object>> columns, Object row) {
        
        BeanWrapperImpl rowWrapper = new BeanWrapperImpl(row);
        
        Iterator<LinkedTreeMap<String, Object>> iter = columns.iterator();
        
        while (iter.hasNext()) {
            
            LinkedTreeMap<String, Object> current = iter.next();
            String currentType = current.get(COL_NAME).toString();
            
            Class<?> checkColumnClass = rowWrapper.getPropertyType(currentType);
            
            if (checkColumnClass == null) {
                iter.remove();
            }
            else {
                current.put(COL_TYPE, checkColumnClass.getSimpleName());
            }
            
        }
    }
    
    @Override
    public void addSheetToWorkbook(XSSFWorkbook workbook, String title, List data, String model) {
        
        GsonBuilder gsb = new GsonBuilder();
        Gson gson = gsb.disableHtmlEscaping().create();
        
        // Περιγραφή στηλών του αρχείου
        List<LinkedTreeMap<String, Object>> columns = gson.fromJson(model, List.class);
        
        // Δεδομένα (γραμμές) του αρχείου
        List<Object> rows = data;
        
        //logger.info("Exporting data {} as Excel file requested by user {}. Data size: {}", title, getUsername(), rows.size());
        
        // Δημιουργία νέου φύλλου εργασίας
        XSSFSheet sheet = workbook.createSheet(title);
        
        // Καθορισμός γενικών παραμέτρων εμφάνισης και μορφοποίησης
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        style.setFont(font);
        
        XSSFCellStyle style2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeightInPoints((short) 16);
        style2.cloneStyleFrom(style);
        style2.setFont(font2);
        style2.setAlignment(HorizontalAlignment.CENTER);
        
        XSSFCellStyle dateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
        
        // Προεπεξεργασία στηλών
        processColumns(columns, rows.get(0));
        
        // Δημιουργία γραμμή επικεφαλίδας
        XSSFRow row0 = sheet.createRow(0);
        XSSFCell spanCell = row0.createCell(0);
        spanCell.setCellValue(title);
        spanCell.setCellStyle(style2);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.size() - 1));
        
        // Δημιουργία γραμμής για τις στήλες του πίνακα
        XSSFRow row1 = sheet.createRow(1);
        
        for (int c = 0; c < columns.size(); c++) {
            String columnName = columns.get(c).get(COL_TRANSLATED_NAME).toString();
            XSSFCell titleCol = row1.createCell(c);
            titleCol.setCellValue(columnName);
            titleCol.setCellStyle(style);
        }
        
        // Δημιουργία γραμμών για τα δεδομένα του πίνακα
        for (int r = 0; r < rows.size(); r++) {
            
            // Τρέχον αντικείμενο δεδομένων
            BeanWrapperImpl rowData = new BeanWrapperImpl(rows.get(r));
            
            XSSFRow row_x = sheet.createRow(r + 2);
            
            // Μία επανάληψη για κάθε γραμμή και για κάθε στήλη
            for (int c = 0; c < columns.size(); c++) {
                
                LinkedTreeMap<String, Object> columnDefinition = columns.get(c);
                
                // Στοιχεία στήλης
                String columnName = columnDefinition.get(COL_NAME).toString();
                String columnPropertyType = columnDefinition.get(COL_TYPE).toString();
                
                // Τιμή πεδίου
                Object cellObj = rowData.getPropertyValue(columnName);
                
                if (cellObj == null) continue;
                
                // Δημιουργία κελιού
                XSSFCell cell = row_x.createCell(c);
                
                // Καταχώρηση τιμής στο κελί
                switch (columnPropertyType) {
                    
                    case "String":
                        cell.setCellValue((String) cellObj);
                        break;
                    
                    case "Long":
                        cell.setCellValue((Long) cellObj);
                        break;
                    
                    case "Integer":
                        cell.setCellValue((Integer) cellObj);
                        break;
                    
                    case "Date":
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue((Date) cellObj);
                        break;
                    
                    default:
                        cell.setCellValue(cellObj.toString());
                        break;
                    
                }
                
            }
            
        }
    }
    
    @Override
    public void writeExcelToOutputStream(XSSFWorkbook workbook, OutputStream fos) {
        try {
            workbook.write(fos);
            fos.flush();
            fos.close();
        }
        catch (IOException e) {
            logger.error("Error while creating Excel file", e);
        }
        finally {
            //logger.trace("Monitor Multisheet Excel File Creation requested by user {}. Number of sheets: {}", getUsername(), workbook.getNumberOfSheets());
        }
    }
}
