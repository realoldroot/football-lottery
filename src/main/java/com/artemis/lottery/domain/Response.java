package com.artemis.lottery.domain;

import lombok.Data;

/**
 * 响应
 *
 * @author zhengenshen
 * @date 2018-05-10 11:02
 */
@Data
public class Response {

    private int code;

    private String message;

    private Object data;

    public Response(Object data) {
        this.code = 0;
        this.message = "成功";
        this.data = data;
    }

    public Response() {
    }
}
