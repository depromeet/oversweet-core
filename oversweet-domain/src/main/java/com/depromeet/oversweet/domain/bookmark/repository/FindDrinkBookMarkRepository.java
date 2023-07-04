package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

import java.util.List;

/**
 * 즐겨 찾기한 음료 조회 Interface
 */
public interface FindDrinkBookMarkRepository {
    List<DrinkBookmarkEntity> findDrinkBookMarkByMemberId(final Long memberId);

    void validateAlreadyDrinkBookMarked(final MemberEntity member, final DrinkEntity drink);
}
