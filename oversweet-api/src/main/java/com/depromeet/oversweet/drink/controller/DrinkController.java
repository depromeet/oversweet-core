package com.depromeet.oversweet.drink.controller;


import com.depromeet.oversweet.drink.dto.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.service.DrinkStatisticsService;
import com.depromeet.oversweet.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "음료", description = "음료 관련 API")
@RestController
@RequestMapping("/api/v1/drinks")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkStatisticsService drinkStatisticsService;

    @Operation(summary = "하루 당 섭취량 통계 조회", description = "유저가 하루 먹은 당 통계를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "유저가 하루 먹은 당 통계 조회."))
    @GetMapping("/statistics/daily")
    public ResponseEntity<DataResponse<DrinkDailySugarStatisticsResponse>> retrieveUserDailySugarStatistics() {
        final DrinkDailySugarStatisticsResponse response = drinkStatisticsService.retrieveUserDailySugarStatistics(1L);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "유저가 하루 먹은 당 통계 조회 성공", response));
    }
}
