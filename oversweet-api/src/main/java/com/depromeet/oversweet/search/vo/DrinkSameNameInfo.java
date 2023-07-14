package com.depromeet.oversweet.search.vo;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 음료 목록 중 같은 이름으로 존재하는 음료끼리 묶어주기
 */
public record DrinkSameNameInfo(
        List<DrinkEntity> drinkEntities
) {
    public List<DrinkAllInfoResponse> getSameNameDrinksSize() {
        final Map<String, List<DrinkEntity>> groupedDrinks = drinkEntities.stream()
                .collect(Collectors.groupingBy(DrinkEntity::getName));

        final Map<String, List<Integer>> getDrinksSizes = groupedDrinks.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(DrinkEntity::getSize)
                                .toList()
                ));

        final Map<String, DrinkEntity> filteredDrinks = groupedDrinks.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(DrinkEntity::getIsMinimum)
                .collect(Collectors.toMap(
                        DrinkEntity::getName,
                        drink -> drink
                ));

        return filteredDrinks.values().stream()
                .map(value -> DrinkAllInfoResponse.of(value, getDrinksSizes.get(value.getName())))
                .toList();
    }

    public List<DrinkAllInfoResponse> getSameNameDrinksGroupByFranchise() {
        Map<FranchiseEntity, Map<String, List<DrinkEntity>>> groupedDrinksByFranchise = drinkEntities.stream()
                .collect(Collectors.groupingBy(DrinkEntity::getFranchise,
                        Collectors.groupingBy(DrinkEntity::getName)));

        Map<FranchiseEntity, Map<String, List<Integer>>> groupedDrinksSizesByFranchise = groupedDrinksByFranchise.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        drinkEntry -> drinkEntry.getValue().stream()
                                                .map(DrinkEntity::getSize)
                                                .collect(Collectors.toList())))));

        Map<FranchiseEntity, Map<String, DrinkEntity>> filteredDrinksByFranchise = groupedDrinksByFranchise.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .flatMap(drinkEntry -> drinkEntry.getValue().stream())
                                .filter(DrinkEntity::getIsMinimum)
                                .collect(Collectors.toMap(DrinkEntity::getName, drink -> drink))));

        return filteredDrinksByFranchise.values().stream()
                .flatMap(map -> map.values().stream())
                .map(value -> DrinkAllInfoResponse.of(value, groupedDrinksSizesByFranchise.get(value.getFranchise()).get(value.getName())))
                .toList();
    }

}
