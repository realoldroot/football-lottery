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

    private int status;

    private String message;

    private Object data;

    public Response(Object data) {
        this.status = 0;
        this.message = "成功";
        this.data = data;
    }

    public Response() {
    }

    public static Response error(String errMessage) {
        Response response = new Response();
        response.setStatus(1);
        response.setMessage(errMessage);
        return response;
    }
}
