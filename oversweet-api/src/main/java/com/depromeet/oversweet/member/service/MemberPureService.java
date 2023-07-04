package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import com.depromeet.oversweet.domain.member.repository.UpdateMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberPureService {

    private final FindMemberRepository findMemberRepository;
    private final UpdateMemberRepository updateMemberRepository;

    public Optional<MemberEntity> searchMemberByNickname(final String nickname) {
        return findMemberRepository.findMemberByNickname(nickname);
    }

    public Optional<MemberEntity> searchMemberBySocialId(final String socialId) {
        return findMemberRepository.findMemberBySocialId(socialId);
    }

    public void saveMember(MemberEntity member) {
        updateMemberRepository.saveMember(member);
    }

    public void modifyMember(MemberEntity member) {
        updateMemberRepository.saveMember(member);
    }

    public MemberEntity searchMemberById(Long id) {
        return findMemberRepository.findMemberById(id);
    }

    public void removeMember(Long id) {
        updateMemberRepository.removeMember(id);
    }
}
