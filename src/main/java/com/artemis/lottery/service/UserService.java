package com.artemis.lottery.service;

import com.artemis.lottery.domain.User;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 14:13
 */

public interface UserService extends BaseService<User> {

    User login(User user) throws Exception;

}
