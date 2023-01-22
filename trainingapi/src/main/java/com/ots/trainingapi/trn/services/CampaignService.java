package com.ots.trainingapi.trn.services;

import com.ots.trainingapi.trn.args.CampaignArgs;
import com.ots.trainingapi.trn.core.domain.Campaign;
import com.ots.trainingapi.trn.dto.index.CampaignIndexDto;
import com.ots.trainingapi.trn.dto.view.CampaignDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CampaignService {


//    Page<Campaign> getAllCampaign(Pageable page);
    
    /**
     * Ανάκτηση σελίδας εγγραφών για το ευρετήριο
     */
    Page<CampaignIndexDto> campaignIndex(CampaignArgs args, Pageable pageable);
    
    /**
     * Ανάκτηση μιας εγγραφής
     */
    CampaignDto getCampaign(Long id);
    
    /**
     * Αποθήκευση μιας εγγραφής
     */
    CampaignDto saveCampaign(CampaignDto campaignDto);
    
    /**
     * Διαγραφή μιας εγγραφής
     */
    void deleteCampaign(Long id);
}
