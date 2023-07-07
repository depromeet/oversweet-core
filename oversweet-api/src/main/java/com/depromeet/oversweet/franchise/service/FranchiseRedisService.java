package com.depromeet.oversweet.franchise.service;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindAllFranchiseRepository;
import com.depromeet.oversweet.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FranchiseRedisService {
    private static final String FRANCHISE = "Franchise";
    private final RedisRepository redisRepository;
    private final FindAllFranchiseRepository findAllFranchiseRepository;

    public void createFranchiseAtRedis(){
        final List<FranchiseEntity> findFranchises = findAllFranchiseRepository.findAll();
        final List<FranchiseInfo> franchiseInfos = findFranchises.stream()
                .map(FranchiseInfo::of)
                .toList();
        redisRepository.saveData(FRANCHISE, franchiseInfos);
    }

    public List<FranchiseInfo> getFranchiseAtRedis(){
        return redisRepository.getData(FRANCHISE, FranchiseInfo.class);
    }
}
