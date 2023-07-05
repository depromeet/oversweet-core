package com.depromeet.oversweet.drink.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

/**
 * 유저가 하루 먹은 당 통계 및 음료 목록 정보 Response Dto
 */
@Builder
public record DrinkDailySugarStatisticsResponse(
        @Schema(description = "하루 음료 당 통계")
        DrinkDailySugarTotalStatisticsInfo totalDailyStatistics,

        @Schema(description = "오늘 마신 음료 목록")
        List<DrinkDailyDetailInfo> dailyDrinks
) {
}
