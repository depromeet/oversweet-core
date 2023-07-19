package com.depromeet.oversweet.domain.record.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class DeleteRecordRepositoryImpl implements DeleteRecordRepository{

    private final RecordJpaRepository recordJpaRepository;


    @Override
    @Transactional
    public void deleteById(final Long recordId) {
        recordJpaRepository.deleteById(recordId);
    }
}
