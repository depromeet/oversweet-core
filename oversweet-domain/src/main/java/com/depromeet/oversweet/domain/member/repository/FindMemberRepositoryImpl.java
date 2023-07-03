package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.member.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_MEMBER;

@Repository
@RequiredArgsConstructor
public class FindMemberRepositoryImpl implements FindMemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberEntity findMemberByNickname(final String nickname) {
        return memberJpaRepository.findByNickname(nickname);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberEntity findMemberBySocialId(final String socialId) {
        return memberJpaRepository.findBySocialId(socialId);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberEntity findMemberById(final Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow(() -> new NotFoundMemberException(NOT_FOUND_MEMBER));
    }

}
