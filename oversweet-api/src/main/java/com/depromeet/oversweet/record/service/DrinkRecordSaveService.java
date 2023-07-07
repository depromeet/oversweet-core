package com.depromeet.oversweet.record.service;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.domain.record.repository.SaveRecordRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarTotalStatisticsInfo;
import com.depromeet.oversweet.drink.vo.DrinkStatisticsTotalInfo;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import com.depromeet.oversweet.record.dto.request.DrinkRecordSaveRequest;
import com.depromeet.oversweet.record.dto.response.DrinkRecordSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkRecordSaveService {

    private final FindMemberRepository findMemberRepository;
    private final FindDrinkRepository findDrinkRepository;
    private final FindRecordsRepository findRecordsRepository;
    private final SaveRecordRepository saveRecordRepository;

    public DrinkRecordSaveResponse saveDrinkRecord(Long memberId, DrinkRecordSaveRequest drinkRecordSaveRequest) {

        final MemberEntity findMember = findMemberRepository.findMemberById(memberId);

        final DrinkEntity findDrink = findDrinkRepository.findDrinkById(drinkRecordSaveRequest.id());

        RecordEntity record = RecordEntity.builder()
                .member(findMember)
                .drink(findDrink)
                .count(drinkRecordSaveRequest.count())
                .intakeSugar(drinkRecordSaveRequest.intakeSugar())
                .build();

        saveRecordRepository.saveRecord(record);

        // 오늘 (데일리 날짜 확인) 00:00 ~ 23:59
        final LocalDateTimeInfo dateTimeInfo = LocalDateTimeInfo.getDailyDateTime();

        // 해당 유저의 오늘(데일리) 섭취 음료 조회 Repository
        final List<RecordEntity> dailyRecords = findRecordsRepository.findRecordsByLocalDateTime(findMember.getId(), dateTimeInfo.startDateTime(), dateTimeInfo.endDateTime());

        DrinkStatisticsTotalInfo drinkStatisticsTotalInfo = new DrinkStatisticsTotalInfo(dailyRecords);

        DrinkDailySugarTotalStatisticsInfo dailyTotalStatisticsInfo = drinkStatisticsTotalInfo.getDailyTotalStatisticsInfo(findMember.getDailySugar());

        // 기록 관련 response 로 반환
        return DrinkRecordSaveResponse.of(record.getIntakeSugar(), dailyTotalStatisticsInfo);
    }
}
