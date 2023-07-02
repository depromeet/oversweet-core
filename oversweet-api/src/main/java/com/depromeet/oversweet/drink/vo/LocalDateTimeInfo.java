package com.depromeet.oversweet.drink.vo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/*
    해당 레코드는 로컬 데이트 타임 계산하는 역할을 가지고 있음.
    데일리의 경우 바로 계산이 되지만, 위클리의 경우 새로운 메서드를 만들어서 Start와 End를 계산
 */
public record LocalDateTimeInfo(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
    public static LocalDateTimeInfo getDailyDateTime(){
        final LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        final LocalDateTime newStartDateTime = nowDateTime.with(LocalTime.MIN);
        final LocalDateTime newEndDateTime = nowDateTime.with(LocalTime.MAX);
        return new LocalDateTimeInfo(newStartDateTime, newEndDateTime);
    }
}
