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

    //{"type":0,"username":"18310860399","token":"e45f4398edd97664c4604cca515dffd4908550e903a650a9829ef9183488dcb3"}
}
