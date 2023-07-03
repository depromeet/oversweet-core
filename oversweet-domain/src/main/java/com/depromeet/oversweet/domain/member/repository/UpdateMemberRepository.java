package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

public interface UpdateMemberRepository {

    void saveMember(MemberEntity member);

    void removeMember(Long id);
}
