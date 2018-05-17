package com.artemis.lottery.config;

import com.artemis.lottery.common.JsonTools;
import com.artemis.lottery.domain.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 包装response
 *
 * @author zhengenshen
 * @date 2018-05-11 15:04
 */

@Slf4j
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof Response) {
            return body;
        }

        // 这一段处理 是为了 防止 controller 返回 String 类型 而被 StringMessageConverter 处理
        // 引用解决方式 https://my.oschina.net/u/1757225/blog/1543715
        // 重写StringMessageConverter 方式 https://stackoverflow.com/questions/44121648/controlleradvice-responsebodyadvice-failed-to-enclose-a-string-response

        if (body instanceof String) {
            try {
                return (JsonTools.toJson(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return new Response(body);
    }
}
