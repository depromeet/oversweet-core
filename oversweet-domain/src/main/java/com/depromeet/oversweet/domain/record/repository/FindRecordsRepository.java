package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.dto.RankingDrinks;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 유저의 해당 기간 음료 당 조회 Interface
 */
public interface FindRecordsRepository {
    List<RecordEntity> findRecordsByLocalDateTime(final Long memberId, final LocalDateTime startDate, LocalDateTime endDate);

    Optional<RecordEntity> findRecordById(Long id);

    RankingDrinks findPopularDrinkRecordsByFranchiseId(Long franchiseId, LocalDateTime startDate, LocalDateTime endDate);
}
