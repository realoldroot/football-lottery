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

    public Set<String> getPlayerNumbers() {
        return playerNumbers;
    }

    public void setPlayerNumbers(Set<String> playerNumbers) {
        this.playerNumbers = playerNumbers;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
