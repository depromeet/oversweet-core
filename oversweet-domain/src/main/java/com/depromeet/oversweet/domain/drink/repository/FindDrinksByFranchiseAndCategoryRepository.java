package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;

import java.util.List;
import java.util.Map;

public interface FindDrinksByFranchiseAndCategoryRepository {

    Map<Long, List<DrinkEntity>> findDrinksByFranchiseAndCategoryAndDirection(Long franchiseId, String category, String column, String direction);

}
