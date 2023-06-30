package com.depromeet.oversweet.bookmark.controller;

import com.depromeet.oversweet.bookmark.dto.response.FranchiseBookMarkedResponseDto;
import com.depromeet.oversweet.bookmark.service.FranchiseBookMarkSearchService;
import com.depromeet.oversweet.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "즐겨찾기", description = "즐겨찾기 관련 API")
@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final FranchiseBookMarkSearchService franchiseBookMarkSearchService;


    /**
     * 유저가 즐겨 찾기한 프랜차이즈 목록 조회
     * 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "즐겨 찾기한 프랜차이즈 목록 조회", description = "유저가 즐겨 찾기한 프랜차이즈 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "즐겨 찾기한 프랜차이즈 목록을 조회 성공")
    })
    @GetMapping("/franchises")
    public ResponseEntity<DataResponse<FranchiseBookMarkedResponseDto>> searchFranchiseBookMarked() {
        FranchiseBookMarkedResponseDto responseDto = franchiseBookMarkSearchService.searchFranchiseBookMarked(100L);
        return ResponseEntity.ok(DataResponse.of(OK, "즐겨 찾기한 프랜차이즈 목록 조회 성공", responseDto));
    }

}
