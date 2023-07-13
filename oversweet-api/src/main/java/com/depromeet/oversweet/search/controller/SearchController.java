package com.depromeet.oversweet.search.controller;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.search.dto.response.SearchInfoResponse;
import com.depromeet.oversweet.search.service.DrinkSearchService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.search.service.FranchiseSearchService;
import com.depromeet.oversweet.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "검색", description = "검색 관련 API")
@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final DrinkSearchService drinkSearchService;
    private final FranchiseSearchService franchiseSearchService;
    private final SearchService searchService;

    @Operation(summary = "해당 프랜차이즈의 음료 목록을 키워드로 조회합니다.", description = "해당 프랜차이즈의 키워드에 매칭되는 음료 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "해당 프랜차이즈의 키워드에 매칭되는 음료 조회 성공"))
    @GetMapping("/{franchiseId}")
    public ResponseEntity<DataResponse<List<DrinkAllInfoResponse>>> getDrinksByKeywordAndFranchise(
            @PathVariable @Parameter(description = "프랜차이즈 Id") final Long franchiseId,
            @RequestParam @Parameter(description = "프랜차이즈 검색을 위한 키워드") final String keyword){
        final List<DrinkAllInfoResponse> drinks = drinkSearchService.getDrinksByKeywordAndFranchise(franchiseId, keyword);
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "해당 프랜차이즈의 키워드에 매칭되는 음료 조회 성공", drinks));
    }


    @Operation(summary = "검색 키워드로 해당하는 프랜차이즈 목록 조회", description = "프랜차이즈 검색 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "프랜차이즈 검색 성공"))
    @GetMapping("/franchise")
    public ResponseEntity<DataResponse<List<FranchiseInfo>>> getFranchiseByKeyword(@RequestParam @Parameter(description = "프랜차이즈 검색을 위한 키워드") final String keyword) {
        final List<FranchiseInfo> response = franchiseSearchService.getFranchiseByKeyword(keyword);
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "검색 키워드로 해당하는 프랜차이즈 목록 조회 성공", response));
    }

    @Operation(summary = "검색 키워드를 통해 해당하는 프랜차이즈 목록과 음료 목록을 조회합니다.", description = "해당 키워드에 매칭되는 프랜차이즈 목록과 음룍 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "해당 키워드에 매칭되는 프랜차이즈 목록 및 음료 목록 조회"))
    @GetMapping
    public ResponseEntity<DataResponse<SearchInfoResponse>> getSearchInfoByKeyword(@RequestParam @Parameter(description = "검색을 위한 키워드") final String keyword){
        final SearchInfoResponse response = searchService.getSearchInfoByKeyword(keyword);
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "검색 키워드를 통해 해당하는 프랜차이즈 목록좌 음료 목록 조회 성공", response));
    }
}
