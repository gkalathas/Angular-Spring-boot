package com.ots.trainingapi.trn.controllers;

import com.ots.trainingapi.trn.args.CampaignArgs;
import com.ots.trainingapi.trn.core.domain.Campaign;
import com.ots.trainingapi.trn.dto.index.CampaignIndexDto;
import com.ots.trainingapi.trn.dto.view.CampaignDto;
import com.ots.trainingapi.trn.services.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Controller διαχείρισης καμπανιών")
@RequestMapping(value = "/trn/campaigns")
public class CampaignController {
    
    private static final Logger logger = LogManager.getLogger(CampaignController.class);
    
    @Autowired
    private CampaignService campaignService;
    
    
    private final String campaignIndexApiOperationValue = "Ευρετήριο καμπανιών";
    private final String campaignIndexApiOperationNotes = "Ευρετήριο καμπανιών.";
    private final String campaignIndexApiParamArgs = "Κριτήρια αναζήτησης";


//    @GetMapping("/getAll")
//    public Page<Campaign> getAllCampaign(Pageable page) {
//        return campaignService.getAllCampaign(page);
//    }
    @ApiOperation(value = campaignIndexApiOperationValue, notes = campaignIndexApiOperationNotes)
    @PostMapping(value = "/index", produces = "application/json;charset=UTF-8")
    public Page<CampaignIndexDto> campaignIndex(Pageable pageable, @RequestBody @ApiParam(campaignIndexApiParamArgs) CampaignArgs args) {
        return campaignService.campaignIndex(args, pageable);
    }
    
    private final String getCampaignApiOperationValue = "Ανάκτηση της καμπάνιας με το δεδομένο id";
    private final String getCampaignApiOperationNotes = "Ανάκτηση της καμπάνιας με το δεδομένο id.";
    private final String getCampaignApiParamId = "Το id της καμπάνιας";
    
    @ApiOperation(value = getCampaignApiOperationValue, notes = getCampaignApiOperationNotes)
    @GetMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public CampaignDto getCampaign(@RequestParam @ApiParam(value = getCampaignApiParamId, required = true) Long id) {
        return campaignService.getCampaign(id);
    }
    
    private final String saveCampaignApiOperationValue = "Αποθήκευση αντικειμένου καμπάνιας";
    private final String saveCampaignApiOperationNotes = "Αποθήκευση του δεδομένου αντικειμένου καμπάνιας. Αν το αντικείμενο δεν έχει id, τότε γίνεται δημιουργία νέας εγγραφής, αλλιώς γίνεται ενημέρωση υπάρχουσας εγγραφής.";
    private final String saveCampaignApiParamCampaignDto = "Αντικείμενο καμπάνιας προς αποθήκευση";
    
    @ApiOperation(value = saveCampaignApiOperationValue, notes = saveCampaignApiOperationNotes)
    @PostMapping(value = "/save", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public CampaignDto saveCampaign(@RequestBody @ApiParam(value = saveCampaignApiParamCampaignDto, required = true) CampaignDto campaignDto) {
        return campaignService.saveCampaign(campaignDto);
    }
    
    private final String deleteCampaignApiOperationValue = "Διαγραφή καμπάνιας";
    private final String deleteCampaignApiOperationNotes = "Διαγραφή τηε δεδομένης καμπάνιας.";
    private final String deleteCampaignApiParamId = "Id καμπάνιας προς διαγραφή";
    
    @ApiOperation(value = deleteCampaignApiOperationValue, notes = deleteCampaignApiOperationNotes)
    @DeleteMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> deleteCampaign(@RequestParam @ApiParam(deleteCampaignApiParamId) Long id) {
        campaignService.deleteCampaign(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}