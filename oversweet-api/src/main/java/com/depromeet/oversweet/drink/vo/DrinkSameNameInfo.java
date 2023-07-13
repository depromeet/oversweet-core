package com.depromeet.oversweet.drink.vo;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.drink.dto.response.DrinkAllInfoResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 음료 목록 중 같은 이름으로 존재하는 음료끼리 묶어주기
 */
public record DrinkSameNameInfo(
        List<DrinkEntity> drinkEntities
) {
    public List<DrinkAllInfoResponse> getSameNameDrinksSize(){
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
}
