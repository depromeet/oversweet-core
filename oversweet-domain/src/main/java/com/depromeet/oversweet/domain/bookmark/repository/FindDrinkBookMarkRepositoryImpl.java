package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.drink.AlreadyDrinkBookMarked;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.oversweet.exception.ErrorCode.ALREADY_DRINK_BOOK_MARKED;

/**
 * 음료 즐겨찾기 조회 레포지토리
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class FindDrinkBookMarkRepositoryImpl implements FindDrinkBookMarkRepository {

    private final DrinkBookMarkJpaRepository drinkBookMarkJpaRepository;

    /**
     * 유저가 즐겨 찾기한 음료 Entity 목록 조회
     *
     * @param memberId 유저 ID
     * @return 즐겨찾기한 음료 Entity 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<DrinkBookmarkEntity> findDrinkBookMarkByMemberId(final Long memberId) {
        return drinkBookMarkJpaRepository.findByMemberId(memberId);
    }

    /**
     * 해당 음료 이미 즐겨찾기 여부 조회
     *
     * @param member 유저 Entity
     * @param drink 음료 Entity
     */
    @Override
    @Transactional(readOnly = true)
    public void validateAlreadyDrinkBookMarked(final MemberEntity member, final DrinkEntity drink) {
        if(drinkBookMarkJpaRepository.existsByMemberAndDrink(member, drink)) {
            throw new AlreadyDrinkBookMarked(ALREADY_DRINK_BOOK_MARKED);
        }
    }
}
