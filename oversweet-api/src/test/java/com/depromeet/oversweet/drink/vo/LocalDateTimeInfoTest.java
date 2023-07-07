package com.depromeet.oversweet.drink.vo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import static com.depromeet.oversweet.drink.vo.LocalDateTimeInfo.DEFAULT_ZONE_ID;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeInfoTest {

    @Test
    void 오늘의_시작_끝_시간을_가져온다() {
        // When
        LocalDateTimeInfo dateTimeInfo = LocalDateTimeInfo.getDailyDateTime();

        // Then
        LocalDateTime now = LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_ID));
        assertEquals(now.with(LocalTime.MIN), dateTimeInfo.startDateTime());
        assertEquals(now.with(LocalTime.MAX), dateTimeInfo.endDateTime());
    }
}