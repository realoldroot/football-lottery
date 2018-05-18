package com.artemis.lottery.domain;

import lombok.Data;

/**
 * @author zhengenshen
 * @date 2018-05-18 10:53
 */

@Data
public class QueryParams {

    private Long no;
    private String username;

    private Integer page;

    private Integer pageSize;

}
