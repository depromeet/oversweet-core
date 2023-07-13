package com.depromeet.oversweet.search.dto.response;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 해당 키워드에 매칭되는 음료 목록 및 프랜이즈 목록 DTO
 */
public record SearchInfoResponse(
        @Schema(description = "프랜차이즈 정보") List<FranchiseInfo> franchise,
        @Schema(description = "음료 목록 정보") List<DrinkAllInfoResponse> drinks
        ) {
}
