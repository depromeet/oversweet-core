package com.depromeet.oversweet.search.service;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksSearchRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseSearchRepository;
import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.search.dto.response.SearchInfoResponse;
import com.depromeet.oversweet.search.vo.DrinkSameNameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final FindFranchiseSearchRepository findFranchiseSearchRepository;
    private final FindDrinksSearchRepository findDrinksSearchRepository;

    public SearchInfoResponse getSearchInfoByKeyword(final String keyword){

        // 키워드로 검색 되는 프랜차이즈 검색
        final List<FranchiseEntity> findFranchises = findFranchiseSearchRepository.findFranchiseByKeyword(keyword);

        final List<FranchiseInfo> franchiseInfos = findFranchises.stream()
                .map(FranchiseInfo::of)
                .toList();

        // 키워드로 검색 되는 음료 검색
        final List<DrinkEntity> findDrinkEntities = findDrinksSearchRepository.findDrinksByKeyword(keyword);

        // 키워드로 검색된 음료 목록 중 이름이 같은 것들로 묶어주기
        // 묶은 목록 중 IsMiniMun 찾기
        final DrinkSameNameInfo sameNameInfo = new DrinkSameNameInfo(findDrinkEntities);

        final List<DrinkAllInfoResponse> drinkInfos = sameNameInfo.getSameNameDrinksGroupByFranchise();

        return new SearchInfoResponse(franchiseInfos, drinkInfos);
    }
}
