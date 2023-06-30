package com.depromeet.oversweet.bookmark.dto.response;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/***
 * 프랜차이즈 정보 DTO
 */
@Getter
public class FranchiseInfo {

    @Schema(description = "프랜차이즈 ID", example = "1")
    private final Long id;

    @Schema(description = "프랜차이즈 이름", example = "스타벅스")
    private final String name;

    public FranchiseInfo(FranchiseEntity franchiseEntity) {
        this.id = franchiseEntity.getId();
        this.name = franchiseEntity.getName();
    }
}
