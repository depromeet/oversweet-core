package com.depromeet.oversweet.domain.member.entity;

import com.depromeet.oversweet.domain.member.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberEntityTest {

    @Test
    void checkRequiredInfoExist메서드는_필수값을_하나라도_null을_가지는_경우_false_응답한다() {
        // given
        MemberEntity memberEntity = MemberEntity.builder()
                .nickname("nickname")
                .email("email")
                .socialId("socialId")
                .build();

        // when
        boolean result = memberEntity.checkRequiredInfoExist();

        // then
        assertFalse(result);
    }

    @Test
    void updateRequiredInfo메서드는_남성일경우_36으로_권장섭취량이_설정된다() {
        MemberEntity member = new MemberEntity();

        member.updateRequiredInfo(Gender.MALE, 70, 170, 30, "nickname");

        Integer dailySugar = member.getDailySugar();

        assertEquals(36, dailySugar);
    }

    @Test
    void updateRequiredInfo메서드는_여성일경우_24으로_권장섭취량이_설정된다() {
        MemberEntity member = new MemberEntity();

        member.updateRequiredInfo(Gender.FEMALE, 70, 170, 30, "nickname");

        Integer dailySugar = member.getDailySugar();

        assertEquals(24, dailySugar);
    }
}