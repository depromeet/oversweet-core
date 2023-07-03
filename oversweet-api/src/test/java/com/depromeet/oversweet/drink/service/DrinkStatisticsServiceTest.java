package com.depromeet.oversweet.drink.service;


import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.drink.dto.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("음료 통계 테스트")
@MockitoSettings
class DrinkStatisticsServiceTest {

    @Mock
    private FindRecordsRepository findRecordsRepository;
    @Mock
    private FindMemberRepository findMemberRepository;
    @InjectMocks
    DrinkStatisticsService drinkStatisticsService;

    private MemberEntity memberEntity;
    private FranchiseEntity franchiseEntity;
    private DrinkEntity drinkEntity;
    private RecordEntity recordEntity;
    @BeforeEach
    void setUp() {
        memberEntity = getMemberEntity();
        franchiseEntity = getFranchiseEntity();
        drinkEntity = getDrinkEntity(franchiseEntity);
        recordEntity = getRecordEntity(memberEntity, drinkEntity);
    }

    @Test
    @DisplayName("해당 유저의 통계 목록을 조회 한다.")
    void getUserDrinkStatisticsInfo() {
        // given
        LocalDateTimeInfo requestDateTime = LocalDateTimeInfo.getDailyDateTime();
        List<RecordEntity> recordEntities = Arrays.asList(recordEntity);

        Mockito.when(findRecordsRepository.findDailyRecordsByLocalDateTime(memberEntity.getId(), requestDateTime.startDateTime(), requestDateTime.endDateTime()))
                .thenReturn(recordEntities);
        Mockito.when(findMemberRepository.findById(memberEntity.getId()))
                .thenReturn(memberEntity);

        // when
        DrinkDailySugarStatisticsResponse response = drinkStatisticsService.retrieveUserDailySugarStatistics(memberEntity.getId());

        // then
        assertThat(response.dailyDrinks().size() == 1);
        assertThat(response.totalDailyStatistics().dailySugar() == memberEntity.getDailySugar());
        assertThat(response.totalDailyStatistics().dailyTotalCalorie() == recordEntity.totalCalorie());
        assertThat(response.totalDailyStatistics().dailyTotalSugar() == recordEntity.totalSugar());
        assertThat(!response.totalDailyStatistics().isExcess());
        assertThat(response.dailyDrinks().get(0).id()).isEqualTo(drinkEntity.getId());
        assertThat(response.dailyDrinks().get(0).name()).isEqualTo(drinkEntity.getName());
        assertThat(response.dailyDrinks().get(0).size()).isEqualTo(drinkEntity.getSize());
    }


    private RecordEntity getRecordEntity(final MemberEntity memberEntity, final DrinkEntity drinkEntity) {
        final RecordEntity recordEntity = RecordEntity.builder()
                .id(1L)
                .member(memberEntity)
                .drink(drinkEntity)
                .intakeSugar(10)
                .count(1)
                .build();
        return recordEntity;
    }

    private DrinkEntity getDrinkEntity(final FranchiseEntity franchiseEntity) {
        final DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(1L)
                .calorie(10)
                .size(355)
                .category(DrinkCategory.AMERICANO)
                .sugar(10)
                .franchise(franchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("아메리카노")
                .build();
        return drinkEntity;
    }

    private FranchiseEntity getFranchiseEntity() {
        final FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();
        return franchiseEntity;
    }

    private MemberEntity getMemberEntity() {
        final MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .socialId("socialId")
                .dailySugar(25)
                .socialProvider(SocialProvider.KAKAO)
                .build();
        return memberEntity;
    }

}
