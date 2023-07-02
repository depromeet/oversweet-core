package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.exception.franchise.NotFoundFranchiseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_FRANCHISE;

/**
 * 프랜차이즈 조회 Repository
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class FindFranchiseRepositoryImpl implements FindFranchiseRepository {

    private final FranchiseJpaRepository franchiseJpaRepository;

    /**
     * 프랜차이즈 ID로 프랜차이즈를 조회한다.
     *
     * @param franchiseId 조회하고자 하는 프랜차이즈 ID
     * @return 프랜차이즈 Entity
     */
    @Override
    @Transactional(readOnly = true)
    public FranchiseEntity findFranchiseById(final Long franchiseId) {
        return franchiseJpaRepository.findById(franchiseId)
                .orElseThrow(() -> new NotFoundFranchiseException(NOT_FOUND_FRANCHISE));
    }
}
