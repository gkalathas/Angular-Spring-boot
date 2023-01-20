package com.ots.trainingapi.trn.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Καμπάνια
 */
@Entity
@Table(name = "trn_campaign")
public class Campaign {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id")
    private Long id;
    
    /**
     * Ονομασία
     */
    @Column(name = "v_name")
    private String name;
    
    /**
     * Id Τύπου Καμπάνιας
     */
    @Column(name = "n_campaigntype_id")
    private Long campaignTypeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_campaigntype_id", referencedColumnName = "n_id", updatable = false, insertable = false)
    private CampaignType campaignType; //Χρησιμοποιείται μόνο για την ταξινόμηση στο ευρετήριο στο frontend
    
    /**
     * Κόστος
     */
    @Column(name = "n_cost")
    private Double cost;
    
    /**
     * Ένδειξη σε Ισχύ
     */
    @Column(name = "n_isrunning")
    private Boolean isRunning;
    
    /**
     * Ημερομηνία Εναρξης
     */
    @Column(name = "dt_startdate")
    private Date startDate;
    
    /**
     * Ημερομηνία Λήξης
     */
    @Column(name = "dt_enddate")
    private Date endDate;
    
    /**
     * Παρατηρήσεις
     */
    @Column(name = "v_comments")
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
    
    public CampaignType getCampaignType() {
        return campaignType;
    }
    
    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
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
