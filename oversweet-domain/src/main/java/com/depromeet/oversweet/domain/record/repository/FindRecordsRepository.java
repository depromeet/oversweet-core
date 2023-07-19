package com.depromeet.oversweet.domain.record.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.depromeet.oversweet.domain.record.dto.RankingDrink;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;

/**
 * 유저의 해당 기간 음료 당 조회 Interface
 */
public interface FindRecordsRepository {
    List<RecordEntity> findRecordsByLocalDateTime(final Long memberId, final LocalDateTime startDate, LocalDateTime endDate);

    Optional<RecordEntity> findRecordById(Long id);

    List<RankingDrink> findPopularDrinkRecordsByFranchiseId(Long franchiseId, LocalDateTime startDate, LocalDateTime endDate);

    RecordEntity findRecordByMemberIdAndDrinkId(Long memberId, Long drinkId);
}
