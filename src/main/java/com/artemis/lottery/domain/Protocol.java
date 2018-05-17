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

    //{"type":0,"username":"17600116321","token":"491e7af2f07b1932314b2c3fc9a566cff324739e9e85598f6da85deb6ff5e3d3"}
}
