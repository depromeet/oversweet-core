package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.bookmark.dto.response.FranchiseBookMarkedResponseDto;
import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.FindFranchiseBookMarkRepository;
import com.depromeet.oversweet.domain.bookmark.repository.RegisterFranchiseBookMarkRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 프랜차이즈 북마크 등록 서비스.
 */
@Service
@RequiredArgsConstructor
public class FranchiseBookMarkRegisterService {

    private final FindMemberRepository findMemberRepository;
    private final FindFranchiseRepository findFranchiseRepository;
    private final FindFranchiseBookMarkRepository findFranchiseBookMarkRepository;
    private final RegisterFranchiseBookMarkRepository registerFranchiseBookMarkRepository;

    /**
     * 프랜차이즈 북마크를 등록한다.
     *
     * @param memberId    회원 ID
     * @param franchiseId 프랜차이즈 ID
     */
    public void register(Long memberId, Long franchiseId) {
        // 즐겨 찾기 위한 유저 조회
        final MemberEntity member = findMemberRepository.findById(memberId);

        // 프랜차이즈가 존재하는지 확인한다.
        final FranchiseEntity franchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 이미 해당 프랜차이즈가 즐겨찾기에 이미 등록되어 있는지 확인한다.
        findFranchiseBookMarkRepository.validateAlreadyFranchiseBookMarked(member, franchise);

        // 해당 프랜차이즈를 즐겨찾기에 등록한다.
        registerFranchiseBookMarkRepository.saveFranchiseBookmark(member, franchise);
    }

    /**
     * 프랜차이즈 북마크를 해제한다.
     *
     * @param memberId 유저 고유 ID
     * @param franchiseId 프랜차이즈 고유 ID
     * @return 최신 즐겨찾기 목록
     */
    public FranchiseBookMarkedResponseDto unregister(Long memberId, Long franchiseId) {
        // 즐겨 찾기 위한 유저 조회
        final MemberEntity member = findMemberRepository.findById(memberId);

        // 프랜차이즈가 존재하는지 확인한다.
        final FranchiseEntity franchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 해당 프랜차이즈를 즐겨찾기에서 삭제한다.
        registerFranchiseBookMarkRepository.deleteFranchiseBookmark(member, franchise);

        // 최신 즐겨찾기 목록을 반환한다.
        final List<FranchiseBookmarkEntity> bookMarks = findFranchiseBookMarkRepository.findFranchiseBookMarkByMemberId(memberId);

        return new FranchiseBookMarkedResponseDto(bookMarks);
    }
}
