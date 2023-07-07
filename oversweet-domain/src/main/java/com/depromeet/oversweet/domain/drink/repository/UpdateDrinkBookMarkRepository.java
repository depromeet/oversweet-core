package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

public interface UpdateDrinkBookMarkRepository {

    void saveDrinkBookmark(final MemberEntity member, final DrinkEntity drink);

    void deleteDrinkBookmark(final MemberEntity member, final DrinkEntity drink);

}
