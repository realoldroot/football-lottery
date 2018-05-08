package com.artemis.lottery.domain;

import lombok.Data;

import java.util.Set;

/**
 * 开奖结果
 *
 * @author zhengenshen
 * @date 2018-05-08 16:19
 */
@Data
public class LotteryResult {

    private Set<String> playerNumbers;

    private String teamName;

}
