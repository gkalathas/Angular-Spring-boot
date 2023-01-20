package com.ots.trainingapi.global.querydsl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Extension of {@link QueryDslJpaRepository} which adds implementation for {@link ExtendedQueryDslJpaRepository}
 * <p>Υλοποίηση μεθόδων που θα χρησιμοποιούνται από όλα τα repositories σε ένα κοινό σημείο.</p>
 * <p>Ορίζεται ως βασική κλάση υλοποίησης των Jpa repositories στο {@link com.ots.campus.registry.config.JpaConfig}</p>
 * @param <T>  Entity class
 * @param <ID> Id class
 */
public class ExtendedQueryDslJpaRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements ExtendedQueryDslJpaRepository<T, ID> {

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;

    private EntityManager entityManager;

    public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver
            resolver) {

        super(entityInformation, entityManager);
        this.path = resolver.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder(this.path.getType(), this.path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
        this.entityManager = entityManager;
    }

    @Override
    public <T1> Page<T1> findAll(JPQLQuery jpqlQuery, Pageable pageable) {

        // Ερώτημα count συνολικών αποτελεσμάτων
        final JPQLQuery<?> countQuery = jpqlQuery;

        // Ερώτημα συγκεκριμένης σελίδας αποτελεσμάτων
        JPQLQuery<T1> query = querydsl.applyPagination(pageable, jpqlQuery);

        // Εκτέλεση των ερωτημάτων
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }
}
