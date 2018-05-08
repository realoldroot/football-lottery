package com.artemis.lottery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

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
    private List<String> winners;

    /**
     * 中奖的球队
     */
    private String winnerTeam;


}
