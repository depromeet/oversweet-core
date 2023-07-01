package com.depromeet.oversweet.bookmark.dto.response;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 유저가 즐겨 찾기한 프랜차이즈 정보 DTO
 */
@Getter
public class FranchiseBookMarkedInfo {

    @Schema(description = "프랜차이즈 ID", example = "1")
    private final long id;

    @Schema(description = "프랜차이즈 이미지 URL")
    private final String imageUrl;

    @Schema(description = "프랜차이즈 이름", example = "스타벅스")
    private final String name;

    public FranchiseBookMarkedInfo(long id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public FranchiseBookMarkedInfo(FranchiseBookmarkEntity bookMark) {
        this(bookMark.getId(), bookMark.getFranchise().getImageUrl(), bookMark.getFranchise().getName());
    }
}
