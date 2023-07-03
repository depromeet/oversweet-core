package com.depromeet.oversweet.bookmark.dto.response;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 유저가 즐겨찾기 음료 정보 DTO
 */
@Getter
public class DrinkBookMarkedInfo {

    @Schema(description = "음료 ID", example = "1")
    private final Long id;

    @Schema(description = "음료 이름", example = "아메리카노")
    private final String name;

    @Schema(description = "음료 프랜차이즈 정보")
    private final FranchiseInfo franchise;

    @Schema(description = "음료 이미지 URL")
    private final String drinkImageUrl;

    @Schema(description = "음료 사이즈", example = "355")
    private final int size;

    @Schema(description = "음료 칼로리", example = "100")
    private final int calorie;

    @Schema(description = "음료 당도", example = "10")
    private final int sugar;

    public DrinkBookMarkedInfo(DrinkBookmarkEntity drinkBookmarkEntity) {
        this.id = drinkBookmarkEntity.getDrink().getId();
        this.name = drinkBookmarkEntity.getDrink().getName();
        this.franchise = new FranchiseInfo(drinkBookmarkEntity.getDrink().getFranchise());
        this.drinkImageUrl = drinkBookmarkEntity.getDrink().getImageUrl();
        this.size = drinkBookmarkEntity.getDrink().getSize();
        this.calorie = drinkBookmarkEntity.getDrink().getCalorie();
        this.sugar = drinkBookmarkEntity.getDrink().getSugar();
    }
}
