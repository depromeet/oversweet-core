package com.depromeet.oversweet.search.service;


import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksByFranchiseAndCategoryRepository;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksSearchRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.search.vo.DrinkSameNameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * 해당 프랜차이즈의 음료 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkSearchService {

    private final FindFranchiseRepository findFranchiseRepository;
    private final FindDrinksSearchRepository findDrinksSearchRepository;
    private final FindDrinksByFranchiseAndCategoryRepository findDrinksByFranchiseAndCategoryRepository;

    public List<DrinkAllInfoResponse> getDrinksByKeywordAndFranchise(final Long franchiseId, final String keyword) {
        // 프랜차이즈 존재 여부 확인
        final FranchiseEntity findFranchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 해당 프랜차이즈의 키워드로 검색 되는 음료 검색
        final List<DrinkEntity> findDrinks = findDrinksSearchRepository.findDrinksByFranchiseAndKeyword(findFranchise.getId(), keyword);

        // 키워드로 검색된 음료 목록 중 이름이 같은 것들로 묶어주기
        // 묶은 목록 중 IsMiniMun 찾기
        final DrinkSameNameInfo sameNameInfo = new DrinkSameNameInfo(findDrinks);

        return sameNameInfo.getSameNameDrinksSize();
    }

    public List<DrinkAllInfoResponse> getDrinksByFranchiseAndCategoryAndDirection(Long franchiseId, String category, String column, String direction) {
        //franchiseId 별로 List<DrinkEntity> 를 묶는다. 이유는 다른 프랜차이즈이지만 음료 이름이 같으면 중복으로 나오기 때문이다.
        Map<Long, List<DrinkEntity>> drinkEntitiesWithFranchise = findDrinksByFranchiseAndCategoryRepository.findDrinksByFranchiseAndCategoryAndDirection(franchiseId, category, column, direction);

        //프랜차이즈별 && 음료명 별로 묶는다.
        Map<Long, Map<String, List<DrinkEntity>>> drinkEntitiesWithFranchiseGroupByName = drinkEntitiesWithFranchise
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .collect(groupingBy(DrinkEntity::getName))
                ));

        List<DrinkAllInfoResponse> drinkAllInfoResponses = new ArrayList<>();
        drinkEntitiesWithFranchiseGroupByName.entrySet().stream()
                .forEach(entry -> {
                    entry.getValue().entrySet().stream()
                            .forEach(innerEntry -> {
                                //프랜차이즈별 && 음료명별 안에서 가장 작은 사이즈의 음료와 그 음료가 가지는 사이즈 list 를 찾아서 넣어준다.
                                List<DrinkEntity> drinkEntities = innerEntry.getValue();

                                Optional<DrinkEntity> minimumDrinkEntityOptional = drinkEntities.stream().filter(DrinkEntity::getIsMinimum).findFirst();
                                if (minimumDrinkEntityOptional.isPresent()) {
                                    List<Integer> sizes = drinkEntities.stream().map(DrinkEntity::getSize).sorted().toList();

                                    DrinkAllInfoResponse drinkAllInfoResponse = DrinkAllInfoResponse.of(minimumDrinkEntityOptional.get(), sizes);
                                    drinkAllInfoResponses.add(drinkAllInfoResponse);
                                }
                            });
                });

        return sortByColumnAndDirection(drinkAllInfoResponses, column, direction);
    }

    private List<DrinkAllInfoResponse> sortByColumnAndDirection(List<DrinkAllInfoResponse> drinkAllInfoResponses, String column, String direction) {
        switch (column) {
            case "sugar":
                if (direction.equalsIgnoreCase("desc")) {
                    return drinkAllInfoResponses.stream()
                            .sorted(Comparator.comparing(DrinkAllInfoResponse::sugar).reversed())
                            .toList();
                }

                return drinkAllInfoResponses.stream()
                        .sorted(Comparator.comparing(DrinkAllInfoResponse::sugar))
                        .toList();
            default:
                return drinkAllInfoResponses;
        }
    }

}
