package com.artemis.lottery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * 选择队伍
 *
 * @author zhengenshen
 * @date 2018-05-08 11:34
 */
@Data
public class ChoiceTeam {

    @Id
    private Long id;

    /**
     * 用户标识
     */
    private String username;

    /**
     * 期号
     */
    private Long no;

    /**
     * 队伍名称
     */
    private String teamName;

    /**
     * 球号
     */
    private Set<String> playerNumbers;

    /**
     * 下注积分
     */
    private Integer score;

    /**
     * 状态 中奖还是未中奖。
     */
    private int status;

}
