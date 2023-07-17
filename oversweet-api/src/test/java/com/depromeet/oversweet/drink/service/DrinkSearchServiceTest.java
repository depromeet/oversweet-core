package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.common.TestSetup;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinksByFranchiseAndCategoryRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.search.dto.response.DrinkAllInfoResponse;
import com.depromeet.oversweet.search.service.DrinkSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("음료 데일리(하루) 통계 테스트")
@MockitoSettings
class DrinkSearchServiceTest {

    @Mock
    FindDrinksByFranchiseAndCategoryRepository findDrinksByFranchiseAndCategoryRepository;
    @InjectMocks
    DrinkSearchService drinkSearchService;

    FranchiseEntity firstFranchiseEntity;
    FranchiseEntity secondFranchiseEntity;
    DrinkEntity firstDrinkEntity;
    DrinkEntity secondDrinkEntity;
    DrinkEntity thirdDrinkEntity;
    DrinkEntity fourthDrinkEntity;

    @BeforeEach
    void setUp() {
        TestSetup testSetup = new TestSetup();
        testSetup.setUp();

        firstFranchiseEntity = testSetup.getFirstFranchise();
        secondFranchiseEntity = testSetup.getSecondFranchise();

        firstDrinkEntity = testSetup.getFirstDrink();       //스타벅스 아메리카노 355ml / 당 10 / isMinimum
        secondDrinkEntity = testSetup.getSecondDrink();     //스타벅스 카페라떼 355 / 당 30 / isMinimum
        thirdDrinkEntity = testSetup.getThirdDrink();       //스타벅스 아메리카노 475 / 당 20
        fourthDrinkEntity = testSetup.getFourthDrink();     //이디야 아메리카노 600 / 당 30 / isMinimum
    }

    @Test
    void 스타벅스_아메리카노_당_낮은순_조회() {
        //given
        Long franchiseId = firstFranchiseEntity.getId();
        String category = firstDrinkEntity.getCategory().name();
        String column = "sugar";
        String order = "asC";

        Map<Long, List<DrinkEntity>> drinkEntityMap = new HashMap<>();
        drinkEntityMap.put(franchiseId, Arrays.asList(firstDrinkEntity, thirdDrinkEntity));

        Mockito.when(findDrinksByFranchiseAndCategoryRepository.findDrinksByFranchiseAndCategoryAndDirection(franchiseId, category, column, order)).thenReturn(drinkEntityMap);

        //when
        List<DrinkAllInfoResponse> result = drinkSearchService.getDrinksByFranchiseAndCategoryAndDirection(franchiseId, category, column, order);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void 전체_프랜차이즈_아메리카노_당_높은순_조회() {
        //given
        String category = firstDrinkEntity.getCategory().name();
        String column = "sugar";
        String order = "DeSc";

        Map<Long, List<DrinkEntity>> drinkEntityMap = new HashMap<>();
        drinkEntityMap.put(1L, Arrays.asList(thirdDrinkEntity, firstDrinkEntity));
        drinkEntityMap.put(2L, Arrays.asList(fourthDrinkEntity));

        Mockito.when(findDrinksByFranchiseAndCategoryRepository.findDrinksByFranchiseAndCategoryAndDirection(null, category, column, order)).thenReturn(drinkEntityMap);

        //when
        List<DrinkAllInfoResponse> result = drinkSearchService.getDrinksByFranchiseAndCategoryAndDirection(null, category, column, order);

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void 전체_프랜차이즈_전체_음료_당_높은순_조회() {
        //given
        String column = "sugar";
        String order = "DeSc";

        Map<Long, List<DrinkEntity>> drinkEntityMap = new HashMap<>();
        drinkEntityMap.put(1L, Arrays.asList(secondDrinkEntity, thirdDrinkEntity, firstDrinkEntity));
        drinkEntityMap.put(2L, Arrays.asList(fourthDrinkEntity));

        Mockito.when(findDrinksByFranchiseAndCategoryRepository.findDrinksByFranchiseAndCategoryAndDirection(null, null, column, order)).thenReturn(drinkEntityMap);

        //when
        List<DrinkAllInfoResponse> result = drinkSearchService.getDrinksByFranchiseAndCategoryAndDirection(null, null, column, order);

        //then
        assertThat(result.size()).isEqualTo(3);
    }
}