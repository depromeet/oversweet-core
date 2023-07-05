package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 유저의 해당 기간 음료 당 조회 Interface
 */
public interface FindRecordsRepository {
    List<RecordEntity> findRecordsByLocalDateTime(final Long memberId, final LocalDateTime startDate, LocalDateTime endDate);
}
