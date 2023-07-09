package com.depromeet.oversweet.drink.dto.response;

import com.depromeet.oversweet.bookmark.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.repository.DrinkInfoWithScrapStatus;
import lombok.Getter;

import java.util.List;

/**
 * 상세 음료 정보를 응답하는 DTO
 */
@Getter
public class DrinkDetailInfoResponse {

    private final FranchiseInfo franchise;

    private final List<DrinkInfoWithScrapStatus> drinks;

    public DrinkDetailInfoResponse(FranchiseInfo franchise, List<DrinkInfoWithScrapStatus> drinks) {
        this.franchise = franchise;
        this.drinks = drinks;
    }
}
