package com.ots.trainingapi.trn.dto.mini;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Τύπος Καμπάνιας - MiniDto")
public class CampaignTypeMiniDto {
    
    @ApiModelProperty("Id Εγγραφής")
    private Long id;
    
    @ApiModelProperty("Περιγραφή")
    private String description;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
