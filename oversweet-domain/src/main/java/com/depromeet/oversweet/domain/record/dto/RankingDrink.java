package com.depromeet.oversweet.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

/**
 * 특정 프랜차이즈 기준, 가장 많이 기록된(인기 있는) 음료 정보
 */
@Getter
public class RankingDrink {

    private Long id;
    private String imageUrl;
    private String name;

    @QueryProjection
    public RankingDrink(Long id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

}
