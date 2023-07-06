package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.UpdateMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final UpdateMemberRepository updateMemberRepository;

    public void saveMember(MemberEntity member) {
        updateMemberRepository.saveMember(member);
    }

    public void modifyMember(MemberEntity member) {
        updateMemberRepository.saveMember(member);
    }

}
