package com.artemis.lottery.service;

import com.artemis.lottery.domain.ChoiceTeam;

/**
 * @author zhengenshen
 * @date 2018-05-15 16:04
 */

public interface LotteryService {

    ChoiceTeam query(Long no, String username);
}
