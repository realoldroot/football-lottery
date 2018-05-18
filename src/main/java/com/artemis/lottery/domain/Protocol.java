package com.artemis.lottery.domain;

import lombok.Data;

/**
 * @author zhengenshen
 * @date 2018-05-10 10:36
 */

@Data
public class Protocol {

    private int type;

    private String username;

    private String token;

    //{"type":0,"username":"上","token":"18ef6f43e0aae41a5f3bc159a27b81c928678d148c1ba277d95d89efc53eef2d"}
}
