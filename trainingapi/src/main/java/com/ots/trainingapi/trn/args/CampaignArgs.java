package com.ots.trainingapi.trn.args;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ots.trainingapi.global.core.enums.YesNoEnum;
import com.ots.trainingapi.global.utils.JsonDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Καμπάνια - Args")
public class CampaignArgs {
    
    @ApiModelProperty("Ονομασία")
    private String name;
    
    @ApiModelProperty("Id Τύπου Ακινήτου")
    private Long campaignTypeId;
    
    @ApiModelProperty("Ένδειξη αν το συμβόλαιο έχει παρατάσεις")
    private YesNoEnum isRunning;
    
    @ApiModelProperty("Ημερομηνία Εναρξης Από")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date startDateAfter;
    
    @ApiModelProperty("Ημερομηνία Εναρξης Έως")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date startDateBefore;
    
    
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
    
    public YesNoEnum getIsRunning() {
        return isRunning;
    }
    
    public void setIsRunning(YesNoEnum isRunning) {
        this.isRunning = isRunning;
    }
    
    public Date getStartDateAfter() {
        return startDateAfter;
    }
    
    public void setStartDateAfter(Date startDateAfter) {
        this.startDateAfter = startDateAfter;
    }
    
    public Date getStartDateBefore() {
        return startDateBefore;
    }
    
    public void setStartDateBefore(Date startDateBefore) {
        this.startDateBefore = startDateBefore;
    }
}
