package com.depromeet.oversweet.drink.dto;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 프랜차이즈 정보 DTO
 */
public record FranchiseInfo(

        @Schema(description = "프랜차이즈 ID", example = "1") Long id,

        @Schema(description = "프랜차이즈 이름", example = "스타벅스") String name
) {
    public static FranchiseInfo of(final FranchiseEntity franchise){
        return new FranchiseInfo(
                franchise.getId(),
                franchise.getName()
        );
    }
}
