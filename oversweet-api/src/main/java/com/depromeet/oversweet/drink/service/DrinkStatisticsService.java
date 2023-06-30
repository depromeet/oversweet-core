package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindDailyRecordsRepository;
import com.depromeet.oversweet.drink.dto.DrinkDailyDetailInfo;
import com.depromeet.oversweet.drink.dto.DrinkDailySugarStatisticsResponse;
import com.depromeet.oversweet.drink.dto.DrinkDailySugarTotalStatisticsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkStatisticsService {

    private final FindDailyRecordsRepository findDailyRecordsRepository;
    private final FindMemberRepository findMemberRepository;

    public DrinkDailySugarStatisticsResponse retrieveUserDailySugarStatistics(final Long memberId) {
        // TODO 유저 검증

        // 유저의 하루 적정 섭취량 가져오기
        final MemberEntity findMember = findMemberRepository.findById(memberId);

        // 오늘 (데일리 날짜 확인) 00:00 ~ 23:59
        final LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        final LocalDateTime startDateTime = nowDateTime.with(LocalTime.MIN);
        final LocalDateTime endDateTime = nowDateTime.with(LocalTime.MAX);

        // 해당 유저의 오늘(데일리) 섭취 음료 조회 Repository
        final List<RecordEntity> dailyRecords = findDailyRecordsRepository.findDailyRecordsByLocalDatetime(memberId, startDateTime, endDateTime);

        // 조회 한 데이터 가공 하기. ( 당 섭취량 계산, 칼로리 계산 )
        final int totalIntakeSugar = getTotalIntakeSugar(dailyRecords);
        final int totalCalorie = getTotalCalorie(dailyRecords);
        final int remainingSugar = getRemainingSugar(dailyRecords, findMember.getDailySugar());

        // Response로 만들어 주기
        final DrinkDailySugarTotalStatisticsInfo totalStatisticsInfo =
                DrinkDailySugarTotalStatisticsInfo.of(findMember.getDailySugar(), remainingSugar, totalIntakeSugar, totalCalorie);

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
                .map(dr -> {
                    return DrinkDailyDetailInfo.of(dr.getDrink());
                }).toList();
    }

    private int getTotalIntakeSugar(final List<RecordEntity> recordEntities) {
        return recordEntities.stream()
                .mapToInt(RecordEntity::getIntakeSugar)
                .sum();
    }

    private int getTotalCalorie(final List<RecordEntity> dailyRecords) {
        return dailyRecords.stream()
                .mapToInt(record -> record.getDrink().getCalorie())
                .sum();
    }

    private int getRemainingSugar(final List<RecordEntity> recordEntities, final Integer memberDailySugar) {
        final Integer totalIntakeSugar = getTotalIntakeSugar(recordEntities);
        final int remainingSugar = memberDailySugar - totalIntakeSugar;

        // 당 섭취량을 초과했을경우 -1로 보내주기.
        if (0 > remainingSugar) {
            return -1;
        }

        return remainingSugar;
    }
}
