package com.artemis.lottery.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
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

    public String get(String username) {
        return redisTemplate.opsForValue().get(TOKEN_KEY + username);
    }

    /**
     * 校验 token是否正确
     *
     * @param username 用户
     * @param token    token
     * @return true false
     */
    public boolean verifyToken(String username, String token) {
        return StringUtils.equals(get(username), token);
    }

    /**
     * 校验签名
     *
     * @param username  用户名
     * @param timestamp 时间戳
     * @param sign      签名
     * @return 校验成功/失败
     */
    public boolean verifyToken(String username, String timestamp, String sign) {
        if (StringUtils.isAnyEmpty(username, timestamp, sign)) {
            log.debug("校验失败，参数都为空");
            return false;
        }
        if (has(username)) {
            String s = SHATools.SHA256(username + get(username) + timestamp);
            return StringUtils.equals(s, sign);
        } else {
            return false;
        }
    }
}
