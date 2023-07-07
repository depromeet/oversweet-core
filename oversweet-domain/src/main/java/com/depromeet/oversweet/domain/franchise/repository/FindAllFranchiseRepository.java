package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

import java.util.List;

/**
 * 프랜차이즈 전체 목록 조호 인터페이스
 */
public interface FindAllFranchiseRepository {
    List<FranchiseEntity> findAll();
}
