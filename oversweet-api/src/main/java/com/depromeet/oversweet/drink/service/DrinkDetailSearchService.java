package com.depromeet.oversweet.drink.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.depromeet.oversweet.bookmark.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.dto.DrinkInfoWithScrapStatus;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkDetailInfoResponse;

import lombok.RequiredArgsConstructor;

/**
 * 음료 상세 정보를 보기 위한 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkDetailSearchService {

    private final FindDrinkRepository findDrinkRepository;
    private final FindFranchiseRepository findFranchiseRepository;

    /**
     * 음료 상세 정보 조회
     *
     * @param memberId    API 접근자 memberId
     * @param franchiseId 프랜차이즈 ID
     * @param drinkName   음료 이름
     * @return 음료 상세 정보
     */
    public DrinkDetailInfoResponse retrieveDrinkDetail(Long memberId, Long franchiseId, String drinkName) {
        // 프랜차이즈 ID와 음료 이름으로 => 음료가 존재하는지 학인 => 없으면 exception
        findDrinkRepository.checkDrinkExist(franchiseId, drinkName);

        // 프랜차이즈 응답을 위한 조회
        FranchiseEntity franchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // 음료 정보를 조회 (음료 정보 & 사이즈별 즐겨 찾기 여부)
        List<DrinkInfoWithScrapStatus> drinkDetails = findDrinkRepository.findDrinkDetail(memberId, franchise.getId(), drinkName);

        return new DrinkDetailInfoResponse(new FranchiseInfo(franchise), drinkDetails);
    }


}
