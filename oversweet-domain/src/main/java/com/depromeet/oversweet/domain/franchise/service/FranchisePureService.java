package com.depromeet.oversweet.domain.franchise.service;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FranchiseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 프랜차이즈에 대한 순수 Service.
 * 의존성은 FranchiseEntityRepository 만 갖는다.
 * 주로 Optional 에 대한 처리를 하고 온전한 Entity 를 반환한다.
 */
@Service
@RequiredArgsConstructor
public class FranchisePureService {

    private final FranchiseJpaRepository franchiseJpaRepository;

    /**
     * 프랜차이즈 이름을 반환한다.
     * @param franchiseId 프랜차이즈 아이디
     * @return 프랜차이즈 이름
     */
    public String getFranchiseName(Long franchiseId) {
        return franchiseJpaRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("프랜차이즈가 존재하지 않습니다."))
                .getName();
    }

    /**
     * 프랜차이즈 이름 목록을 반환한다.
     * @param franchiseIds 프랜차이즈 아이디 목록
     * @return 프랜차이즈 이름 목록
     */
    public List<String> getFranchiseNames(List<Long> franchiseIds) {
        return franchiseJpaRepository.findAllByFranchiseIds(franchiseIds)
                .stream()
                .map(FranchiseEntity::getName)
                .collect(Collectors.toList());
    }


}
