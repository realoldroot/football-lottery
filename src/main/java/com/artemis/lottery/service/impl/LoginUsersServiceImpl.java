package com.artemis.lottery.service.impl;

import com.artemis.lottery.common.MD5Tools;
import com.artemis.lottery.domain.LoginUsers;
import com.artemis.lottery.repository.LoginUsersRepository;
import com.artemis.lottery.service.LoginUsersService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:48
 */

@Service
public class LoginUsersServiceImpl extends AbstractBaseService<LoginUsersRepository, LoginUsers> implements LoginUsersService {


    @Override
    public LoginUsers login(String username, String password) {

        String decode = MD5Tools.crypt(password);

        return r.findByBcPhoneAndPassword(username, decode)
                .orElseThrow(() -> new EntityNotFoundException("用户不存在"));
    }
}
