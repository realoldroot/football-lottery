package com.artemis.lottery.service;

import com.artemis.lottery.domain.LoginUsers;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:47
 */

public interface LoginUsersService extends BaseService<LoginUsers> {

    LoginUsers login(String username, String password);
}
