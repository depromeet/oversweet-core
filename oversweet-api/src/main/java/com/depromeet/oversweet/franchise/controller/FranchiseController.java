package com.depromeet.oversweet.franchise.controller;

import com.depromeet.oversweet.annotation.SecurityExclusion;
import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.franchise.dto.response.MostPopularDrinkResponse;
import com.depromeet.oversweet.franchise.service.FranchiseRedisService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.security.service.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.oversweet.franchise.service.FranchiseRetrieveService;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "프랜차이즈", description = "프랜차이즈 관련 API")
@RestController
@RequestMapping("/api/v1/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseRetrieveService franchiseRetrieveService;
    private final FranchiseRedisService franchiseRedisService;

    @Operation(summary = "레디스에 저장된 프랜차이즈 목록 조회하거나 없다면 생성합니다.", description = "레디스에 저장된 프랜차이즈 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "레디스에 저장된 프랜차이즈 목록 조회 성공"))
    @SecurityExclusion
    @GetMapping("/redis")
    public ResponseEntity<DataResponse<List<FranchiseInfo>>> getOrCreateFranchiseAtRedis() {
        final List<FranchiseInfo> franchise = franchiseRedisService.getFranchises();
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "레디스에 저장된 프랜차이즈 목록 조회 성공", franchise));
    }

    /**
     * 프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준)
     */
    @Operation(summary = "프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준)", description = "프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준) API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준) 성공"))
    @SecurityRequirement(name = "accessToken")
    @GetMapping("/popular/{franchiseId}")
    public ResponseEntity<DataResponse<MostPopularDrinkResponse>> getPopularDrinksByFranchiseId(@PathVariable @Parameter(description = "프랜차이즈 검색을 위한 키워드") final Long franchiseId,
                                                                                                @AuthenticationPrincipal CustomUserDetails customUserDetails)  {
            MostPopularDrinkResponse response = franchiseRetrieveService.searchPopularDrinkByFranchiseId(customUserDetails.getId(), franchiseId);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준) 성공", response));
    }
}
