package com.artemis.lottery.web;

import com.artemis.lottery.common.TokenTools;
import com.artemis.lottery.domain.LoginUsers;
import com.artemis.lottery.domain.RespUser;
import com.artemis.lottery.service.LoginUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统
 *
 * @author zhengenshen
 * @date 2018-05-11 14:48
 */
@Api
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {


    @Autowired
    private LoginUsersService loginUsersService;

    @Autowired
    private TokenTools tokenTools;


    @ApiOperation("登陆")
    @PostMapping("/login")
    public RespUser login(@RequestBody UserParams params) throws Exception {

        LoginUsers login = loginUsersService.login(params.username, params.password);

        log.debug("成功 登陆 {}", login);

        RespUser r = new RespUser();
        r.setToken(tokenTools.build(login.getBcPhone()));

        return r;

    }

    @Data
    private static class UserParams {

        private String username;
        private String password;
        private String nickname;
        private int sms;
    }

}
