package com.depromeet.oversweet.drink.vo;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * 유저가 먹은 음료 당, 칼로리 통계 정보 DTO
 */
public class DrinkDailySugarTotalStatisticsInfo {
    @Schema(description = "하루 적정 당 섭취량", example = "25")
    private final int dailySugar;

    @Schema(description = "남은 하루 적정 당 섭취량", example = "5")
    private final int remainingDailySugar;

    @Schema(description = "오늘 먹은 총 당 섭취량", example = "15")
    private final int dailyTotalSugar;

    @Schema(description = "오늘 먹은 총 당 칼로리", example = "300")
    private final int dailyTotalCalorie;

    @Schema(description = "적정 당 섭취량을 초과 했는지 안했는지 여부", example = "false")
    private final boolean isExcess;

    private DrinkDailySugarTotalStatisticsInfo (final int dailySugar, final int remainingDailySugar, final int dailyIntakeTotalSugar, final int dailyTotalCalorie) {
        this.dailySugar = dailySugar;
        this.remainingDailySugar = checkRemainingSugar(remainingDailySugar);
        this.dailyTotalSugar = dailyIntakeTotalSugar;
        this.dailyTotalCalorie = dailyTotalCalorie;
        this.isExcess = isSugarExceeded(dailyIntakeTotalSugar, dailySugar);
    }

    public static DrinkDailySugarTotalStatisticsInfo of(final int dailySugar, final int remainingDailySugar, final int dailyTotalSugar, final int dailyTotalCalorie) {
        return new DrinkDailySugarTotalStatisticsInfo(dailySugar, remainingDailySugar, dailyTotalSugar, dailyTotalCalorie);
    }


    private int checkRemainingSugar(final int remainingDailySugar) {
        if (remainingDailySugar >= 0) {
            return remainingDailySugar;
        }
        return remainingDailySugar * -1;
    }

    private boolean isSugarExceeded(final int dailyTotalSugar, final int dailySugar) {
        return (dailySugar - dailyTotalSugar) < 0;
    }

    public int dailySugar() {
        return dailySugar;
    }

    public int remainingDailySugar() {
        return remainingDailySugar;
    }

    public int dailyTotalSugar() {
        return dailyTotalSugar;
    }

    public int dailyTotalCalorie() {
        return dailyTotalCalorie;
    }

    public boolean isExcess() {
        return isExcess;
    }
}
