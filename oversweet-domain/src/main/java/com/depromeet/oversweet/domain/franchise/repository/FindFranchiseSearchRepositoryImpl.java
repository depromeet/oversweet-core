package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.exception.franchise.NotFoundFranchiseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_FRANCHISE;

/**
 * 프랜차이즈 검색 인터페이스 구현체
 */
@Repository
@RequiredArgsConstructor
public class FindFranchiseSearchRepositoryImpl implements FindFranchiseSearchRepository {

    private final FranchiseJpaRepository franchiseJpaRepository;

    @Override
    public List<FranchiseEntity> findFranchiseByKeyword(final String keyword) {
        return franchiseJpaRepository.findByKeyword(keyword);
    }
}
