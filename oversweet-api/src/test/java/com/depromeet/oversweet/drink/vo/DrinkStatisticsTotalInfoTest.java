package com.depromeet.oversweet.drink.vo;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarTotalStatisticsInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DrinkStatisticsTotalInfoTest {

    private List<RecordEntity> recordEntities;
    private DrinkStatisticsTotalInfo drinkStatisticsTotalInfo;

    /**
     * [권장 당 섭취량 : 36]
     *
     * [오늘 섭취량]
     *  - 음료  : 2잔
     *  - 총 섭취 당 : 100g
     *  - 총 섭취 칼로리 : 300kcal
     *
     *  [통계]
     *   - 권장 섭취량 : 36
     *   - 남은 섭취량 : 64
     *   - 총 섭취 당 : 100
     *   - 총 섭취 칼로리 : 300
     *   - 초과 여부 : true
     */
    @BeforeEach
    public void setup() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .dailySugar(36)
                .build();

        DrinkEntity drink = DrinkEntity.builder()
                .id(1L)
                .name("아메리카노")
                .sugar(10)
                .calorie(100)
                .build();

        recordEntities = Arrays.asList(
                new RecordEntity(1L, member, drink, 1, 30),
                new RecordEntity(2L, member, drink, 2, 35)
        );

        drinkStatisticsTotalInfo = new DrinkStatisticsTotalInfo(recordEntities);
    }

    @Test
    void 오늘하루_섭취량_통계_계산 () {
        // Given (하루 당 권장 섭취량)
        int dailySugar = 36;

        // When
        DrinkDailySugarTotalStatisticsInfo statisticsInfo = drinkStatisticsTotalInfo.getDailyTotalStatisticsInfo(dailySugar);

        // Then
        assertThat(36).isEqualTo(statisticsInfo.dailySugar());
        assertEquals(64, statisticsInfo.remainingDailySugar());
        assertEquals(100, statisticsInfo.dailyTotalSugar());
        assertEquals(300, statisticsInfo.dailyTotalCalorie());
        assertThat(true).isEqualTo(statisticsInfo.isExcess());
    }
}