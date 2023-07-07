package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;

/**
 * 유저가 마신 음료 당 기록 Interface
 */
public interface SaveRecordRepository {

    void saveRecord(RecordEntity record);
}
