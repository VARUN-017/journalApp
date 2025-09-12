package com.aimers.journal_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);  // Convert JSON String → Java object (POJO)
        } catch (Exception e){
            log.error("Exception e");
            return null;
        }
    }


    public void set(String key, Object o, Long ttl) {
        try {
            redisTemplate.opsForValue().set("spring_test_key", "spring_hello");
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);  // 	Convert Java object → JSON String
            redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
            log.info("Key '{}' set with TTL {}s. Value: {}", key, ttl, jsonValue);
        } catch (Exception e){
            log.error("Failed to set key '{}': {}", key, e.getMessage());
        }
    }
}
