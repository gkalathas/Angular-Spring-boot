package com.ots.trainingapi.trn.predicates;

import com.ots.trainingapi.global.core.enums.YesNoEnum;
import com.ots.trainingapi.global.utils.DateUtils;
import com.ots.trainingapi.global.utils.TextUtils;
import com.ots.trainingapi.trn.args.CampaignArgs;
import com.ots.trainingapi.trn.core.domain.QCampaign;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Date;

public class CampaignPredicates {
    
    /**
     * Ονομασία (startsWith)
     */
    public static BooleanExpression nameStartsWith(final String name) {
        return QCampaign.campaign.name.upper().like(TextUtils.toUpperCaseGreekSupportExtended(name + "%"));
    }
    
    /**
     * Id Τύπου Καμπάνιας (equals)
     */
    public static BooleanExpression campaignTypeIdEquals(final Long campaignTypeId) {
        return QCampaign.campaign.campaignTypeId.eq(campaignTypeId);
    }
    
    /**
     * Σε Ισχύ
     */
    public static BooleanExpression isRunning() {
        return QCampaign.campaign.isRunning.eq(true);
    }
    
    /**
     * Όχι σε Ισχύ
     */
    public static BooleanExpression isNotRunning() {
        return QCampaign.campaign.isRunning.eq(false).or(QCampaign.campaign.isRunning.isNull());
    }
    
    /**
     * Ημερομηνία Έναρξης από
     */
    public static BooleanExpression startDateAfter(final Date startDate) {
        return QCampaign.campaign.startDate.goe(DateUtils.getStartOfDay(startDate));
    }
    
    /**
     * Ημερομηνία Έναρξης έως
     */
    public static BooleanExpression startDateBefore(final Date startDate) {
        return QCampaign.campaign.startDate.loe(DateUtils.getEndOfDay(startDate));
    }
    
    /**
     * Κριτήρια ευρετηρίου
     */
    public static BooleanBuilder createCampaignIndexPredicate(CampaignArgs args) {
        
        BooleanBuilder predicate = new BooleanBuilder();
        
        if(!TextUtils.isEmpty(args.getName())) {
            predicate.and(nameStartsWith(args.getName()));
        }
        
        if(args.getCampaignTypeId() != null) {
            predicate.and(campaignTypeIdEquals(args.getCampaignTypeId()));
        }
        
        if(args.getIsRunning() != null) {
            if(args.getIsRunning().equals(YesNoEnum.YES)) {
                predicate.and(isRunning());
            }
            else if(args.getIsRunning().equals(YesNoEnum.NO)) {
                predicate.and(isNotRunning());
            }
        }
        
        if(args.getStartDateAfter() != null) {
            predicate.and(startDateAfter(args.getStartDateAfter()));
        }
        
        if(args.getStartDateBefore() != null) {
            predicate.and(startDateBefore(args.getStartDateBefore()));
        }
        
        return predicate;
    }
}
