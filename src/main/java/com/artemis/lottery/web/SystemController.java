package com.artemis.lottery.web;

import com.artemis.lottery.domain.User;
import com.artemis.lottery.service.UserService;
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
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User user) throws Exception {

        User response = userService.login(user);

        log.debug("登陆 {}", response);

        return response;

    }

}
