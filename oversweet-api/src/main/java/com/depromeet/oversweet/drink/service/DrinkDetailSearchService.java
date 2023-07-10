package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.bookmark.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.dto.DrinkInfoWithScrapStatus;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseRepository;
import com.depromeet.oversweet.drink.dto.request.DrinkInfoRequest;
import com.depromeet.oversweet.drink.dto.response.DrinkDetailInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param memberId API 접근자 memberId
     * @param request 음료 정보를 확인하기 위해 필요한 값을 담고 있는 request dto
     * @return 음료 상세 정보
     */
    public DrinkDetailInfoResponse retrieveDrinkDetail(Long memberId, DrinkInfoRequest request) {
        // 프랜차이즈 ID와 음료 이름으로 => 음료가 존재하는지 학인 => 없으면 exception
        findDrinkRepository.checkDrinkExist(request.getFranchiseId(), request.getDrinkName());

        // 프랜차이즈 응답을 위한 조회
        FranchiseEntity franchise = findFranchiseRepository.findFranchiseById(request.getFranchiseId());

        // 음료 정보를 조회 (음료 정보 & 사이즈별 즐겨 찾기 여부)
        List<DrinkInfoWithScrapStatus> drinkDetails = findDrinkRepository.findDrinkDetail(memberId, franchise.getId(), request.getDrinkName());

        return new DrinkDetailInfoResponse(new FranchiseInfo(franchise), drinkDetails);
    }


}
