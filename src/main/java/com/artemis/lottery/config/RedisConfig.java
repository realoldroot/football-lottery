package com.artemis.lottery.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author zhengenshen
 * @date 2018-05-18 15:39
 */

@Configuration
public class RedisConfig {

    // @Bean
    // JedisConnectionFactory jedisConnectionFactory() {
    //     return new JedisConnectionFactory();
    // }
    //
    // @Bean("intRedis")
    // public RedisTemplate<String, Integer> redisTemplate() {
    //     RedisTemplate<String, Integer> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory());
    //     template.setKeySerializer(new StringRedisSerializer());
    //     return template;
    // }
}
