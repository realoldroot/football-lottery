package com.artemis.lottery.service;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhengenshen
 * @date 2018-05-15 16:04
 */

public interface LotteryService {

    List<ChoiceTeam> query(Long no, String username);

    Page<FootballTeam> query();

    Page<ChoiceTeam> query(String username, Integer page, Integer pageSize);

    Page<FootballTeam> queryHistory(Integer page, Integer pageSize);
}
