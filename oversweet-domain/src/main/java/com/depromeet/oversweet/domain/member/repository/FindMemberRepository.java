package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

/**
 * 유저 정보 조회 Interface
 */
public interface FindMemberRepository {
    MemberEntity findById(final Long memberId);
}
