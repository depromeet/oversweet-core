package com.depromeet.oversweet.bookmark.controller;

import com.depromeet.oversweet.bookmark.dto.response.DrinkBookMarkedResponseDto;
import com.depromeet.oversweet.bookmark.dto.response.FranchiseBookMarkedResponseDto;
import com.depromeet.oversweet.bookmark.service.DrinkBookMarkRegisterService;
import com.depromeet.oversweet.bookmark.service.DrinkBookMarkSearchService;
import com.depromeet.oversweet.bookmark.service.FranchiseBookMarkRegisterService;
import com.depromeet.oversweet.bookmark.service.FranchiseBookMarkSearchService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.response.MessageResponse;
import com.depromeet.oversweet.security.service.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @Operation(summary = "즐겨 찾기한 프랜차이즈 목록 조회", description = "유저가 즐겨 찾기한 프랜차이즈 목록을 조회한다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "즐겨 찾기한 프랜차이즈 목록을 조회 성공")})
    @SecurityRequirement(name = "accessToken")
    @GetMapping("/franchises")
    public ResponseEntity<DataResponse<FranchiseBookMarkedResponseDto>> searchFranchiseBookMarked(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        FranchiseBookMarkedResponseDto responseDto = franchiseBookMarkSearchService.searchFranchiseBookMarked(userDetails.getId());
        return ResponseEntity.ok(DataResponse.of(OK, "즐겨 찾기한 프랜차이즈 목록 조회 성공", responseDto));
    }


    /**
     * 유저가 즐겨 찾기한 음료 목록 조회
     */
    @Operation(summary = "즐겨 찾기한 음료 목록 조회", description = "유저가 즐겨 찾기한 음료 목록을 조회한다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "즐겨 찾기한 음료 목록을 조회 성공")})
    @SecurityRequirement(name = "accessToken")
    @GetMapping("/drinks")
    public ResponseEntity<DataResponse<DrinkBookMarkedResponseDto>> searchDrinkBookMarked(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        DrinkBookMarkedResponseDto responseDto = drinkBookMarkSearchService.searchDrinkBookMarked(userDetails.getId());
        return ResponseEntity.ok(DataResponse.of(OK, "즐겨 찾기한 음료 목록 조회 성공", responseDto));
    }


    /**
     * 유저가 특정 프랜차이즈를 즐겨 찾기 할 수 있다.
     */
    @Operation(summary = "프랜차이즈 즐겨 찾기", description = "유저가 특정 프랜차이즈를 즐겨 찾기 할 수 있다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "프랜차이즈 즐겨 찾기 성공")})
    @SecurityRequirement(name = "accessToken")
    @PostMapping("/franchises/{franchiseId}")
    public ResponseEntity<MessageResponse> markFranchiseAsBookMark(
            @PathVariable @Parameter(description = "프랜차이즈 고유 Id") Long franchiseId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        franchiseBookMarkRegisterService.register(userDetails.getId(), franchiseId);
        return ResponseEntity.ok(MessageResponse.of(OK, "프랜차이즈 즐겨 찾기 등록 성공"));
    }

    /**
     * 유저가 특정 음료를 즐겨 찾기 할 수 있다.
     */
    @Operation(summary = "음료 즐겨 찾기", description = "유저가 특정 음료를 즐겨 찾기 할 수 있다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "음료 즐겨 찾기 성공")})
    @SecurityRequirement(name = "accessToken")
    @PostMapping("/drinks/{drinkId}")
    public ResponseEntity<MessageResponse> markDrinkAsBookMark(
            @PathVariable @Parameter(description = "음료 고유 Id") Long drinkId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        drinkBookMarkRegisterService.register(userDetails.getId(), drinkId);
        return ResponseEntity.ok(MessageResponse.of(OK, "음료 즐겨 찾기 등록 성공"));
    }

    /**
     * 유저가 특정 프랜차이즈를 즐겨 찾기 해제 할 수 있다.
     */
    @Operation(summary = "프랜차이즈 즐겨 찾기 해제", description = "유저가 특정 프랜차이즈를 즐겨 찾기 해제 할 수 있다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "프랜차이즈 즐겨 찾기 해제 성공")})
    @SecurityRequirement(name = "accessToken")
    @DeleteMapping("/franchises/{franchiseId}")
    public ResponseEntity<DataResponse<FranchiseBookMarkedResponseDto>> unMarkFranchiseAsBookMark(
            @PathVariable @Parameter(description = "프랜차이즈 고유 Id") Long franchiseId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        FranchiseBookMarkedResponseDto response = franchiseBookMarkRegisterService.unregister(userDetails.getId(), franchiseId);
        return ResponseEntity.ok(DataResponse.of(OK, "프랜차이즈 즐겨 찾기 해제 성공", response));
    }

    /**
     * 유저가 특정 음료를 즐겨 찾기 해제 할 수 있다.
     */
    @Operation(summary = "음료 즐겨 찾기 해제", description = "유저가 특정 음료를 즐겨 찾기 해제 할 수 있다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "음료 즐겨 찾기 해제 성공")})
    @SecurityRequirement(name = "accessToken")
    @DeleteMapping("/drinks/{drinkId}")
    public ResponseEntity<DataResponse<DrinkBookMarkedResponseDto>> unMarkDrinkAsBookMark(
            @PathVariable @Parameter(description = "음료 고유 Id") Long drinkId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        DrinkBookMarkedResponseDto response = drinkBookMarkRegisterService.unregister(userDetails.getId(), drinkId);
        return ResponseEntity.ok(DataResponse.of(OK, "음료 즐겨 찾기 해제 성공", response));
    }

}
