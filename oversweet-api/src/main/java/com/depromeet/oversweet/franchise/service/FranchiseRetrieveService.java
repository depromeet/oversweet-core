package com.depromeet.oversweet.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.bookmark.repository.FindFranchiseBookMarkRepository;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseSearchRepository;
import com.depromeet.oversweet.domain.record.dto.RankingDrink;
import com.depromeet.oversweet.domain.record.repository.FindRecordsRepository;
import com.depromeet.oversweet.drink.vo.LocalDateTimeInfo;
import com.depromeet.oversweet.franchise.dto.response.FranchiseInfoWithScrapStatus;
import com.depromeet.oversweet.franchise.dto.response.MostPopularDrinkResponse;

import lombok.RequiredArgsConstructor;

/**
 * 프랜차이즈 조회 서비스
 */
@Service
@RequiredArgsConstructor
public class FranchiseRetrieveService {

    private final FindFranchiseSearchRepository findFranchiseSearchRepository;
    private final FindRecordsRepository findRecordsRepository;
    private final FindFranchiseBookMarkRepository findFranchiseBookMarkRepository;

    /**
     * 프랜차이즈 기준 가장 인기있는 음료 3개 조회 (어제 기준)
     * 1, 2, 3위 음료 조회
     *
     * @param franchiseId 프랜차이즈 ID
     */
    public MostPopularDrinkResponse searchPopularDrinkByFranchiseId(final Long memberId, final Long franchiseId) {
        // 프랜차이즈 조회
        FranchiseEntity franchise = findFranchiseSearchRepository.findFranchiseById(franchiseId);

        // 프랜차이즈 즐겨찾기 여부 조회
        boolean isBookMarked = findFranchiseBookMarkRepository.isFranchiseBookMarkedByMemberId(memberId, franchiseId);


        // 가장 많이 기록된 음료 3개 조회
        LocalDateTimeInfo localDateTimeInfo = LocalDateTimeInfo.getYesterday();
        List<RankingDrink> topDrinks = findRecordsRepository
                .findPopularDrinkRecordsByFranchiseId(franchiseId, localDateTimeInfo.startDateTime(), localDateTimeInfo.endDateTime());

        return MostPopularDrinkResponse.of(topDrinks, FranchiseInfoWithScrapStatus.of(franchise, isBookMarked));
    }
}
