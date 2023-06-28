package com.depromeet.oversweet.bookmark.dto;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

/**
 * 유저가 즐겨찾기 한 프랜차이즈 목록 응답 DTO
 */
@Getter
public class FranchiseBookMarkedResponseDto {


    @Schema(description = "즐겨찾기한 프랜차이즈 목록", example = "[]")
    private final List<FranchiseBookMarkedInfo> bookMarkedFranchises;

    public FranchiseBookMarkedResponseDto(List<FranchiseBookmarkEntity> bookMarks) {
        this.bookMarkedFranchises = bookMarks.stream()
                .map(FranchiseBookMarkedInfo::new)
                .toList();
    }
}
