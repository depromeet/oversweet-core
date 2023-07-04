package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.bookmark.AlreadyFranchiseBookMarked;
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
class FindFranchiseBookMarkRepositoryImplTest {

    @Mock
    private FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;

    @InjectMocks
    private FindFranchiseBookMarkRepositoryImpl findFranchiseBookMarkRepository;

    @Test
    void 유저_ID로_즐겨찾기한_프랜차이즈_목록_조회() {
        // Given
        Long memberId = 1L;
        MemberEntity member = MemberEntity.builder()
                .id(memberId)
                .age(20)
                .email("test1@gmail.com")
                .build();

        FranchiseEntity franchise = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();

        FranchiseBookmarkEntity bookmarkEntity = FranchiseBookmarkEntity.builder()
                .id(1L)
                .member(member)
                .franchise(franchise)
                .build();

        List<FranchiseBookmarkEntity> expectedBookmarks = Collections.singletonList(bookmarkEntity);

        when(franchiseBookMarkJpaRepository.findByMemberId(memberId)).thenReturn(expectedBookmarks);

        // When
        List<FranchiseBookmarkEntity> actualBookmarks = findFranchiseBookMarkRepository.findFranchiseBookMarkByMemberId(memberId);

        // Then
        assertThat(actualBookmarks).isEqualTo(expectedBookmarks);
    }

    @Test
    void 이미_해당_프랜차이즈를_즐겨찾기한_경우() {
        // Given
        Long memberId = 1L;
        MemberEntity member = MemberEntity.builder()
                .id(memberId)
                .age(20)
                .email("test1@gmail.com")
                .build();

        FranchiseEntity franchise = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();


        // Given
        when(franchiseBookMarkJpaRepository.existsByMemberAndFranchise(member, franchise)).thenReturn(true);

        // Then
        assertThatThrownBy(() -> findFranchiseBookMarkRepository.validateAlreadyFranchiseBookMarked(member, franchise))
                .isInstanceOf(AlreadyFranchiseBookMarked.class);
    }

    @Test
    void 새롭게_즐겨찾기한_경우() {
        // Given
        Long memberId = 1L;
        MemberEntity member = MemberEntity.builder()
                .id(memberId)
                .age(20)
                .email("test1@gmail.com")
                .build();

        FranchiseEntity franchise = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();


        // Given
        when(franchiseBookMarkJpaRepository.existsByMemberAndFranchise(member, franchise)).thenReturn(false);

        // Then
        assertDoesNotThrow(() -> findFranchiseBookMarkRepository.validateAlreadyFranchiseBookMarked(member, franchise));
    }


}