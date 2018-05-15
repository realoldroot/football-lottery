package com.artemis.lottery.web;

import com.artemis.lottery.domain.User;
import com.artemis.lottery.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统
 *
 * @author zhengenshen
 * @date 2018-05-11 14:48
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody UserParams params) throws Exception {

        User response = userService.login(params.getUsername(), params.getPassword());

        log.debug("成功 登陆 {}", response);

        return response;

    }

    @PostMapping("/register")
    public void register(@RequestBody UserParams params) throws Exception {

        userService.register(params.getUsername(), params.getPassword(), params.getSms(),params.getNickname());
    }

    @PostMapping("/sms")
    public User sms(@RequestBody Map<String, String> map) {
        //TODO
        return null;
    }

    @Data
    public class UserParams {

        private String username;
        private String password;
        private String nickname;
        private int sms;
    }

}
