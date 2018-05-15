package com.artemis.lottery.service;

import com.artemis.lottery.domain.FootballTeam;
import com.sun.istack.internal.Nullable;

/**
 * 球队
 *
 * @author zhengenshen
 * @date 2018-05-08 11:07
 */

public interface FootballTeamService extends BaseService<FootballTeam> {


    @Nullable
    FootballTeam findFootballTeam();

    FootballTeam findById(Long id);

    /**
     * 当前时间的一期
     */
    FootballTeam findCurrentTeam();

    /**
     * 当前时间的 下一期
     */
    FootballTeam findNextTeam();

}
