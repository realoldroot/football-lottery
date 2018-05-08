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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String[] getTeams() {
        return teams;
    }

    public void setTeams(String[] teams) {
        this.teams = teams;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getWinners() {
        return winners;
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }
}
