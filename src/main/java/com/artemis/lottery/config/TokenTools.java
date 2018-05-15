package com.artemis.lottery.config;

import com.artemis.lottery.common.SHATools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * token工具
 *
 * @author zhengenshen
 * @date 2018-05-15 16:27
 */
@Component
public class TokenTools {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String TOKEN_KEY = "TOKEN_";

    public String build(String username) {
        String value = SHATools.SHA256(username + System.currentTimeMillis());
        redisTemplate.opsForValue().set(TOKEN_KEY + username, value);
        return value;
    }

    public void remove(String username) {
        redisTemplate.delete(TOKEN_KEY + username);
    }

    public boolean has(String username) {
        return redisTemplate.hasKey(TOKEN_KEY + username);
    }
}
