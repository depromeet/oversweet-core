package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.exception.drink.NotFoundDrinkException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindDrinkRepositoryImplTest {

    @Mock
    private DrinkJpaRepository drinkJpaRepository;

    @InjectMocks
    private FindDrinkRepositoryImpl findDrinkRepository;


    @Test
    void 음료_조회_성공() {
        Long drinkId = 1L;
        DrinkEntity drink = DrinkEntity.builder()
                .id(drinkId)
                .name("아메리카노")
                .build();

        when(drinkJpaRepository.findById(drinkId)).thenReturn(Optional.of(drink));

        DrinkEntity foundDrink = findDrinkRepository.findDrinkById(drinkId);

        assertThat(foundDrink).isEqualTo(drink);
    }

    @Test
    void 찾고자_하는_음료가_없는_경우() {
        Long drinkId = 1L;

        when(drinkJpaRepository.findById(drinkId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findDrinkRepository.findDrinkById(drinkId))
                .isInstanceOf(NotFoundDrinkException.class);
    }
}