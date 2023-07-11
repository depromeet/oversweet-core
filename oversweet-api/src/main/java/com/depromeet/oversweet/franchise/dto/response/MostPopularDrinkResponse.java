package com.depromeet.oversweet.franchise.dto.response;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.depromeet.oversweet.domain.record.dto.RankingDrink;

import lombok.Getter;

/**
 * 해당 프랜차이즈의 인기 음료 조회 응답 DTO
 */
@Getter
public class MostPopularDrinkResponse {
    private static final int ZERO = 0;
    private final Map<Integer, RankingDrink> rankingDrinks;
    private final FranchiseInfoWithScrapStatus franchise;

    private MostPopularDrinkResponse(Map<Integer, RankingDrink> rankingDrinks, FranchiseInfoWithScrapStatus franchise) {
        this.rankingDrinks = rankingDrinks;
        this.franchise = franchise;
    }

    public static MostPopularDrinkResponse of(List<RankingDrink> rankingDrinks, FranchiseInfoWithScrapStatus franchise) {
        return new MostPopularDrinkResponse(covertFrom(rankingDrinks), franchise);
    }

    private static Map<Integer, RankingDrink> covertFrom(List<RankingDrink> rankingDrinks) {
        return IntStream.range(ZERO, rankingDrinks.size())
                .boxed()
                .collect(toMap(i -> i + 1, rankingDrinks::get));
    }

}
