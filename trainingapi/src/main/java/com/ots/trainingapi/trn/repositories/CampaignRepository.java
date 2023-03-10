package com.ots.trainingapi.trn.repositories;

import com.ots.trainingapi.global.querydsl.ExtendedQueryDslJpaRepository;
import com.ots.trainingapi.trn.core.domain.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends ExtendedQueryDslJpaRepository<Campaign, Long> {


//    Page<Campaign> getAll(Pageable page);
}
