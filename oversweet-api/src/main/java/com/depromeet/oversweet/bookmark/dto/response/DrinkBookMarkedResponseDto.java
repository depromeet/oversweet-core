package com.depromeet.oversweet.bookmark.dto.response;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

/**
 * 유저가 즐겨찾기한 음료 목록 조회 응답 DTO
 */
@Getter
public class DrinkBookMarkedResponseDto {

    @Schema(description = "즐겨찾기 음료 리스트")
    private final List<DrinkBookMarkedInfo> bookMarkedDrinks;

    public DrinkBookMarkedResponseDto(List<DrinkBookmarkEntity> bookmarks) {
        this.bookMarkedDrinks = bookmarks.stream()
                .map(DrinkBookMarkedInfo::new)
                .toList();
    }
}
