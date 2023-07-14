package com.depromeet.oversweet.domain.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 프랜차이즈 정보 dto
 */
@Getter
public class FranchiseInfo {

    @Schema(description = "프랜차이즈 ID", example = "1")
    private Long id;

    @Schema(description = "프랜차이즈 이름", example = "스타벅스")
    private String name;

    @Schema(description = "프랜차이즈 URL", example = "https://oversweet.s3~~")
    private String imageUrl;

    public FranchiseInfo(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
