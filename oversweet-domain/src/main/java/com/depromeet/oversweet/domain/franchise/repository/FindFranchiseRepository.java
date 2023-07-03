package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

/**
 * 프랜차이즈 조회 Interface
 */
public interface FindFranchiseRepository {

    FranchiseEntity findFranchiseById(final Long franchiseId);
}
