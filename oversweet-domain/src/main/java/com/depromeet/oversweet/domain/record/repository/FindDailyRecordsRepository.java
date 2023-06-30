package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface FindDailyRecordsRepository {
    List<RecordEntity> findDailyRecordsByLocalDatetime(final Long memberId, final LocalDateTime startDate, LocalDateTime endDate);
}
