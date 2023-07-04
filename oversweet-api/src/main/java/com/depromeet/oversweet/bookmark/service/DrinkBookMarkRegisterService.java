package com.depromeet.oversweet.bookmark.service;

import com.depromeet.oversweet.domain.bookmark.repository.FindDrinkBookMarkRepository;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.drink.repository.RegisterDrinkBookMarkRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 음료 북마크 등록 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkBookMarkRegisterService {

    private final FindMemberRepository findMemberRepository;
    private final FindDrinkRepository findDrinkRepository;
    private final FindDrinkBookMarkRepository findDrinkBookMarkRepository;
    private final RegisterDrinkBookMarkRepository registerDrinkBookMarkRepository;

    public void register(final Long memberId, final Long drinkId) {
        // 즐겨 찾기 위한 유저 조회
        final MemberEntity member = findMemberRepository.findById(memberId);

        // 음료가 존재하는지 확인한다.
        final DrinkEntity drink = findDrinkRepository.findDrinkById(drinkId);

        // 이미 해당 음료가 즐겨찾기에 이미 등록되어 있는지 확인한다.
        findDrinkBookMarkRepository.validateAlreadyDrinkBookMarked(member, drink);

        // 해당 프랜차이즈를 즐겨찾기에 등록한다.
        registerDrinkBookMarkRepository.saveDrinkBookmark(member, drink);
    }
}
