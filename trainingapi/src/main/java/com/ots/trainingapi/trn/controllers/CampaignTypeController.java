package com.ots.trainingapi.trn.controllers;

import com.ots.trainingapi.trn.dto.mini.CampaignTypeMiniDto;
import com.ots.trainingapi.trn.services.CampaignTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("Controller διαχείρισης τύπων καμπάνιας")
@RequestMapping("/trn/campaigntypes")
public class CampaignTypeController {
    
    private static final Logger logger = LogManager.getLogger(CampaignTypeController.class);
    
    @Autowired
    private CampaignTypeService campaignTypeService;
    
    private final String getAllCampaignTypesApiOperationValue = "Ανάκτηση όλων των τύπων καμπάνιας";
    private final String getAllCampaignTypesApiOperationNotes = "Ανάκτηση όλων των τύπων καμπάνιας.";
    
    @ApiOperation(value = getAllCampaignTypesApiOperationValue, notes = getAllCampaignTypesApiOperationNotes)
    @GetMapping(value = "/getall", produces = "application/json;charset=UTF-8")
    public List<CampaignTypeMiniDto> getAllCampaignTypes() {
        return campaignTypeService.getAllCampaignTypes();
    }
}
