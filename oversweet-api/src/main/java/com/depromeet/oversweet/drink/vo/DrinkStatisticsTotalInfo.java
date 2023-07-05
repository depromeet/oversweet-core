package com.depromeet.oversweet.drink.vo;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarTotalStatisticsInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarTotalStatisticsInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
    RecordEntity를 이용해서 총합 (당, 칼로리, 남은 당)을 계산하기 위한 클래스입니다.
 */
public record DrinkStatisticsTotalInfo(
        List<RecordEntity> recordEntities
){
    public DrinkDailySugarTotalStatisticsInfo getDailyTotalStatisticsInfo(final int dailySugar){
        final int totalIntakeSugar = getTotalIntakeSugar();
        final int totalCalorie = getTotalCalorie();
        final int remainingSugar = getRemainingSugar(totalIntakeSugar, dailySugar);

        return DrinkDailySugarTotalStatisticsInfo.of(dailySugar, remainingSugar, totalIntakeSugar, totalCalorie);
    }

    public DrinkWeeklySugarTotalStatisticsInfo getWeeklyTotalStatisticsInfo(){
        final int totalIntakeSugar = getTotalIntakeSugar();
        final int dailyAverageSugar = getDailyAverageSugar();
        final int totalCalorie = getTotalCalorie();
        return new DrinkWeeklySugarTotalStatisticsInfo(totalIntakeSugar, dailyAverageSugar, totalCalorie);
    }

    public List<DrinkDailySugarInfo> getDayOfWeekSugarInfo() {
        return recordEntities.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getCreatedAt().toLocalDate(),
                        Collectors.summingInt(RecordEntity::totalSugar)))
                .entrySet().stream()
                .map(entry -> new DrinkDailySugarInfo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private int getDailyAverageSugar() {
        return (int) recordEntities.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getCreatedAt().toLocalDate(),
                        Collectors.summingInt(RecordEntity::totalSugar)))
                .values().stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }

    private int getTotalIntakeSugar() {
        return recordEntities.stream()
                .mapToInt(RecordEntity::totalSugar)
                .sum();
    }

    private int getTotalCalorie() {
        return recordEntities.stream()
                .mapToInt(RecordEntity::totalCalorie)
                .sum();
    }

    private int getRemainingSugar(final int totalIntakeSugar, final int memberDailySugar) {
        return memberDailySugar - totalIntakeSugar;
    }

}
