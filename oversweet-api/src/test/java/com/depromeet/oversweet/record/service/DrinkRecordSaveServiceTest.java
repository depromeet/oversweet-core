package com.depromeet.oversweet.record.service;

import com.depromeet.oversweet.common.TestSetup;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.domain.record.repository.SaveRecordRepository;
import com.depromeet.oversweet.record.dto.request.DrinkRecordSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("마신 음료 당 기록 테스트")
@MockitoSettings
class DrinkRecordSaveServiceTest {

    @Mock
    private FindMemberRepository findMemberRepository;
    @Mock
    private FindDrinkRepository findDrinkRepository;
    @Mock
    private FindRecordsRepository findRecordsRepository;
    @Mock
    private SaveRecordRepository saveRecordRepository;

    @InjectMocks
    DrinkRecordSaveService drinkRecordSaveService;

    private MemberEntity memberEntity;
    private DrinkEntity drinkEntity;
    private RecordEntity recordEntity;

    @BeforeEach
    void setUp() {
        TestSetup testSetup = new TestSetup();
        testSetup.setUp();
        memberEntity = testSetup.getMember();
        drinkEntity = testSetup.getFirstDrink();
        recordEntity = testSetup.getFirstRecord();
    }

    @Test
    @DisplayName("마신 음료 당 기록을 한다.")
    void saveDrinkRecordTest() {
        //given

        //when
        when(findMemberRepository.findMemberById(memberEntity.getId())).thenReturn(memberEntity);
        when(findDrinkRepository.findDrinkById(drinkEntity.getId())).thenReturn(drinkEntity);
        when(findRecordsRepository.findRecordById(recordEntity.getId())).thenReturn(Optional.ofNullable(recordEntity));

        DrinkRecordSaveRequest saveRequest = DrinkRecordSaveRequest.builder()
                .drinkId(drinkEntity.getId())
                .count(recordEntity.getCount())
                .intakeSugar(recordEntity.getIntakeSugar())
                .build();

        drinkRecordSaveService.saveDrinkRecord(memberEntity.getId(), saveRequest);

        Optional<RecordEntity> findRecordOpt = findRecordsRepository.findRecordById(recordEntity.getId());

        assertThat(findRecordOpt.isPresent());
        assertEquals(recordEntity.getId(), findRecordOpt.get().getId());
    }

}