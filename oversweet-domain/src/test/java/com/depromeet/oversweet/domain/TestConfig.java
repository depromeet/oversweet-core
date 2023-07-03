package com.depromeet.oversweet.domain;

import com.depromeet.oversweet.domain.bookmark.repository.FindFranchiseBookMarkRepositoryImpl;
import com.depromeet.oversweet.domain.bookmark.repository.FranchiseBookMarkJpaRepository;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepositoryImpl;
import com.depromeet.oversweet.domain.franchise.repository.FranchiseJpaRepository;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepositoryImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final FranchiseJpaRepository franchiseJpaRepository;
    private final FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;
    private final EntityManager entityManager;

    @Bean
    public FindRecordsRepositoryImpl findRecordsRepository(){
        return new FindRecordsRepositoryImpl(entityManager);
    }

    @Bean
    public FindFranchiseRepositoryImpl findFranchiseRepositoryImpl(){
        return new FindFranchiseRepositoryImpl(franchiseJpaRepository);
    }

    @Bean
    public FindFranchiseBookMarkRepositoryImpl findFranchiseBookMarkRepositoryImpl(){
        return new FindFranchiseBookMarkRepositoryImpl(franchiseBookMarkJpaRepository);
    }
}