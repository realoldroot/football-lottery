package com.artemis.lottery.domain;

import lombok.Data;

/**
 * @author zhengenshen
 * @date 2018-05-15 16:41
 */

@Data
public class RespUser {

    private String username;
    private String nickname;
    private Integer scores;
    private String token;


    public RespUser() {
    }
}
