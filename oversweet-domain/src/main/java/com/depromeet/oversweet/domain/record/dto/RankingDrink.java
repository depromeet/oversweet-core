package com.depromeet.oversweet.domain.record.dto;

import lombok.Getter;

/**
 * 특정 프랜차이즈 기준, 가장 많이 기록된(인기 있는) 음료 정보
 */
@Getter
public class RankingDrink {

    private Long id;
    private String imageUrl;
    private String name;
    private Long count;
    private int rank;

    public RankingDrink(Long id, String imageUrl, String name, Long count) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.count = count;
    }


    public void markRank(int rank) {
        this.rank = rank;
    }
}
