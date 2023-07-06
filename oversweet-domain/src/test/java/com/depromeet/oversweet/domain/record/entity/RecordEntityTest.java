package com.depromeet.oversweet.domain.record.entity;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordEntityTest {

    @Test
    void totalSugar메서드는_count와_intakeSugar을_곱한값을_응답한다() {
        // given
        RecordEntity recordEntity = RecordEntity.builder()
                .count(2)
                .intakeSugar(10)
                .build();

        // when
        int totalSugar = recordEntity.totalSugar();

        // then
        assertEquals(20, totalSugar);
    }

    @Test
    void totalCalorie메서드는_count와_calorie를_곱한값을_응답한다() {
        // given
        DrinkEntity drinkEntity = DrinkEntity.builder()
                .calorie(10)
                .build();
        RecordEntity recordEntity = RecordEntity.builder()
                .count(2)
                .drink(drinkEntity)
                .build();

        // when
        int totalCalorie = recordEntity.totalCalorie();

        // then
        assertEquals(20, totalCalorie);
    }
}