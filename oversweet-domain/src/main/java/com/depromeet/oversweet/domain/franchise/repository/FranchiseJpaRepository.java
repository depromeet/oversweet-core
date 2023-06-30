package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, Long>, FranchiseEntityFilter {

}