package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

import java.util.List;

/**
 * 프랜차이즈를 필터링을 통해서 조회하는 커스텀 인터페이스.
 */
public interface FranchiseEntityFilter {

    List<FranchiseEntity> findAllByFranchiseIds(List<Long> franchiseIds);

}
