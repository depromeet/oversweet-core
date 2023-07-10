package com.depromeet.oversweet.drink.service;

import com.depromeet.oversweet.domain.drink.dto.DrinkSimpleInfo;
import com.depromeet.oversweet.domain.drink.repository.FindAllDrinkRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkRedisInfo;
import com.depromeet.oversweet.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 음료 정보를 레디스에 저장하거나 가져오는 서비스
 */
@Service
@RequiredArgsConstructor
public class DrinkRedisService {

    private static final String DRINK = "drink";
    private final RedisRepository redisRepository;

    private final FindAllDrinkRepository findAllDrinkRepository;

    public List<DrinkRedisInfo> getDrinks() {
        List<DrinkRedisInfo> drinkInfos = redisRepository.getData(DRINK, DrinkRedisInfo.class);
        if (drinkInfos == null || drinkInfos.isEmpty()) {
            final List<DrinkSimpleInfo> findDrinks = findAllDrinkRepository.findAll();
            drinkInfos = findDrinks.stream()
                    .map(DrinkRedisInfo::of)
                    .toList();
            redisRepository.saveData(DRINK, drinkInfos);
        }
        return drinkInfos;
    }
}
