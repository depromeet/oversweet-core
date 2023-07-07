package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.DrinkBookMarkJpaRepository;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 음료 즐겨찾기 업데이트(등록, 삭제) 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class UpdateDrinkBookMarkRepositoryImpl implements UpdateDrinkBookMarkRepository {

    private final DrinkBookMarkJpaRepository drinkBookMarkJpaRepository;

    /**
     * 음료 즐겨찾기 등록
     *
     * @param member API 접근자 Member Entity
     * @param drink 즐겨찾기 등록할 Drink Entity
     */
    @Override
    @Transactional
    public void saveDrinkBookmark(final MemberEntity member, final DrinkEntity drink) {
        final DrinkBookmarkEntity bookMark = DrinkBookmarkEntity.builder()
                .member(member)
                .drink(drink)
                .build();
        drinkBookMarkJpaRepository.save(bookMark);
    }

    /**
     * 음료 즐겨찾기 해제
     *
     * @param member API 접근자 Member Entity
     * @param drink 즐겨찾기 해제할 Drink Entity
     */
    @Override
    @Transactional
    public void deleteDrinkBookmark(MemberEntity member, DrinkEntity drink) {
        drinkBookMarkJpaRepository.deleteByMemberAndDrink(member, drink);
    }

}
