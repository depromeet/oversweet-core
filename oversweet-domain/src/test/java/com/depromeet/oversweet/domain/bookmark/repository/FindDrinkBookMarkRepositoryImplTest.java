package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.drink.AlreadyDrinkBookMarked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindDrinkBookMarkRepositoryImplTest {

    @Mock
    private DrinkBookMarkJpaRepository drinkBookMarkJpaRepository;

    @InjectMocks
    private FindDrinkBookMarkRepositoryImpl findDrinkBookMarkRepository;

    @Test
    void 음료_즐겨찾기_조회_성공() {
        Long memberId = 1L;
        MemberEntity member = MemberEntity.builder()
                .id(memberId)
                .build();

        DrinkEntity drink = DrinkEntity.builder()
                .id(1L)
                .name("아메리카노")
                .sugar(50)
                .build();

        DrinkBookmarkEntity drinkBookmark = DrinkBookmarkEntity.builder()
                .id(1L)
                .member(member)
                .drink(drink)
                .build();

        List<DrinkBookmarkEntity> drinkBookmarkEntities = Collections.singletonList(drinkBookmark);

        when(drinkBookMarkJpaRepository.findByMemberId(memberId)).thenReturn(drinkBookmarkEntities);

        List<DrinkBookmarkEntity> foundDrinkBookmarks = findDrinkBookMarkRepository.findDrinkBookMarkByMemberId(memberId);

        assertThat(foundDrinkBookmarks).isEqualTo(drinkBookmarkEntities);
        assertThat(foundDrinkBookmarks.get(0).getMember()).isEqualTo(member);
        assertThat(foundDrinkBookmarks.get(0).getDrink()).isEqualTo(drink);
    }

    @Test
    void 이미_즐겨찾기에_등록된_음료인_경우_Exception을_응답한다() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .build();

        DrinkEntity drink = DrinkEntity.builder()
                .id(1L)
                .name("아메리카노")
                .sugar(50)
                .build();

        when(drinkBookMarkJpaRepository.existsByMemberAndDrink(member, drink)).thenReturn(true);

        assertThatThrownBy(() -> findDrinkBookMarkRepository.validateAlreadyDrinkBookMarked(member, drink))
                .isInstanceOf(AlreadyDrinkBookMarked.class);
    }

    @Test
    void 음료를_새롭게_즐겨찾기_하는_경우_Exception을_응답하지_않는다() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .build();

        DrinkEntity drink = DrinkEntity.builder()
                .id(1L)
                .name("아메리카노")
                .sugar(50)
                .build();

        when(drinkBookMarkJpaRepository.existsByMemberAndDrink(member, drink)).thenReturn(false);

        assertDoesNotThrow(() -> findDrinkBookMarkRepository.validateAlreadyDrinkBookMarked(member, drink));
    }

}