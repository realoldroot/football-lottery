package com.artemis.lottery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

/**
 * 足球队
 *
 * @author zhengenshen
 * @date 2018-05-08 10:25
 */

@Data
public class FootballTeam {

    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 参赛球员
     */
    private List<String> players;

    /**
     * 球队名称
     */
    private String[] teams;

    /**
     * 数据状态
     */
    private int status;

    /**
     * 中奖的球员
     */
    private Set<String> winners;

    /**
     * 中奖的球队
     */
    private String winnerTeam;

    /**
     * 开奖金额
     */
    private int lotteryAmount;

    /**
     * 单笔金额
     */
    private int singleAmount;

    private int userCount;
}
