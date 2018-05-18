package com.artemis.lottery.domain;

import lombok.Data;

import java.util.Collection;

/**
 * @author zhengenshen
 * @date 2018-05-15 16:41
 */

@Data
public class RespUser {

    private String username;
    private String nickname;
    // 充值积分
    private Integer gameCredits;
    // 赠送积分
    private Integer presentExp;
    private String token;

    private Collection<ChoiceTeam> record;


    public RespUser() {
    }

}
