package com.artemis.lottery.domain;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 开奖结果
 *
 * @author zhengenshen
 * @date 2018-05-08 16:19
 */
@Data
public class LotteryResult {

    private List<String> players;

    private String[] teams;

    private Set<String> winners;

    private String winnerTeam;

    private List<String> users;

}
