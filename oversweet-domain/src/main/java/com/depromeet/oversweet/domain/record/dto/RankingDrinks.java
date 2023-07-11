package com.depromeet.oversweet.domain.record.dto;

import java.util.List;

import lombok.Getter;

/**
 * RankingDrink 의 일급 컬렉션
 */
@Getter
public class RankingDrinks {

    private final List<RankingDrink> rankingDrinks;

    public RankingDrinks(List<RankingDrink> rankingDrinks) {
        this.rankingDrinks = rankingDrinks;
    }

    public void markRanking() {
        for (int i = 0; i < rankingDrinks.size(); i++) {
            rankingDrinks.get(i).markRank(i + 1);
        }
    }
}
