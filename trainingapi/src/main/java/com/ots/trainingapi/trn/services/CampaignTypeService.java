package com.ots.trainingapi.trn.services;

import com.ots.trainingapi.trn.dto.mini.CampaignTypeMiniDto;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CampaignTypeService {
    
    /**
     * Ανάκτηση όλων των τύπων καμπάνιας (mini)
     */
    List<CampaignTypeMiniDto> getAllCampaignTypes();
}
