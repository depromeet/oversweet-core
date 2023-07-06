package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSearchService {

    private final FindMemberRepository findMemberRepository;

    public Optional<MemberEntity> searchMemberByNickname(final String nickname) {
        return findMemberRepository.findMemberByNickname(nickname);
    }

    public Optional<MemberEntity> searchMemberBySocialId(final String socialId) {
        return findMemberRepository.findMemberBySocialId(socialId);
    }

    public MemberEntity searchMemberById(Long id) {
        return findMemberRepository.findMemberById(id);
    }
}
