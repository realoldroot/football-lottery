package com.artemis.lottery.service;

import com.artemis.lottery.domain.ChoiceTeam;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:12
 */

public interface ChoiceTeamService extends BaseService<ChoiceTeam> {

    @Override
    void save(ChoiceTeam choiceTeam);
}
