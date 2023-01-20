package com.ots.trainingapi.trn.services.impl;

import com.ots.trainingapi.global.utils.MessageSourceProvider;
import com.ots.trainingapi.global.utils.TextUtils;
import com.ots.trainingapi.global.utils.ValidationUtils;
import com.ots.trainingapi.trn.args.CampaignArgs;
import com.ots.trainingapi.trn.core.domain.Campaign;
import com.ots.trainingapi.trn.core.domain.QCampaign;
import com.ots.trainingapi.trn.core.domain.QCampaignType;
import com.ots.trainingapi.trn.dto.index.CampaignIndexDto;
import com.ots.trainingapi.trn.dto.view.CampaignDto;
import com.ots.trainingapi.trn.predicates.CampaignPredicates;
import com.ots.trainingapi.trn.repositories.CampaignRepository;
import com.ots.trainingapi.trn.services.CampaignService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {
    
    private static final Logger logger = LogManager.getLogger(CampaignServiceImpl.class);
    
    @Autowired
    private JPAQueryFactory queryFactory;
    
    @Autowired
    private MessageSourceProvider messageSourceProvider;
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    
    /**
     * Ορισμός των πεδίων που έρχονται στο index dto
     */
    private FactoryExpression<CampaignIndexDto> campaignIndexDtoExpression(QCampaign qCampaign, QCampaignType qCampaignType) {
        return Projections.bean(CampaignIndexDto.class,
                qCampaign.id,
                qCampaign.name,
                qCampaignType.description.as("campaignTypeDescription"),
                qCampaignType.kind.stringValue().as("campaignTypeKind"),
                qCampaign.cost,
                qCampaign.startDate,
                qCampaign.endDate
        );
    }
    
    /**
     * Ορισμός των πεδίων που έρχονται στο dto
     */
    private FactoryExpression<CampaignDto> campaignDtoExpression(QCampaign qCampaign) {
        return Projections.bean(CampaignDto.class,
                qCampaign.id,
                qCampaign.name,
                qCampaign.campaignTypeId,
                qCampaign.cost,
                qCampaign.isRunning,
                qCampaign.startDate,
                qCampaign.endDate,
                qCampaign.comments
        );
    }
    
    /**
     * Αντιγραφή πεδίων από το dto στο entity
     */
    private void dtoToEntity(CampaignDto campaignDto, Campaign campaign) {
        
        if(campaignDto == null) {
            return;
        }
        
        if(campaign == null) {
            campaign = new Campaign();
        }
        
        BeanUtils.copyProperties(campaignDto, campaign);
    }
    
    /**
     * Validation για την αποθήκευση
     */
    private List<String> validateSave(Campaign campaign) {
        
        List<String> errors = new ArrayList<>();
        
        //Πρέπει να έχει οριστεί ονομασία
        if(TextUtils.isEmpty(campaign.getName())) {
            errors.add(messageSourceProvider.getMessage("error.trn.campaign.save.nameNull"));
        }
        
        //Πρέπει να έχει επιλεχθεί τύπος καμπάνιας
        if(campaign.getCampaignTypeId() == null) {
            errors.add(messageSourceProvider.getMessage("error.trn.campaign.save.campaignTypeNull"));
        }
        
        return errors;
    }
    
    /**
     * Validation για τη διαγραφή
     */
    private List<String> validateDelete(Campaign campaign) {
        
        List<String> errors = new ArrayList<>();
        
        //Delete validation checks here
        
        return errors;
    }
    
    @Override
    public Page<CampaignIndexDto> campaignIndex(CampaignArgs args, Pageable pageable) {
        
        QCampaign qCampaign = QCampaign.campaign;
        QCampaignType qCampaignType = QCampaignType.campaignType;
        
        BooleanBuilder predicate = CampaignPredicates.createCampaignIndexPredicate(args);
        
        FactoryExpression<CampaignIndexDto> factoryExpression = campaignIndexDtoExpression(qCampaign, qCampaignType);
        
        JPQLQuery<CampaignIndexDto> query = queryFactory
                .select(factoryExpression)
                .from(qCampaign)
                .leftJoin(qCampaignType).on(qCampaignType.id.eq(qCampaign.campaignTypeId))
                .where(predicate);
        
        return campaignRepository.findAll(query, pageable);
    }
    
    @Override
    public CampaignDto getCampaign(Long id) {
        
        if(id == null) {
            return null;
        }
        
        QCampaign qCampaign = QCampaign.campaign;
        
        FactoryExpression<CampaignDto> factoryExpression = campaignDtoExpression(qCampaign);
        
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qCampaign.id.eq(id));
        
        return queryFactory.select(factoryExpression).from(qCampaign).where(predicate).fetchFirst();
    }
    
    @Override
    @Transactional
    public CampaignDto saveCampaign(CampaignDto campaignDto) {
        
        Campaign campaign = (campaignDto.getId() == null) ? new Campaign() : campaignRepository.findById(campaignDto.getId()).get();
        
        if(campaign == null) {
            return null;
        }
        
        dtoToEntity(campaignDto, campaign);
        
        List<String> allErrors = validateSave(campaign);
        
        campaignRepository.save(campaign);
        
        ValidationUtils.throwIfExistsValidationException(allErrors);
        
        return getCampaign(campaign.getId());
    }
    
    @Override
    @Transactional
    public void deleteCampaign(Long id) {
        
        if(id != null) {
            Campaign campaign = campaignRepository.findById(id).get();
            
            if(campaign != null) {
                
                List<String> allErrors = validateDelete(campaign);
                
                ValidationUtils.throwIfExistsValidationException(allErrors);
                
                campaignRepository.delete(campaign);
            }
        }
    }
}
