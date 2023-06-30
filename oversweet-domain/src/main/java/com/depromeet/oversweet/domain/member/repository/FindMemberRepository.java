package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

public interface FindMemberRepository {
    MemberEntity findById(final Long memberId);
}
