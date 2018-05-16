package com.artemis.lottery.interceptor;

import com.artemis.lottery.common.TokenTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author zhengenshen
 * @date 2018-05-15 17:17
 */
@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenTools tokenTools;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String auth = request.getHeader("auth");
        log.debug("auth    {}", auth);
        return true;
    }
}
