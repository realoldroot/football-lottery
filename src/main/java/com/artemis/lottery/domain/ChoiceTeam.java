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

    private String username;

    private String teamName;

    private Set<String> playerNumbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<String> getPlayerNumbers() {
        return playerNumbers;
    }

    public void setPlayerNumbers(Set<String> playerNumbers) {
        this.playerNumbers = playerNumbers;
    }
}
