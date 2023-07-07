package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

import java.util.List;

public interface FindAllFranchiseRepository {
    List<FranchiseEntity> findAll();
}
