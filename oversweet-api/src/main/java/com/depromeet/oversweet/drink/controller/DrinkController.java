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

    /**
     * 유저 하루(데일리) 먹은 당 통계 및 음료 목록 조회.
     * 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "하루 당 섭취량 통계 조회", description = "유저가 하루 먹은 당 통계를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "유저가 하루 먹은 당 통계 조회."))
    @GetMapping("/statistics/daily")
    public ResponseEntity<DataResponse<DrinkDailySugarStatisticsResponse>> retrieveUserDailySugarStatistics() {
        final DrinkDailySugarStatisticsResponse response = drinkStatisticsService.retrieveUserDailySugarStatistics(1L);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "유저가 하루 먹은 당 통계 조회 성공", response));
    }
}
