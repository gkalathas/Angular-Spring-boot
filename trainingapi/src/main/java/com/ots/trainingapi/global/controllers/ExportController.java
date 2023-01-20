package com.ots.trainingapi.global.controllers;

import com.ots.trainingapi.config.ContextProvider;
import com.ots.trainingapi.global.args.IndexArgsFactory;
import com.ots.trainingapi.global.dto.ExportDataDto;
import com.ots.trainingapi.global.services.ExportService;
import com.ots.trainingapi.global.utils.TextUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@RestController
@RequestMapping(value = "/global/export")
public class ExportController {
    
    private static final Logger logger = LogManager.getLogger(ExportController.class);
    
    @Autowired
    private ExportService exportService;
    
    
    @ApiOperation(value = "", hidden = true)
    @PostMapping(value = "/excel")
    public void exportToExcel(@RequestParam String controllerName,
                              @RequestParam String methodName,
                              @RequestParam(required = false) String argsClass,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        
        ExportDataDto exportParams = exportService.initializeExportParams(request);
        Pageable pageable = exportService.getExportPageable(exportParams);
        
        Class<?> argsClazz = null;
        Object args = null;
        
        String searchString = request.getParameter("searchString");
        if(!TextUtils.isEmpty(searchString) && !TextUtils.isEmpty(argsClass) && !argsClass.equals("null")) {
			try {
				argsClazz = Class.forName("com.ots.trainingapi." + argsClass);
				args = IndexArgsFactory.createArgs(argsClazz, searchString);
			}
			catch (ClassNotFoundException e) {
				logger.error("Error getting class for args: " + argsClass);
				return;
			}
		}
		
		try {
			Object controllerObject = ContextProvider.getBean(controllerName);
			Method method = null;
			Page indexData = null;
			
			if(argsClazz != null) {
				//Με args
				method = controllerObject.getClass().getMethod(methodName, Pageable.class, argsClazz);
				indexData = (Page) method.invoke(controllerObject, pageable, args);
			}
			else {
				//Χωρίς args
				method = controllerObject.getClass().getMethod(methodName, Pageable.class);
				indexData = (Page) method.invoke(controllerObject, pageable);
			}
			
			try {
				ServletOutputStream outputStream = response.getOutputStream();
				exportService.listToExcelFile(exportParams.getTitle(), indexData.getContent(), exportParams.getModel(), outputStream);
			}
			catch(IOException e) {
				logger.error("Error while initializing Excel file creation", e);
			}
		}
		catch(Exception ex) {
			logger.error("Error while invoking method for Excel file creation", ex);
		}
    }
}
