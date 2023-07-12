package com.depromeet.oversweet.drink.service;


import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksByFranchiseAndKeywordRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.drink.vo.DrinkSameNameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 해당 프랜차이즈의 음료 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkSearchService {

    private final FindFranchiseRepository findFranchiseRepository;
    private final FindDrinksByFranchiseAndKeywordRepository findDrinksByFranchiseAndKeywordRepository;

    public List<DrinkAllInfoResponse> getDrinksByKeywordAndFranchise(final Long franchiseId, final String keyword) {
        // 프랜차이즈 존재 여부 확인
        final FranchiseEntity findFranchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 해당 프랜차이즈의 키워드로 검색 되는 음료 검색
        final List<DrinkEntity> findDrinks = findDrinksByFranchiseAndKeywordRepository.FindDrinksByFranchiseAndKeyword(findFranchise.getId(), keyword);

        // 키워드로 검색된 음료 목록 중 이름이 같은 것들로 묶어주기
        // 묶은 목록 중 IsMiniMun 찾기
        final DrinkSameNameInfo sameNameInfo = new DrinkSameNameInfo(findDrinks);
        final List<DrinkAllInfoResponse> response = sameNameInfo.getSameNameDrinksSize();

        return response;
    }
}
