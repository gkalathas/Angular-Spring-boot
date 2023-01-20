package com.ots.trainingapi.trn.core.domain;

import com.ots.trainingapi.trn.core.enums.CampaignTypeKind;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Τύπος Καμπάνιας
 */
@Entity
@Table(name = "trn_campaigntype")
public class CampaignType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id")
    private Long id;
    
    /**
     * Περιγραφή
     */
    @Column(name = "v_description")
    private String description;
    
    /**
     * Είδος
     */
    @Column(name = "v_kind")
    @Enumerated(EnumType.STRING)
    private CampaignTypeKind kind;
    
    
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
    
    public CampaignTypeKind getKind() {
        return kind;
    }
    
    public void setKind(CampaignTypeKind kind) {
        this.kind = kind;
    }
}
