package com.depromeet.oversweet.domain.record.repository;

public interface DeleteRecordRepository {
    void deleteRecordByMemberIdAndDrinkId(Long memberId, Long drinkId);
}
