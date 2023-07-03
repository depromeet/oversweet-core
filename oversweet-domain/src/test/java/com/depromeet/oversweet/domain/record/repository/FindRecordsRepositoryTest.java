package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.TestConfig;
import com.depromeet.oversweet.domain.config.JpaEntityConfig;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.drink.repository.DrinkJpaRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FranchiseJpaRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.member.repository.MemberJpaRepository;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaEntityConfig.class, TestConfig.class})
class FindRecordsRepositoryTest {
    private final FindRecordsRepository findRecordsRepository;
    private final MemberJpaRepository memberJpaRepository;

    private final FranchiseJpaRepository franchiseJpaRepository;

    private final DrinkJpaRepository drinkJpaRepository;

    private final RecordJpaRepository recordJpaRepository;

    @Autowired
    public FindRecordsRepositoryTest(FindRecordsRepository findRecordsRepository,
                                     MemberJpaRepository memberJpaRepository,
                                     FranchiseJpaRepository franchiseJpaRepository,
                                     DrinkJpaRepository drinkJpaRepository,
                                     RecordJpaRepository recordJpaRepository) {
        this.findRecordsRepository = findRecordsRepository;
        this.memberJpaRepository = memberJpaRepository;
        this.franchiseJpaRepository = franchiseJpaRepository;
        this.drinkJpaRepository = drinkJpaRepository;
        this.recordJpaRepository = recordJpaRepository;
    }

    @BeforeEach
    void setUp() {
        final MemberEntity memberEntity = getMemberEntity();
        memberJpaRepository.save(memberEntity);

        final FranchiseEntity franchiseEntity = getFranchiseEntity();
        franchiseJpaRepository.save(franchiseEntity);

        final DrinkEntity drinkEntity = getDrinkEntity(franchiseEntity);
        drinkJpaRepository.save(drinkEntity);

        final RecordEntity recordEntity = getRecordEntity(memberEntity, drinkEntity);
        recordJpaRepository.save(recordEntity);
    }



    @Test
    void 유저의_고유_ID를_유저의_데일리_당_통계_조회() {
        // given
        final Long userId = 1L;
        final LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        final LocalDateTime newStartDateTime = nowDateTime.with(LocalTime.MIN);
        final LocalDateTime newEndDateTime = nowDateTime.with(LocalTime.MAX);

        // when
        List<RecordEntity> records = findRecordsRepository.findDailyRecordsByLocalDateTime(userId, newStartDateTime, newEndDateTime);

        // then
        assertThat(records).hasSize(1);
    }

    private RecordEntity getRecordEntity(final MemberEntity memberEntity, final DrinkEntity drinkEntity) {
        final RecordEntity recordEntity = RecordEntity.builder()
                .id(1L)
                .member(memberEntity)
                .drink(drinkEntity)
                .intakeSugar(10)
                .count(1)
                .build();
        return recordEntity;
    }

    private DrinkEntity getDrinkEntity(final FranchiseEntity franchiseEntity) {
        final DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(1L)
                .calorie(10)
                .size(355)
                .category(DrinkCategory.AMERICANO)
                .sugar(10)
                .franchise(franchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("아메리카노")
                .build();
        return drinkEntity;
    }

    private FranchiseEntity getFranchiseEntity() {
        final FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();
        return franchiseEntity;
    }

    private MemberEntity getMemberEntity() {
        final MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .socialId("socialId")
                .socialProvider(SocialProvider.KAKAO)
                .build();
        return memberEntity;
    }
}
