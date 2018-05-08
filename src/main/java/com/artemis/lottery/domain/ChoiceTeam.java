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


}
