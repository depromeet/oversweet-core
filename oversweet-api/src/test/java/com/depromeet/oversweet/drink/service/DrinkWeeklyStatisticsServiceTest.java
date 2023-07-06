package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.common.TestSetup;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.domain.record.repository.RecordJpaRepository;
import com.depromeet.oversweet.drink.dto.request.DrinkWeeklySugarDateRequest;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarStatisticsResponse;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("음료 데일리(하루) 통계 테스트")
@MockitoSettings
public class DrinkWeeklyStatisticsServiceTest {
    @Mock
    private FindRecordsRepository findRecordsRepository;
    @Mock
    private FindMemberRepository findMemberRepository;
    @InjectMocks
    DrinkWeeklyStatisticsService drinkWeeklyStatisticsService;

    private MemberEntity memberEntity;
    private RecordEntity firstRecordEntity;
    private RecordEntity secondRecordEntity;

    @BeforeEach
    void setUp() {
        TestSetup testSetup = new TestSetup();
        testSetup.setUp();
        memberEntity = testSetup.getMember();
        firstRecordEntity = testSetup.getFirstRecord();

        secondRecordEntity = testSetup.getSecondRecord();
    }

    @Test
    @DisplayName("해당 유저의 주간 통계 목록을 조회 한다.")
    void getUserDrinkWeeklyStatisticsInfo() {
        // given
        final LocalDate now = LocalDate.now();
        final LocalDate startDate = now.minusDays(1);
        final LocalDate endDate = LocalDate.MAX;

        DrinkWeeklySugarDateRequest request = new DrinkWeeklySugarDateRequest(startDate, endDate);

        // TODO 음료 기록하는 테스트 나오면 수정하기
        LocalDateTimeInfo requestDateTime = LocalDateTimeInfo.getWeeklyDateTime(startDate, endDate);
        List<RecordEntity> recordEntities = Arrays.asList(firstRecordEntity, secondRecordEntity);

        // when
        Mockito.when(findRecordsRepository.findRecordsByLocalDateTime(memberEntity.getId(), requestDateTime.startDateTime(), requestDateTime.endDateTime()))
                .thenReturn(recordEntities);
        Mockito.when(findMemberRepository.findById(memberEntity.getId()))
                .thenReturn(memberEntity);

        // then
        DrinkWeeklySugarStatisticsResponse response = drinkWeeklyStatisticsService.retrieveUserWeeklySugarStatistics(memberEntity.getId(), request);

        assertThat(response.weeklyChart().size() == 2);
        assertThat(response.weekStatistics().dailyAverageSugar() == 20);
        assertThat(response.weekStatistics().totalCalorie() == 30);
        assertThat(response.weekStatistics().totalSugar() == 40);
        assertThat(response.member().memberId() == memberEntity.getId());
        assertThat(response.member().dailySugar() == memberEntity.getDailySugar());
        assertThat(response.member().nickname() == memberEntity.getNickname());
    }

}
