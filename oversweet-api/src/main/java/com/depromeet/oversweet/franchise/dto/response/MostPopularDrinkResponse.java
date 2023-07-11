package com.depromeet.oversweet.franchise.dto.response;

import java.util.List;

import com.depromeet.oversweet.domain.record.dto.RankingDrink;

import lombok.Getter;

/**
 * 해당 프랜차이즈의 인기 음료 조회 응답 DTO
 */
@Getter
public class MostPopularDrinkResponse {
    private final List<RankingDrink> rankingDrinks;
    private final FranchiseInfoWithScrapStatus franchise;

    public MostPopularDrinkResponse(List<RankingDrink> rankingDrinks, FranchiseInfoWithScrapStatus franchise) {
        this.rankingDrinks = rankingDrinks;
        this.franchise = franchise;
    }

}
