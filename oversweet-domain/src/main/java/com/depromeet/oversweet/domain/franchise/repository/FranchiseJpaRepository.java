package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, Long>, FranchiseEntityFilter {

    Optional<FranchiseEntity> findById(Long franchiseId);
}