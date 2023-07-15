package com.depromeet.oversweet.common;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;

public class TestSetup {

    private MemberEntity memberEntity;
    private FranchiseEntity firstFranchiseEntity;
    private FranchiseEntity secondFranchiseEntity;
    private DrinkEntity firstDrinkEntity;
    private DrinkEntity secondDrinkEntity;
    private DrinkEntity thirdDrinkEntity;
    private DrinkEntity fourthDrinkEntity;
    private RecordEntity firstRecordEntity;
    private RecordEntity secondRecordEntity;

    public void setUp() {
        memberEntity = getMemberEntity();
        firstFranchiseEntity = getFirstFranchiseEntity();
        secondFranchiseEntity = getSecondFranchiseEntity();
        firstDrinkEntity = getFirstDrinkEntity(firstFranchiseEntity);
        secondDrinkEntity = getSecondDrinkEntity(firstFranchiseEntity);
        thirdDrinkEntity = getThirdDrinkEntity(firstFranchiseEntity);
        fourthDrinkEntity = getFourthDrinkEntity(secondFranchiseEntity);
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

    private DrinkEntity getFirstDrinkEntity(final FranchiseEntity firstFranchiseEntity) {
        final DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(1L)
                .calorie(10)
                .size(355)
                .category(DrinkCategory.AMERICANO)
                .sugar(10)
                .franchise(firstFranchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("아메리카노")
                .build();
        return drinkEntity;
    }

    private DrinkEntity getSecondDrinkEntity(final FranchiseEntity firstFranchiseEntity) {
        final DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(2L)
                .calorie(20)
                .size(355)
                .category(DrinkCategory.LATTE)
                .sugar(30)
                .franchise(firstFranchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("카페라떼")
                .build();
        return drinkEntity;
    }

    private DrinkEntity getThirdDrinkEntity(final FranchiseEntity firstFranchiseEntity) {
        return DrinkEntity.builder()
                .id(3L)
                .calorie(10)
                .size(475)
                .category(DrinkCategory.AMERICANO)
                .sugar(20)
                .franchise(firstFranchiseEntity)
                .imageUrl(null)
                .isMinimum(false)
                .name("아메리카노")
                .build();
    }

    private DrinkEntity getFourthDrinkEntity(final FranchiseEntity secondFranchiseEntity) {
        return DrinkEntity.builder()
                .id(4L)
                .calorie(10)
                .size(600)
                .category(DrinkCategory.AMERICANO)
                .sugar(30)
                .franchise(secondFranchiseEntity)
                .imageUrl(null)
                .isMinimum(true)
                .name("아메리카노")
                .build();
    }

    private FranchiseEntity getFirstFranchiseEntity() {
        final FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();
        return franchiseEntity;
    }

    private FranchiseEntity getSecondFranchiseEntity() {
        final FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(2L)
                .name("이디야")
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

    public FranchiseEntity getFirstFranchise() {
        return firstFranchiseEntity;
    }

    public FranchiseEntity getSecondFranchise() {
        return secondFranchiseEntity;
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

    public DrinkEntity getThirdDrink() {
        return thirdDrinkEntity;
    }

    public DrinkEntity getFourthDrink() {
        return fourthDrinkEntity;
    }

    public RecordEntity getSecondRecord() {
        return secondRecordEntity;
    }

}
