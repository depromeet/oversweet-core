package com.depromeet.oversweet.domain.record.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.depromeet.oversweet.domain.record.dto.RankingDrink;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordJpaRepository extends JpaRepository<RecordEntity, Long> {


    @Query("SELECT new com.depromeet.oversweet.domain.record.dto.RankingDrink(d.id, d.imageUrl, d.name, COUNT(r)) " +
            "FROM RecordEntity r JOIN r.drink d " +
            "WHERE d.franchise.id = :franchiseId AND r.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY d.id, d.imageUrl, d.name " +
            "ORDER BY COUNT(r) DESC")
    List<RankingDrink> findTop3PopularDrinksByFranchiseIdAndBetweenDates(
            @Param("franchiseId") Long franchiseId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

    Optional<RecordEntity> findByIdAndMemberId(Long id, Long memberId);
}
