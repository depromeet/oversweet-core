package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 프랜차이즈 전체 목록 조회 구현체
 */
@Repository
@RequiredArgsConstructor
public class FindAllFranchiseRepositoryImpl implements FindAllFranchiseRepository {

    private final FranchiseJpaRepository franchiseJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FranchiseEntity> findAll() {
        return franchiseJpaRepository.findAll();
    }
}
