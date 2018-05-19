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
     * 当前实际金额
     */
    private float realAmount;

    /**
     * 预设金额
     */
    private float settingAmount;
    /**
     * 开奖金额
     */
    private float totalAmount;


    /**
     * 几注
     */
    private int bet;

    /**
     * 单笔金额
     */
    private float singleAmount;

    private List<String> users;
}
