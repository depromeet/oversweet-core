package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

import java.util.List;

/**
 * 프랜차이즈 검색 인터페이스
 */
public interface FindFranchiseSearchRepository {
    List<FranchiseEntity> findFranchiseByKeyword(String keyword);

    FranchiseEntity findFranchiseById(Long franchiseId);
}
