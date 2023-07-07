package com.depromeet.oversweet.common;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;

public class TestSetup {
    private MemberEntity memberEntity;
    private FranchiseEntity franchiseEntity;
    private DrinkEntity firstDrinkEntity;
    private DrinkEntity secondDrinkEntity;
    private RecordEntity firstRecordEntity;
    private RecordEntity secondRecordEntity;

    public void setUp() {
        memberEntity = getMemberEntity();
        franchiseEntity = getFranchiseEntity();
        firstDrinkEntity = getFirstDrinkEntity(franchiseEntity);
        secondDrinkEntity = getSecondDrinkEntity(franchiseEntity);
        firstRecordEntity = getRecordEntity(1L, memberEntity, firstDrinkEntity);
        secondRecordEntity = getRecordEntity(2L, memberEntity, secondDrinkEntity);
    }

    private RecordEntity getRecordEntity(final Long id, final MemberEntity memberEntity, final DrinkEntity drinkEntity) {
        final RecordEntity recordEntity = RecordEntity.builder()
                .id(id)
                .member(memberEntity)
                .drink(drinkEntity)
                .intakeSugar(drinkEntity.getSugar())
                .count(1)
                .build();
        return recordEntity;
    }

    private DrinkEntity getFirstDrinkEntity(final FranchiseEntity franchiseEntity) {
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

    private DrinkEntity getSecondDrinkEntity(final FranchiseEntity franchiseEntity) {
        final DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(2L)
                .calorie(20)
                .size(355)
                .category(DrinkCategory.LATTE)
                .sugar(30)
                .franchise(franchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("카페라떼")
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
                .dailySugar(25)
                .socialProvider(SocialProvider.KAKAO)
                .build();
        return memberEntity;
    }

    public MemberEntity getMember() {
        return memberEntity;
    }

    public FranchiseEntity getFranchise() {
        return franchiseEntity;
    }

    public DrinkEntity getFirstDrink() {
        return firstDrinkEntity;
    }

    public RecordEntity getFirstRecord() {
        return firstRecordEntity;
    }

    public DrinkEntity getSecondDrink() {
        return secondDrinkEntity;
    }

    public RecordEntity getSecondRecord() {
        return secondRecordEntity;
    }

}
