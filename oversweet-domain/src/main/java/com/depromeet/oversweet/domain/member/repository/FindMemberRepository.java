package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

import java.util.Optional;

/**
 * 유저 정보 조회 Interface
 */
public interface FindMemberRepository {

    Optional<MemberEntity> findMemberByNickname(String nickname);

    Optional<MemberEntity> findMemberBySocialId(String socialId);

    MemberEntity findMemberById(Long id);

    MemberEntity findById(final Long memberId);

}
