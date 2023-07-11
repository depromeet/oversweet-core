package com.depromeet.oversweet.domain.franchise.repository;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_FRANCHISE;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.exception.franchise.NotFoundFranchiseException;

import lombok.RequiredArgsConstructor;

/**
 * 프랜차이즈 검색 인터페이스 구현체
 */
@Repository
@RequiredArgsConstructor
public class FindFranchiseSearchRepositoryImpl implements FindFranchiseSearchRepository {

    private final FranchiseJpaRepository franchiseJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FranchiseEntity> findFranchiseByKeyword(final String keyword) {
        return franchiseJpaRepository.findByKeyword(keyword);
    }

    /**
     * 프랜차이즈 ID로 프랜차이즈 조회
     *
     * @param franchiseId 프랜차이즈 ID
     * @return 프랜차이즈
     */
    @Override
    @Transactional(readOnly = true)
    public FranchiseEntity findFranchiseById(Long franchiseId) {
        return franchiseJpaRepository.findById(franchiseId)
                .orElseThrow(() -> new NotFoundFranchiseException(NOT_FOUND_FRANCHISE));
    }
}
