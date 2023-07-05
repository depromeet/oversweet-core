package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.member.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_MEMBER;

/**
 * 유저 정보 조회 레포지토리
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class FindMemberRepositoryImpl implements FindMemberRepository{

    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<MemberEntity> findMemberByNickname(final String nickname) {
        return memberJpaRepository.findByNickname(nickname);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MemberEntity> findMemberBySocialId(final String socialId) {
        return memberJpaRepository.findBySocialId(socialId);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberEntity findMemberById(final Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow(() -> new NotFoundMemberException(NOT_FOUND_MEMBER));
    }

    @Override
    @Transactional(readOnly = true)
    public MemberEntity findById(final Long memberId) {
       return memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
    }
}
