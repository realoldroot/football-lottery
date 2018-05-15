package com.artemis.lottery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;

/**
 * 异常处理器
 *
 * @author zhengenshen
 * @date 2018-05-11 14:46
 */
@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "账号或密码错误")
    public void entityNotFoundException() {
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "账号或密码错误")
    public void authException() {

    }
}
