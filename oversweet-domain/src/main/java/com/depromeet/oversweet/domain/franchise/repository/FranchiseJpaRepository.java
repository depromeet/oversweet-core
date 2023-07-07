package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, Long>, FranchiseEntityFilter {

    @Query(value = "select * from franchise f where f.name like %:keyword%", nativeQuery = true)
    List<FranchiseEntity> findByKeyword(@Param("keyword") String keyword);
}