package com.artemis.lottery.service.impl;

import com.artemis.lottery.common.MD5Tools;
import com.artemis.lottery.common.RSATools;
import com.artemis.lottery.common.TokenTools;
import com.artemis.lottery.domain.LoginUsers;
import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.domain.RespUser;
import com.artemis.lottery.repository.LoginUsersRepository;
import com.artemis.lottery.service.LoginUsersService;
import com.artemis.lottery.service.PubIntegralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:48
 */

@Service
public class LoginUsersServiceImpl extends AbstractBaseService<LoginUsersRepository, LoginUsers> implements LoginUsersService {

    @Autowired
    private PubIntegralsService pubIntegralsService;

    @Autowired
    private TokenTools tokenTools;

    @Override
    public RespUser login(String username, String password) throws Exception {
        String decode;
        if (password.length() > 20) {
            decode = RSATools.decode(password);
            decode = MD5Tools.crypt(decode);
        } else {
            decode = MD5Tools.crypt(password);
        }

        LoginUsers lu = r.findByBcPhoneAndPassword(username, decode)
                .orElseThrow(() -> new EntityNotFoundException("账号或密码错误"));

        PubIntegrals pi = pubIntegralsService.findByBcUser(username);

        RespUser respUser = new RespUser();

        respUser.setUsername(username);
        respUser.setGameCredits(pi.getGameCredits());
        respUser.setPresentExp(pi.getPresentExp());
        respUser.setToken(tokenTools.build(lu.getBcPhone()));

        return respUser;
    }
}
