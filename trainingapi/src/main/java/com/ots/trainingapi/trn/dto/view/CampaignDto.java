package com.ots.trainingapi.trn.dto.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ots.trainingapi.global.utils.JsonDateDeserializer;
import com.ots.trainingapi.global.utils.JsonDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Καμπάνια - Dto")
public class CampaignDto {
    
    @ApiModelProperty("ID Εγγραφής")
    private Long id;
    
    @ApiModelProperty("Ονομασία")
    private String name;
    
    @ApiModelProperty("Id Τύπου Καμπάνιας")
    private Long campaignTypeId;
    
    @ApiModelProperty("Κόστος")
    private Double cost;
    
    @ApiModelProperty("Ένδειξη σε Ισχύ")
    private Boolean isRunning;
    
    @ApiModelProperty("Ημερομηνία Εναρξης")
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date startDate;
    
    @ApiModelProperty("Ημερομηνία Λήξης")
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date endDate;
    
    @ApiModelProperty("Παρατηρήσεις")
    private String comments;
    
    
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
    
    public Long getCampaignTypeId() {
        return campaignTypeId;
    }
    
    public void setCampaignTypeId(Long campaignTypeId) {
        this.campaignTypeId = campaignTypeId;
    }
    
    public Double getCost() {
        return cost;
    }
    
    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    public Boolean getIsRunning() {
        return isRunning;
    }
    
    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
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
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
}
