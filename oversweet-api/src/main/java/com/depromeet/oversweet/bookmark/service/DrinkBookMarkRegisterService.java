package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.bookmark.dto.response.DrinkBookMarkedResponseDto;
import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import com.depromeet.oversweet.domain.bookmark.repository.FindDrinkBookMarkRepository;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.drink.repository.UpdateDrinkBookMarkRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 음료 북마크 등록 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkBookMarkRegisterService {

    private final FindMemberRepository findMemberRepository;
    private final FindDrinkRepository findDrinkRepository;
    private final FindDrinkBookMarkRepository findDrinkBookMarkRepository;
    private final UpdateDrinkBookMarkRepository updateDrinkBookMarkRepository;

    /**
     * 음료 즐겨 찾기 등록
     *
     * @param memberId Member Entity ID
     * @param drinkId 즐겨찾기로 등록할 Drink Entity ID
     */
    public void register(final Long memberId, final Long drinkId) {
        // 유저 조회
        final MemberEntity member = findMemberRepository.findById(memberId);

        // 음료가 존재하는지 확인한다.
        final DrinkEntity drink = findDrinkRepository.findDrinkById(drinkId);

        // 이미 해당 음료가 즐겨찾기에 이미 등록되어 있는지 확인한다.
        findDrinkBookMarkRepository.validateAlreadyDrinkBookMarked(member, drink);

        // 해당 음료를 즐겨찾기에 등록한다.
        updateDrinkBookMarkRepository.saveDrinkBookmark(member, drink);
    }

    /**
     * 음료 즐겨 찾기 해제
     *
     * @param memberId Member Entity ID
     * @param drinkId 즐겨찾기 해제할 Drink Entity ID
     * @return 새롭게 갱신된 즐겨찾기 음료 목록
     */
    public DrinkBookMarkedResponseDto unregister(Long memberId, Long drinkId) {
        // 유저 조회
        final MemberEntity member = findMemberRepository.findById(memberId);

        // 음료가 존재하는지 확인한다.
        final DrinkEntity drink = findDrinkRepository.findDrinkById(drinkId);

        // 해당 음료를 즐겨찾기에서 해제한다.
        updateDrinkBookMarkRepository.deleteDrinkBookmark(member, drink);

        // 새롭게 갱신된 즐겨찾기 음료 목록을 반환한다.
        List<DrinkBookmarkEntity> bookMarks = findDrinkBookMarkRepository.findDrinkBookMarkByMemberId(memberId);

        return new DrinkBookMarkedResponseDto(bookMarks);
    }
}
