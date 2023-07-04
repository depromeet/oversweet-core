package com.depromeet.oversweet.bookmark.controller;

import com.depromeet.oversweet.bookmark.dto.response.DrinkBookMarkedResponseDto;
import com.depromeet.oversweet.bookmark.dto.response.FranchiseBookMarkedResponseDto;
import com.depromeet.oversweet.bookmark.service.DrinkBookMarkRegisterService;
import com.depromeet.oversweet.bookmark.service.DrinkBookMarkSearchService;
import com.depromeet.oversweet.bookmark.service.FranchiseBookMarkRegisterService;
import com.depromeet.oversweet.bookmark.service.FranchiseBookMarkSearchService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "즐겨찾기", description = "즐겨찾기 관련 API")
@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final FranchiseBookMarkSearchService franchiseBookMarkSearchService;
    private final DrinkBookMarkSearchService drinkBookMarkSearchService;
    private final FranchiseBookMarkRegisterService franchiseBookMarkRegisterService;
    private final DrinkBookMarkRegisterService drinkBookMarkRegisterService;


    /**
     * 유저가 즐겨 찾기한 프랜차이즈 목록 조회
     * TODO : 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
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


    /**
     * 유저가 즐겨 찾기한 음료 목록 조회
     * TODO : 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "즐겨 찾기한 음료 목록 조회", description = "유저가 즐겨 찾기한 음료 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "즐겨 찾기한 음료 목록을 조회 성공")
    })
    @GetMapping("/drinks")
    public ResponseEntity<DataResponse<DrinkBookMarkedResponseDto>> searchDrinkBookMarked() {
        DrinkBookMarkedResponseDto responseDto = drinkBookMarkSearchService.searchDrinkBookMarked(100L);
        return ResponseEntity.ok(DataResponse.of(OK, "즐겨 찾기한 음료 목록 조회 성공", responseDto));
    }


    /**
     * 유저가 특정 프랜차이즈를 즐겨 찾기 할 수 있다.
     * TODO : 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "프랜차이즈 즐겨 찾기", description = "유저가 특정 프랜차이즈를 즐겨 찾기 할 수 있다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "프랜차이즈 즐겨 찾기 성공")
    })
    @PostMapping("/franchises/{franchiseId}")
    public ResponseEntity<MessageResponse> markFranchiseAsBookMark(@PathVariable @Parameter(description = "프랜차이즈 고유 Id") Long franchiseId) {
        franchiseBookMarkRegisterService.register(100L, franchiseId);
        return ResponseEntity.ok(MessageResponse.of(OK, "프랜차이즈 즐겨 찾기 등록 성공"));
    }

    /**
     * 유저가 특정 음료를 즐겨 찾기 할 수 있다.
     * TODO : 추후 로그인 기능 구현 후, 로그인한 유저의 ID를 받아와야 함 (ex. @AuthenticationPrincipal User user)
     */
    @Operation(summary = "음료 즐겨 찾기", description = "유저가 특정 음료를 즐겨 찾기 할 수 있다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "음료 즐겨 찾기 성공")
    })
    @PostMapping("/drinks/{drinkId}")
    public ResponseEntity<MessageResponse> markDrinkAsBookMark(@PathVariable @Parameter(description = "음료 고유 Id") Long drinkId) {
        drinkBookMarkRegisterService.register(100L, drinkId);
        return ResponseEntity.ok(MessageResponse.of(OK, "음료 즐겨 찾기 등록 성공"));
    }

}
