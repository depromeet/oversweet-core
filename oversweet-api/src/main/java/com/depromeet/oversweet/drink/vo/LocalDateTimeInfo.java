package com.depromeet.oversweet.drink.vo;

import java.time.LocalDate;
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
    public static final String DEFAULT_ZONE_ID = "Asia/Seoul";
    private static final int ONE_DAY = 1;

    public static LocalDateTimeInfo getDailyDateTime() {
        final LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_ID));
        final LocalDateTime newStartDateTime = nowDateTime.with(LocalTime.MIN);
        final LocalDateTime newEndDateTime = nowDateTime.with(LocalTime.MAX);
        return new LocalDateTimeInfo(newStartDateTime, newEndDateTime);
    }

    public static LocalDateTimeInfo getWeeklyDateTime(final LocalDate startDate, final LocalDate endDate) {
        final LocalDateTime startDateTime = startDate.atTime(LocalTime.MIN);
        final LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return new LocalDateTimeInfo(startDateTime, endDateTime);
    }

    /**
     * 어제 날짜를 기준으로 시작시간과 끝시간을 계산
     *
     * @return 어제 날짜 기준 시작시간과 끝시간
     */
    public static LocalDateTimeInfo getYesterday() {
        final LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_ID));
        final LocalDateTime newStartDateTime = nowDateTime.minusDays(ONE_DAY).with(LocalTime.MIN);
        final LocalDateTime newEndDateTime = nowDateTime.minusDays(ONE_DAY).with(LocalTime.MAX);
        return new LocalDateTimeInfo(newStartDateTime, newEndDateTime);
    }
}
