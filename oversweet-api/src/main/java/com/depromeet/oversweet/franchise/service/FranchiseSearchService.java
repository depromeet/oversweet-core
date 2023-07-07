package com.depromeet.oversweet.franchise.service;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 프랜차이즈 검색 서비스
 */
@Service
@RequiredArgsConstructor
public class FranchiseSearchService {
    private final FindFranchiseSearchRepository findFranchiseSearchRepository;

    public List<FranchiseInfo> getFranchiseByKeyword(final String keyword) {
        final List<FranchiseEntity> findFranchise = findFranchiseSearchRepository.findFranchiseByKeyword(keyword);
        return findFranchise.stream()
                .map(franchise -> FranchiseInfo.of(franchise))
                .toList();
    }
}
