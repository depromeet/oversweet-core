package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UpdateMemberRepositoryImpl implements UpdateMemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional
    public void saveMember(MemberEntity member) {
        memberJpaRepository.save(member);
    }

    @Override
    @Transactional
    public void removeMember(Long id) {
        memberJpaRepository.deleteById(id);
    }
}
