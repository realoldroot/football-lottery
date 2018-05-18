package com.artemis.lottery.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 奖金池
 *
 * @author zhengenshen
 * @date 2018-05-18 15:13
 */

@Service
public class BonusPool {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String key = "BONUS_POOL";


    public void add(int v) {
        redisTemplate.opsForValue().increment(key, v);
    }

    public int get() {
        String v = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(v)) {
            return Integer.parseInt(v);
        } else {
            return 0;
        }
    }
}
