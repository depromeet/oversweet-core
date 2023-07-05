package com.depromeet.oversweet.drink.dto.response;

import com.depromeet.oversweet.common.dto.response.MemberInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

/**
 * 유저의 주간 당 통계 Response Dto
 */
@Builder
public record DrinkWeeklySugarStatisticsResponse(
        @Schema(description = "유저 디테일 정보") MemberInfo member,
        @Schema(description = "주간 당 통계 차트 정보") List<DrinkDailySugarInfo> weeklyChart,
        @Schema(description = "주간 당 종합 정보") DrinkWeeklySugarTotalStatisticsInfo weeklyTotalSugarInfo
        ) {

}
