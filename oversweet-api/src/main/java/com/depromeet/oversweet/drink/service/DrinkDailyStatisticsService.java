package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkDailyDetailInfo;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarTotalStatisticsInfo;
import com.depromeet.oversweet.drink.vo.DrinkStatisticsTotalInfo;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 음료 하루(데일리) 통계 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkDailyStatisticsService {

    private final FindRecordsRepository findRecordsRepository;
    private final FindMemberRepository findMemberRepository;

    /**
     * 유저의 데일리(하루) 음료 당 통계 및 음료 목록 조회
     *
     * @param memberId 유저 ID
     * @return 유저의 데일리(하루) 음료 당 통계 및 음료 목록 정보
     */
    public DrinkDailySugarStatisticsResponse retrieveUserDailySugarStatistics(final Long memberId) {
        // TODO 유저 검증

        // 유저의 하루 적정 섭취량 가져오기 ( 유저의 정보를 가져온 후 찾기 )
        final MemberEntity findMember = findMemberRepository.findById(memberId);

        // 오늘 (데일리 날짜 확인) 00:00 ~ 23:59
        final LocalDateTimeInfo dateTimeInfo = LocalDateTimeInfo.getDailyDateTime();

        // 해당 유저의 오늘(데일리) 섭취 음료 조회 Repository
        final List<RecordEntity> dailyRecords = findRecordsRepository.findRecordsByLocalDateTime(findMember.getId(), dateTimeInfo.startDateTime(), dateTimeInfo.endDateTime());

        // 일급 컬렉션으로 그 객체에 계산 로직 위임.
        final DrinkStatisticsTotalInfo drinkStatisticsTotalInfo = new DrinkStatisticsTotalInfo(dailyRecords);
        
        // Response로 만들어 주기
        final DrinkDailySugarTotalStatisticsInfo totalStatisticsInfo = 
                drinkStatisticsTotalInfo.getDailyTotalStatisticsInfo(findMember.getDailySugar());

        final List<DrinkDailyDetailInfo> dailyDetailInfo = getDailyDrinks(dailyRecords);

        // return
       return DrinkDailySugarStatisticsResponse
                .builder()
                .totalDailyStatistics(totalStatisticsInfo)
                .dailyDrinks(dailyDetailInfo)
                .build();
    }

    private List<DrinkDailyDetailInfo> getDailyDrinks(final List<RecordEntity> dailyRecords) {
        return dailyRecords.stream()
                .map(dr -> DrinkDailyDetailInfo.of(dr.getDrink(), dr.getId()))
                .toList();
    }
}
