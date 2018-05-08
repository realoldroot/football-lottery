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
}
