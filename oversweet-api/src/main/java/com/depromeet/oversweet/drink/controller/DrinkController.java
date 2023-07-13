package com.depromeet.oversweet.drink.controller;


import com.depromeet.oversweet.drink.dto.request.DrinkInfoRequest;
import com.depromeet.oversweet.drink.dto.request.DrinkWeeklySugarDateRequest;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkDetailInfoResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkRedisInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarStatisticsResponse;
import com.depromeet.oversweet.drink.service.DrinkDailyStatisticsService;
import com.depromeet.oversweet.drink.service.DrinkDetailSearchService;
import com.depromeet.oversweet.drink.service.DrinkRedisService;
import com.depromeet.oversweet.drink.service.DrinkWeeklyStatisticsService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.security.service.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "음료", description = "음료 관련 API")
@RestController
@RequestMapping("/api/v1/drinks")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkDailyStatisticsService drinkDailyStatisticsService;
    private final DrinkWeeklyStatisticsService drinkWeeklyStatisticsService;
    private final DrinkDetailSearchService drinkDetailSearchService;
    private final DrinkRedisService drinkRedisService;

    /**
     * 유저 하루(데일리) 먹은 당 통계 및 음료 목록 조회.
     */
    @SecurityRequirement(name = "accessToken")
    @Operation(summary = "하루 당 섭취량 통계 조회", description = "유저가 하루 먹은 당 통계를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "유저가 하루 먹은 당 통계 조회."))
    @GetMapping("/statistics/daily")
    public ResponseEntity<DataResponse<DrinkDailySugarStatisticsResponse>> retrieveUserDailySugarStatistics(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        final DrinkDailySugarStatisticsResponse response = drinkDailyStatisticsService.retrieveUserDailySugarStatistics(userDetails.getId());
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "유저가 하루 먹은 당 통계 조회 성공", response));
    }

    /**
     * 유저 주간 먹은 당 통계 정보 조회.
     */
    @SecurityRequirement(name = "accessToken")
    @Operation(summary = "주간 당 섭취량 통계 조회", description = "유저의 주간 당 통계를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "유저가 먹은 주간 당 통계 조회."))
    @GetMapping("/statistics/weekly")
    public ResponseEntity<DataResponse<DrinkWeeklySugarStatisticsResponse>> retrieveUserWeeklySugarStatistics(
            @RequestBody @Valid final DrinkWeeklySugarDateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        final DrinkWeeklySugarStatisticsResponse response = drinkWeeklyStatisticsService.retrieveUserWeeklySugarStatistics(userDetails.getId(), request);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "유저가 먹은 주간 당 통계 조회 성공", response));
    }

    /**
     *  음료 상세 조회
     *  추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "음료 상세 조회", description = "음료 상세 정보를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "음료 상세 조회."))
    @GetMapping("/detail")
    public ResponseEntity<DataResponse<DrinkDetailInfoResponse>> retrieveDrinkDetail(@RequestBody @Valid final DrinkInfoRequest request) {
        DrinkDetailInfoResponse response = drinkDetailSearchService.retrieveDrinkDetail(100L, request);
        return ResponseEntity.ok().body(DataResponse.of(HttpStatus.OK, "음료 상세 조회 성공", response));
    }

    @Operation(summary = "레디스에 저장된 음료 목록 조회하거나 없다면 생성합니다.", description = "레디스에 저장된 음료 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "레디스에 저장된 음료 목록 조회 성공"))
    @GetMapping("/redis")
    public ResponseEntity<DataResponse<List<DrinkRedisInfo>>> getOrCreateDrinkAtRedis() {
        final List<DrinkRedisInfo> drinks = drinkRedisService.getDrinks();
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "레디스에 저장된 음료 목록 조회 성공", drinks));
    }
}
