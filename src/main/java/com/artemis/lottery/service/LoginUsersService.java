package com.artemis.lottery.service;

import com.artemis.lottery.domain.LoginUsers;
import com.artemis.lottery.domain.RespUser;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:47
 */

public interface LoginUsersService extends BaseService<LoginUsers> {

    RespUser login(String username, String password) throws Exception;
}
