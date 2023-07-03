package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

public interface FindMemberRepository {

    MemberEntity findMemberByNickname(String nickname);

    MemberEntity findMemberBySocialId(String socialId);

    MemberEntity findMemberById(Long id);
}
