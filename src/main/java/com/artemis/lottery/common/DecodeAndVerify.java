package com.artemis.lottery.common;

import lombok.extern.slf4j.Slf4j;

import javax.security.auth.message.AuthException;

/**
 * @author zhengenshen
 * @date 2018-05-14 10:00
 */

@Slf4j
public class DecodeAndVerify {

    /**
     * 解码并且校验
     *
     * @param password 密码
     * @param salt     盐
     * @param hash     密码哈希值
     * @throws Exception 解码错误
     */
    public static void execute(String password, String salt, String hash) throws Exception {
        String decodePswd;
        try {
            decodePswd = RSATools.decode(password);
        } catch (Exception e) {
            log.error("RSA解密失败");
            throw new AuthException("用户名密码错误");
        }
        boolean flag = PasswordHash.validatePassword(decodePswd, hash, salt);
        if (!flag) {
            log.error("PASSWORD_HASH解密失败");
            throw new AuthException("用户名密码错误");
        }

    }

    public static String decode(String password) throws Exception {
        try {
            return RSATools.decode(password);
        } catch (Exception e) {
            log.error("RSA解密失败");
            throw new Exception("密码错误");
        }
    }
}
