package com.artemis.lottery.config;

import com.artemis.lottery.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;

/**
 * 异常处理器
 *
 * @author zhengenshen
 * @date 2018-05-11 14:46
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public Response entityNotFoundException(EntityNotFoundException e) {
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public Response authException(AuthException e) {
        return Response.error(e.getMessage());
    }
}
