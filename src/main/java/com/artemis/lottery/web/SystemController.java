package com.artemis.lottery.web;

import com.artemis.lottery.common.SMSTools;
import com.artemis.lottery.common.TokenTools;
import com.artemis.lottery.domain.RespUser;
import com.artemis.lottery.domain.User;
import com.artemis.lottery.service.UserService;
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
    private UserService userService;

    @Autowired
    private TokenTools tokenTools;

    @Autowired
    private SMSTools smsTools;

    @PostMapping("/login")
    public RespUser login(@RequestBody UserParams params) throws Exception {

        User user = userService.login(params.getUsername(), params.getPassword());

        log.debug("成功 登陆 {}", user);

        RespUser r = new RespUser(user);
        r.setToken(tokenTools.build(user.getUsername()));

        return r;

    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public RespUser register(@RequestBody UserParams params) throws Exception {

        User user = userService.register(params.getUsername(), params.getPassword(), params.getSms(), params.getNickname());

        RespUser r = new RespUser(user);
        r.setToken(tokenTools.build(user.getUsername()));

        return r;
    }

    @ApiOperation("发送短信")
    @PostMapping("/sms")
    public UserParams sms(@RequestBody UserParams params) throws Exception {
        params.setSms(smsTools.sendSms(params.getUsername()));
        return params;
    }

    @Data
    private static class UserParams {

        private String username;
        private String password;
        private String nickname;
        private int sms;
    }

}
