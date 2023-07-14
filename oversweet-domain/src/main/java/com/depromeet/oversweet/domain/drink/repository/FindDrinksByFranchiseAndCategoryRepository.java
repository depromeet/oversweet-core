package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.dto.DrinkSearchInfo;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindDrinksByFranchiseAndCategoryRepository {

    List<DrinkEntity> findDrinksByFranchiseAndCategory(Long franchiseId, String category, Pageable pageable);

    Page<DrinkSearchInfo> getDrinksByFranchiseAndCategoryWithPagination(List<DrinkSearchInfo> drinkSearchInfos, Long franchiseId, String category, Pageable pageable);
}
