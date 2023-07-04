package com.depromeet.oversweet.drink.vo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeInfoTest {

    @Test
    void 오늘의_시작_끝_시간을_가져온다() {
        // When
        LocalDateTimeInfo dateTimeInfo = LocalDateTimeInfo.getDailyDateTime();

        // Then
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now.with(LocalTime.MIN), dateTimeInfo.startDateTime());
        assertEquals(now.with(LocalTime.MAX), dateTimeInfo.endDateTime());
    }
}