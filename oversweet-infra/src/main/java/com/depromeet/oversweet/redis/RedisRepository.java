package com.depromeet.oversweet.redis;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.redis.OverSweetRedisException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RedisRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public <T> List<T> getData(final String key, final Class<T> classType) {
        final String jsonData = (String) redisTemplate.opsForValue().get(key);

        if (StringUtils.hasText(jsonData)) {
            try {
                return objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
            } catch (Exception e) {
                throw new OverSweetRedisException(ErrorCode.OVERSWEET_REDIS_JSON_ERROR);
            }
        }
        return new ArrayList<>();
    }

    public <T> boolean saveData(final String key, final T data) {
        try {
            final String value = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            throw new OverSweetRedisException(ErrorCode.OVERSWEET_REDIS_JSON_ERROR);
        }
    }

}
