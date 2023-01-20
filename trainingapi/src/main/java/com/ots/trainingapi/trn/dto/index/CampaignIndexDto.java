package com.ots.trainingapi.trn.dto.index;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ots.trainingapi.global.utils.JsonDateSerializer;
import com.ots.trainingapi.global.utils.TextUtils;
import com.ots.trainingapi.trn.core.enums.CampaignTypeKind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Καμπάνια - IndexDto")
public class CampaignIndexDto {
    
    @ApiModelProperty("Id εγγραφής")
    private Long id;
    
    @ApiModelProperty("Ονομασία")
    private String name;
    
    @ApiModelProperty("Περιγραφή Τύπου Καμπάνιας")
    private String campaignTypeDescription;
    
    @ApiModelProperty("Είδος Τύπου Καμπάνιας")
    private String campaignTypeKind;
    
    @ApiModelProperty("Λεκτικό Είδους Τύπου Καμπάνιας")
    private String campaignTypeKindLabel;
    
    @ApiModelProperty("Κόστος")
    private Double cost;
    
    @ApiModelProperty("Ημερομηνία Εναρξης")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date startDate;
    
    @ApiModelProperty("Ημερομηνία Λήξης")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endDate;
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCampaignTypeDescription() {
        return campaignTypeDescription;
    }
    
    public void setCampaignTypeDescription(String campaignTypeDescription) {
        this.campaignTypeDescription = campaignTypeDescription;
    }
    
    public String getCampaignTypeKind() {
        return campaignTypeKind;
    }
    
    public void setCampaignTypeKind(String campaignTypeKind) {
        this.campaignTypeKind = campaignTypeKind;
    }
    
    public String getCampaignTypeKindLabel() {
        return TextUtils.isEmpty(campaignTypeKind) ? null : CampaignTypeKind.valueOf(campaignTypeKind).toString();
    }
    
    public void setCampaignTypeKindLabel(String campaignTypeKindLabel) {
        this.campaignTypeKindLabel = campaignTypeKindLabel;
    }
    
    public Double getCost() {
        return cost;
    }
    
    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
