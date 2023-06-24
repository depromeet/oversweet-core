package com.depromeet.oversweet.domain.franchise.service;

import com.depromeet.oversweet.domain.franchise.repository.FranchiseEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchisePureService {

    private final FranchiseEntityRepository franchiseEntityRepository;

    public String getFranchiseName(Long franchiseId) {
        return franchiseEntityRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("프랜차이즈가 존재하지 않습니다."))
                .getName();
    }


}
