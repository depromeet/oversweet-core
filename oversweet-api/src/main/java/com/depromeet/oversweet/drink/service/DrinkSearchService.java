package com.depromeet.oversweet.drink.service;


import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksByFranchiseAndCategoryRepository;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksByFranchiseAndKeywordRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.drink.vo.DrinkSameNameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 해당 프랜차이즈의 음료 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkSearchService {

    private final FindFranchiseRepository findFranchiseRepository;
    private final FindDrinksByFranchiseAndKeywordRepository findDrinksByFranchiseAndKeywordRepository;
    private final FindDrinksByFranchiseAndCategoryRepository findDrinksByFranchiseAndCategoryRepository;

    public List<DrinkAllInfoResponse> getDrinksByKeywordAndFranchise(final Long franchiseId, final String keyword) {
        // 프랜차이즈 존재 여부 확인
        final FranchiseEntity findFranchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 해당 프랜차이즈의 키워드로 검색 되는 음료 검색
        final List<DrinkEntity> findDrinks = findDrinksByFranchiseAndKeywordRepository.findDrinksByFranchiseAndKeyword(findFranchise.getId(), keyword);

        // 키워드로 검색된 음료 목록 중 이름이 같은 것들로 묶어주기
        // 묶은 목록 중 IsMiniMun 찾기
        final DrinkSameNameInfo sameNameInfo = new DrinkSameNameInfo(findDrinks);

        return sameNameInfo.getSameNameDrinksSize();
    }

    public List<DrinkAllInfoResponse> getDrinksByFranchiseAndCategoryAndDirection(Long franchiseId, String category, String column, String direction) {
        // 먼저, franchiseId 별로 List<DrinkEntity> 를 묶는다. 이유는 다른 프랜차이즈이지만 음료 이름이 같으면 중복으로 나오기 때문이다.
        Map<Long, List<DrinkEntity>> drinkEntitiesWithFranchise = findDrinksByFranchiseAndCategoryRepository.findDrinksByFranchiseAndCategoryAndDirection(franchiseId, category, column, direction);

        // 프랜차이즈별 && 음료명 별로 묶는다.
        Map<Long, Map<String, List<DrinkEntity>>> drinkEntitiesWithFranchiseGroupByName = drinkEntitiesWithFranchise
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .collect(Collectors.groupingBy(DrinkEntity::getName))
                ));

        List<DrinkAllInfoResponse> drinkAllInfoResponses = new ArrayList<>();
        drinkEntitiesWithFranchiseGroupByName.entrySet().stream()
                .forEach(entry -> {
                    entry.getValue().entrySet().stream()
                            .forEach(innerEntry -> {
                                //프랜차이즈별 && 음료명별 안에서 가장 작은 사이즈의 음료와 그 음료가 가지는 사이즈 list 를 찾아서 넣어준다.
                                List<DrinkEntity> drinkEntities = innerEntry.getValue();

                                DrinkEntity minimumDrinkEntity = drinkEntities.stream().filter(DrinkEntity::getIsMinimum).findFirst().get();
                                List<Integer> sizes = drinkEntities.stream().map(DrinkEntity::getSize).toList();

                                DrinkAllInfoResponse drinkAllInfoResponse = DrinkAllInfoResponse.of(minimumDrinkEntity, sizes);
                                drinkAllInfoResponses.add(drinkAllInfoResponse);
                            });
                });

        return drinkAllInfoResponses;
    }

}
