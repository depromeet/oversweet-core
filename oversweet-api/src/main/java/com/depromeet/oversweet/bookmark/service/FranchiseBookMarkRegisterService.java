package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.domain.bookmark.repository.FindFranchiseBookMarkRepository;
import com.depromeet.oversweet.domain.bookmark.repository.RegisterFranchiseBookMarkRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 프랜차이즈 북마크 등록 서비스.
 */
@Service
@RequiredArgsConstructor
public class FranchiseBookMarkRegisterService {

    // TODO : 유저 조회하는 Repository 제준님꺼 코드 받아서 진행 (의존성 주입 필요)
    private final FindMemberRepository findMemberRepository;
    private final FindFranchiseRepository findFranchiseRepository;
    private final RegisterFranchiseBookMarkRepository registerFranchiseBookMarkRepository;
    private final FindFranchiseBookMarkRepository findFranchiseBookMarkRepository;

    /**
     * 프랜차이즈 북마크를 등록한다.
     *
     * @param memberId    회원 ID
     * @param franchiseId 프랜차이즈 ID
     */
    public void register(Long memberId, Long franchiseId) {
        // 즐겨 찾기 위한 유저 조회
        MemberEntity member = findMemberRepository.findById(memberId);

        // 프랜차이즈가 존재하는지 확인한다.
        FranchiseEntity franchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 이미 해당 프랜차이즈가 즐겨찾기에 이미 등록되어 있는지 확인한다.
        findFranchiseBookMarkRepository.validateAlreadyFranchiseBookMarked(member, franchise);

        // 해당 프랜차이즈를 즐겨찾기에 등록한다.
        registerFranchiseBookMarkRepository.register(member, franchise);
    }

}
