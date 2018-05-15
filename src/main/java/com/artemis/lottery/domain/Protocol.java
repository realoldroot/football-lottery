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

    //{"type":0,"username":"123123","token":"a1c6132291083fc95c4770b26d6581d569fa461192a675bd3874f157ad54e446"}
}
