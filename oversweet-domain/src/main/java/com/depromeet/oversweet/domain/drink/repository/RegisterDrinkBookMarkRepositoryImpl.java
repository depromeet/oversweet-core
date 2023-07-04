package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.DrinkBookMarkJpaRepository;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 음료 즐겨찾기 등록 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class RegisterDrinkBookMarkRepositoryImpl implements RegisterDrinkBookMarkRepository {

    private final DrinkBookMarkJpaRepository drinkBookMarkJpaRepository;

    @Override
    @Transactional
    public void saveDrinkBookmark(final MemberEntity member, final DrinkEntity drink) {
        final DrinkBookmarkEntity bookMark = DrinkBookmarkEntity.builder()
                .member(member)
                .drink(drink)
                .build();
        drinkBookMarkJpaRepository.save(bookMark);
    }

}
