package com.depromeet.oversweet.franchise.controller;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.franchise.service.FranchiseSearchService;
import com.depromeet.oversweet.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "프랜차이즈", description = "프랜차이즈 관련 API")
@RestController
@RequestMapping("/api/v1/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseSearchService franchiseSearchService;

    @Operation(summary = "검색 키워드로 해당하는 프랜차이즈 목록 조회", description = "프랜차이즈 검색 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "프랜차이즈 검색 성공"))
    @GetMapping("/search")
    public ResponseEntity<DataResponse<List<FranchiseInfo>>> getFranchiseByKeyword(@RequestParam @Parameter(description = "프랜차이즈 검색을 위한 키워드") final String keyword){
        final List<FranchiseInfo> response = franchiseSearchService.getFranchiseByKeyword(keyword);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "검색 키워드로 해당하는 프랜차이즈 목록 조회 성공", response));
    }
}
