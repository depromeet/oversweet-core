package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저가 마신 음료 당 기록 구현체
 */
@Repository
@RequiredArgsConstructor
public class SaveRecordRepositoryImpl implements SaveRecordRepository {

    private final RecordJpaRepository recordJpaRepository;

    @Override
    @Transactional
    public void saveRecord(RecordEntity record) {
        recordJpaRepository.save(record);
    }
}
