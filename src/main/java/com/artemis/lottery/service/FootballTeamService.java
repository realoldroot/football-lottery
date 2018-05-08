package com.artemis.lottery.service;

import com.artemis.lottery.domain.FootballTeam;

/**
 * 球队
 *
 * @author zhengenshen
 * @date 2018-05-08 11:07
 */

public interface FootballTeamService extends BaseService<FootballTeam> {

    /**
     * 初始化球队
     *
     * @return 球队信息
     */
    FootballTeam initFootballTeam();

    FootballTeam findFootballTeam();
}
