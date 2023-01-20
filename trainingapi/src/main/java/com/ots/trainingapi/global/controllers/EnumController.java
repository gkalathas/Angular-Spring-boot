package com.ots.trainingapi.global.controllers;

import com.ots.trainingapi.global.dto.EnumDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "Controller διαχείρισης enums")
@RequestMapping("/global/enums")
public class EnumController {
    
    private static final Logger logger = LogManager.getLogger(EnumController.class);
    
    private final String getEnumValuesApiOperationValue = "Ανάκτηση των τιμών του δεδομένου enum";
    private final String getEnumValuesApiOperationNotes = "Ανάκτηση των τιμών του δεδομένου enum.";
    private final String getEnumValuesApiParamEnumClass = "Ολόκληρο το όνομα της Java κλάσης του api που ακολουθεί το \"com.ots.trainingapi.\"";
    
    @ApiOperation(value = getEnumValuesApiOperationValue, notes = getEnumValuesApiOperationNotes)
    @GetMapping(value = "/getvalues/{enumClass:.+}", produces = "application/json;charset=UTF-8")
    public List<EnumDto> getEnumValues(@PathVariable("enumClass") @ApiParam(getEnumValuesApiParamEnumClass) String enumClass) {
        
        List<EnumDto> list = new ArrayList<>();
        
        try {
            Class<?> clazz = Class.forName("com.ots.trainingapi." + enumClass);
            
            for (Object obj : clazz.getEnumConstants()) {
                Enum<?> enumeration = (Enum<?>) obj;
                
                String value = enumeration.name();
                String label = enumeration.toString();
                Integer ordinal = enumeration.ordinal();
                
                EnumDto enumDto = new EnumDto();
                enumDto.setValue(value);
                enumDto.setLabel(label);
                enumDto.setOrdinal(ordinal);
                
                list.add(enumDto);
            }
            
        }
        catch (ClassNotFoundException e) {
            logger.error(e.toString());
        }
        
        return list;
    }
}
