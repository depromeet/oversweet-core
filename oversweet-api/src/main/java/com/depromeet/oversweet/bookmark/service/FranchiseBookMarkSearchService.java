package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.bookmark.dto.FranchiseBookMarkedResponseDto;
import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.FindFranchiseBookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 프랜차이즈 즐겨찾기 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class FranchiseBookMarkSearchService {

    private final FindFranchiseBookMarkRepository findFranchiseBookMarkRepository;

    /**
     * 유저가 즐겨 찾기한 프랜차이즈 목록 조회
     *
     * @param memberId 유저 ID
     * @return 즐겨찾기한 프랜차이즈 목록
     */
    public FranchiseBookMarkedResponseDto searchFranchiseBookMarked(final Long memberId) {
        final List<FranchiseBookmarkEntity> bookMarks = findFranchiseBookMarkRepository.findByMemberId(memberId);
        return new FranchiseBookMarkedResponseDto(bookMarks);
    }
}
