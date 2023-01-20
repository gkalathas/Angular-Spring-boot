package com.ots.trainingapi.trn.repositories;

import com.ots.trainingapi.global.querydsl.ExtendedQueryDslJpaRepository;
import com.ots.trainingapi.trn.core.domain.CampaignType;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignTypeRepository extends ExtendedQueryDslJpaRepository<CampaignType, Long> {

}
