package com.artemis.lottery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
    public Long id;

    //球员号
    public Set<String> playerNumbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<String> getPlayerNumbers() {
        return playerNumbers;
    }

    public void setPlayerNumbers(Set<String> playerNumbers) {
        this.playerNumbers = playerNumbers;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", playerNumbers=" + playerNumbers +
                '}';
    }
}
