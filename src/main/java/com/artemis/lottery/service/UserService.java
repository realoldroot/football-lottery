package com.artemis.lottery.service;

import com.artemis.lottery.domain.User;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 14:13
 */

public interface UserService extends BaseService<User> {

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     * @throws Exception e
     */
    User login(String username, String password) throws Exception;

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param sms      短信验证码
     * @param nickname 昵称
     * @throws Exception e
     */
    User register(String username, String password, int sms, String nickname) throws Exception;
}
