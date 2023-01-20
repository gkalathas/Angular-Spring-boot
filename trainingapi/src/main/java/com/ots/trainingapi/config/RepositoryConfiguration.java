package com.ots.trainingapi.config;

import com.ots.trainingapi.global.querydsl.ExtendedQueryDslJpaRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.ots.trainingapi.trn.repositories"
},
        repositoryBaseClass = ExtendedQueryDslJpaRepositoryImpl.class
)
public class RepositoryConfiguration {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}