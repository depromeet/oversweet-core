package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.member.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FindMemberRepositoryImpl implements FindMemberRepository{

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MemberEntity findById(final Long memberId) {
       return memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
    }
}
