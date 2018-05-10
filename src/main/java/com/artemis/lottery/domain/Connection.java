package com.artemis.lottery.domain;

import lombok.Data;

/**
 * @author zhengenshen
 * @date 2018-05-10 10:36
 */

@Data
public class Connection {

    private int type;

    private String username;

    private String token;

    //{"type":0,"username":"123123","token":"aaaaaaa"}
}
