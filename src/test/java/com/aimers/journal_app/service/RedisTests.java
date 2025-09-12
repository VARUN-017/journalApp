package com.aimers.journal_app.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class RedisTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Test
    @Disabled
    void testSendEmail(){
        redisTemplate.opsForValue().set("email","varun@email.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 10;
    }
}
