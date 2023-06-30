package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.bookmark.dto.response.DrinkBookMarkedResponseDto;
import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.FindDrinkBookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 음료 즐겨 찾기 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkBookMarkSearchService {

    private final FindDrinkBookMarkRepository findDrinkBookMarkRepository;

    /**
     * 유저가 즐겨 찾기한 음료 목록 조회
     *
     * @param memberId 유저 ID
     * @return 즐겨 찾기한 음료 목록 응답 DTO
     */
    public DrinkBookMarkedResponseDto searchDrinkBookMarked(final Long memberId) {
        final List<DrinkBookmarkEntity> bookmarks = findDrinkBookMarkRepository.findDrinkBookMarkByMemberId(memberId);
        return new DrinkBookMarkedResponseDto(bookmarks);
    }

}
