package com.artemis.lottery.service.impl;

import com.artemis.lottery.common.DecodeAndVerify;
import com.artemis.lottery.common.PasswordHash;
import com.artemis.lottery.common.SMSTools;
import com.artemis.lottery.domain.User;
import com.artemis.lottery.repository.UserRepository;
import com.artemis.lottery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 14:14
 */

@Service
public class UserServiceImpl extends AbstractBaseService<UserRepository, User> implements UserService {

    @Autowired
    private SMSTools smsTools;

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     * @throws Exception e
     */
    @Override
    public User login(String username, String password) throws Exception {

        User user = r.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("用户不存在"));

        DecodeAndVerify.execute(password, user.getPasswordSalt(), user.getPasswordHash());

        return user;
    }

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param sms      短信验证码
     * @param nickname 昵称
     * @throws Exception e
     */
    @Override
    public void register(String username, String password, int sms, String nickname) throws Exception {

        smsTools.verify(username, sms);

        String[] hash = PasswordHash.createHash(password);

        User user = new User(username, hash[0], hash[1], nickname);

    }

}
