package com.artemis.lottery.web;

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


    @ApiOperation(value = "登陆", notes = "登陆只需要传username和password就可以了，密码请使用RSA加密")
    @PostMapping("/login")
    public RespUser login(@RequestBody UserParams params) throws Exception {

        return loginUsersService.login(params.username, params.password);

    }

    @Data
    private static class UserParams {

        private String username;
        private String password;
        private String nickname;
        private int sms;
    }

}
