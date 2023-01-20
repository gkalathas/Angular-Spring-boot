package com.ots.trainingapi.trn.services.impl;

import com.ots.trainingapi.global.utils.MessageSourceProvider;
import com.ots.trainingapi.trn.core.domain.QCampaignType;
import com.ots.trainingapi.trn.dto.mini.CampaignTypeMiniDto;
import com.ots.trainingapi.trn.repositories.CampaignTypeRepository;
import com.ots.trainingapi.trn.services.CampaignTypeService;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignTypeServiceImpl implements CampaignTypeService {
    
    private static final Logger logger = LogManager.getLogger(CampaignTypeServiceImpl.class);
    
    @Autowired
    private JPAQueryFactory queryFactory;
    
    @Autowired
    private MessageSourceProvider messageSourceProvider;
    
    @Autowired
    private CampaignTypeRepository campaignTypeRepository;
    
    
    /**
     * Ορισμός των πεδίων που έρχονται στο mini dto
     */
    private FactoryExpression<CampaignTypeMiniDto> campaignTypeMiniDtoExpression(QCampaignType qCampaignType) {
        return Projections.bean(CampaignTypeMiniDto.class,
                qCampaignType.id,
                qCampaignType.description
        );
    }
    
    @Override
    public List<CampaignTypeMiniDto> getAllCampaignTypes() {
        
        QCampaignType qCampaignType = QCampaignType.campaignType;
        
        FactoryExpression<CampaignTypeMiniDto> factoryExpression = campaignTypeMiniDtoExpression(qCampaignType);
        
        return queryFactory
                .select(factoryExpression)
                .from(qCampaignType)
                .fetch();
    }
}
