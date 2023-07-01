package com.depromeet.oversweet.domain.record.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public FindRecordsRepositoryImpl findRecordsRepository(){
        return new FindRecordsRepositoryImpl(entityManager);
    }
}