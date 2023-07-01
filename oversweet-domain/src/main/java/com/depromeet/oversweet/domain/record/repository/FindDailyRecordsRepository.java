package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 유저의 데일리(하루) 음료 조회 Interface
 */
public interface FindDailyRecordsRepository {
    List<RecordEntity> findDailyRecordsByLocalDatetime(final Long memberId, final LocalDateTime startDate, LocalDateTime endDate);
}
