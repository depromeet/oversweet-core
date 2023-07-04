package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.member.NotFoundMemberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMemberRepositoryImplTest {
    @Mock
    private MemberJpaRepository memberJpaRepository;

    @InjectMocks
    private FindMemberRepositoryImpl findMemberRepository;

    @Test
    void 유저_조회_성공() {
        Long memberId = 1L;
        MemberEntity member = MemberEntity.builder()
                .id(memberId)
                .age(20)
                .email("test1@gmail.com")
                .build();

        when(memberJpaRepository.findById(memberId)).thenReturn(Optional.of(member));

        MemberEntity foundMember = findMemberRepository.findById(memberId);

        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void 유저를_찾지_못하는_경우() {
        Long memberId = 1L;

        when(memberJpaRepository.findById(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findMemberRepository.findById(memberId))
                .isInstanceOf(NotFoundMemberException.class);
    }
}