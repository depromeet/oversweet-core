package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, Long>, FranchiseEntityFilter {

    @Query(value = "select * from franchise f where f.name like %:keyword%", nativeQuery = true)
    @QueryHints(@QueryHint(name = "org.hibernate.readOnly", value = "true"))
    List<FranchiseEntity> findByKeyword(@Param("keyword") String keyword);
}