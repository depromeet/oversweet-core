package com.depromeet.oversweet.drink.controller;


import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;

import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.search.service.DrinkSearchService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.oversweet.annotation.SecurityExclusion;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkDetailInfoResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkRecommendResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkRedisInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarStatisticsResponse;
import com.depromeet.oversweet.drink.service.DrinkDailyStatisticsService;
import com.depromeet.oversweet.drink.service.DrinkDetailSearchService;
import com.depromeet.oversweet.drink.service.DrinkRecommendService;
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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Tag(name = "음료", description = "음료 관련 API")
@RestController
@RequestMapping("/api/v1/drinks")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkDailyStatisticsService drinkDailyStatisticsService;
    private final DrinkWeeklyStatisticsService drinkWeeklyStatisticsService;
    private final DrinkDetailSearchService drinkDetailSearchService;
    private final DrinkRedisService drinkRedisService;
    private final DrinkRecommendService drinkRecommendService;
    private final DrinkSearchService drinkSearchService;

    /**
     * 유저 하루(데일리) 먹은 당 통계 및 음료 목록 조회.
     */
    @SecurityRequirement(name = "accessToken")
    @Operation(summary = "하루 당 섭취량 통계 조회", description = "유저가 하루 먹은 당 통계를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "유저가 하루 먹은 당 통계 조회."))
    @SecurityRequirement(name = "accessToken")
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
    @SecurityRequirement(name = "accessToken")
    @GetMapping("/statistics/weekly")
    public ResponseEntity<DataResponse<DrinkWeeklySugarStatisticsResponse>> retrieveUserWeeklySugarStatistics(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        final DrinkWeeklySugarStatisticsResponse response = drinkWeeklyStatisticsService.retrieveUserWeeklySugarStatistics(userDetails.getId(), startDate, endDate);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "유저가 먹은 주간 당 통계 조회 성공", response));
    }

    /**
     * 음료 상세 조회
     */
    @Operation(summary = "음료 상세 조회", description = "음료 상세 정보를 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "음료 상세 조회."))
    @SecurityRequirement(name = "accessToken")
    @GetMapping("/detail")
    public ResponseEntity<DataResponse<DrinkDetailInfoResponse>> retrieveDrinkDetail(
            @RequestParam @Valid @NotNull(message = "필수 값 입니다.") Long franchiseId,
            @RequestParam @Valid @NotEmpty(message = "필수 값 입니다.") String drinkName,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        DrinkDetailInfoResponse response = drinkDetailSearchService.retrieveDrinkDetail(userDetails.getId(), franchiseId, drinkName);
        return ResponseEntity.ok().body(DataResponse.of(HttpStatus.OK, "음료 상세 조회 성공", response));
    }

    @Operation(summary = "레디스에 저장된 음료 목록 조회하거나 없다면 생성합니다.", description = "레디스에 저장된 음료 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "레디스에 저장된 음료 목록 조회 성공"))
    @SecurityExclusion
    @GetMapping("/redis")
    public ResponseEntity<DataResponse<List<DrinkRedisInfo>>> getOrCreateDrinkAtRedis() {
        final List<DrinkRedisInfo> drinks = drinkRedisService.getDrinks();
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "레디스에 저장된 음료 목록 조회 성공", drinks));
    }


    /**
     * 음료 사이즈 기준으로 당 성분이 비슷한 음료 추천
     */
    @Operation(summary = "음료 사이즈 기준으로 당 성분이 비슷한 음료 추천", description = "음료 사이즈 기준으로 당 성분이 비슷한 음료 추천")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "음료 사이즈 기준으로 당 성분이 비슷한 음료 추천"))
    @SecurityExclusion
    @GetMapping("/recommend/{drinkId}")
    public ResponseEntity<DataResponse<DrinkRecommendResponse>> recommendDrink(@PathVariable Long drinkId) {
        DrinkRecommendResponse response = drinkRecommendService.recommendDrink(drinkId);
        return ResponseEntity.ok().body(DataResponse.of(OK, "음료 사이즈 기준으로 당 성분이 비슷한 음료 추천 성공", response));
    }

    /**
     * 음료 목록 조회 - 필터링(프랜차이즈, 음료 카테고리, 정렬)
     */
    @Operation(summary = "프랜차이즈, 음료 카테고리, 정렬 조건에 따라 음료 목록을 조회합니다.", description = "조건에 따라 음료 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "조건에 따라 음료 목록 조회 성공"))
    @SecurityExclusion
    @GetMapping("/list")
    public ResponseEntity<DataResponse<List<DrinkAllInfoResponse>>> getDrinksByFranchiseAndCategoryWithPagination(
            @RequestParam(required = false) @Parameter(description = "프랜차이즈 Id") final Long franchiseId,
            @RequestParam(required = false) @Parameter(description = "음료 카테고리(ex: AMERICANO, LATTE)") final String category,
            @RequestParam @Parameter(description = "정렬 기준 컬럼(ex: sugar)") final String column,
            @RequestParam @Parameter(description = "DESC or ASC") final String direction
    ) {
        List<DrinkAllInfoResponse> drinkAllSearchInfos = drinkSearchService.getDrinksByFranchiseAndCategoryAndDirection(franchiseId, category, column, direction);
        return ResponseEntity.ok().body(DataResponse.of(OK, "조건에 따라 음료 목록 조회 성공", drinkAllSearchInfos));
    }
}
