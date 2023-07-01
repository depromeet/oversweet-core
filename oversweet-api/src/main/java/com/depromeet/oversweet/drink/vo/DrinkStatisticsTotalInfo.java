package com.depromeet.oversweet.drink.vo;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.drink.dto.DrinkDailySugarTotalStatisticsInfo;

import java.util.List;

public record DrinkStatisticsTotalInfo(
        List<RecordEntity> recordEntities
){
    public DrinkDailySugarTotalStatisticsInfo getTotalStatisticsInfo(final int dailySugar){
        final int totalIntakeSugar = getTotalIntakeSugar(recordEntities);
        final int totalCalorie = getTotalCalorie(recordEntities);
        final int remainingSugar = getRemainingSugar(totalIntakeSugar, dailySugar);

        return DrinkDailySugarTotalStatisticsInfo.of(dailySugar, remainingSugar, totalIntakeSugar, totalCalorie);
    }
    private int getTotalIntakeSugar(final List<RecordEntity> recordEntities) {
        return recordEntities.stream()
                .mapToInt(RecordEntity::totalSugar)
                .sum();
    }

    private int getTotalCalorie(final List<RecordEntity> dailyRecords) {
        return dailyRecords.stream()
                .mapToInt(RecordEntity::totalCalorie)
                .sum();
    }

    private int getRemainingSugar(final int totalIntakeSugar, final int memberDailySugar) {
        return memberDailySugar - totalIntakeSugar;
    }

}
