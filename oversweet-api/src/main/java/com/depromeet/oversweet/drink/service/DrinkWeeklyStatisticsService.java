package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.common.dto.response.MemberInfo;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.drink.dto.request.DrinkWeeklySugarDateRequest;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarStatisticsResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarInfo;
import com.depromeet.oversweet.drink.vo.DrinkStatisticsTotalInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkWeeklySugarTotalStatisticsInfo;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 음료 주간 통계 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkWeeklyStatisticsService {
    private final FindRecordsRepository findRecordsRepository;
    private final FindMemberRepository findMemberRepository;

    /**
     * 유저의 주간 당 통계 정보 조회
     * @param userId 해당 유저의 Id
     * @param request 조회할 시작 날짜, 끝 날짜
     * @return 유저의 주간 당 총 통계 및 음료 차트 목록
     */
    public DrinkWeeklySugarStatisticsResponse retrieveUserWeeklySugarStatistics(final Long userId, final DrinkWeeklySugarDateRequest request) {

        // 유저의 하루 적정 섭취량 가져오기 ( 유저의 정보를 가져온 후 찾기 )
        final MemberEntity findMember = findMemberRepository.findById(userId);

        // 주간 시작일, 끝낼일 가져오기.
        final LocalDateTimeInfo dateTimeInfo = LocalDateTimeInfo.getWeeklyDateTime(request.startDate(), request.endDate());

        // 주간 당 섭취량 가져오기.
        final List<RecordEntity> weeklyRecords = findRecordsRepository.findRecordsByLocalDateTime(findMember.getId(), dateTimeInfo.startDateTime(), dateTimeInfo.endDateTime());

        // 일급 컬렉션을 사용해 계산 작업 수행. (주간 당 전체 섭취량, 주간 칼로리 섭취량, 주간 당 평균 섭취량)
        final DrinkStatisticsTotalInfo drinkStatisticsTotalInfo = new DrinkStatisticsTotalInfo(weeklyRecords);
        final DrinkWeeklySugarTotalStatisticsInfo weeklyTotalStatisticsInfo = drinkStatisticsTotalInfo.getWeeklyTotalStatisticsInfo();

        // 해당 요일의 총 당 섭취량 정보 리스트 매핑
        final List<DrinkDailySugarInfo> dayOfWeekSugarInfo = drinkStatisticsTotalInfo.getDayOfWeekSugarInfo();

        // Response로 변환
        final MemberInfo memberInfo = MemberInfo.of(findMember.getId(), findMember.getNickname(), findMember.getDailySugar());

        return DrinkWeeklySugarStatisticsResponse.builder()
                .member(memberInfo)
                .weeklyChart(dayOfWeekSugarInfo)
                .weekStatistics(weeklyTotalStatisticsInfo)
                .build();

    }
}
