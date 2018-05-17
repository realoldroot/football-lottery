package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-15 16:05
 */

@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private ChoiceTeamRepository choiceTeamRepository;

    @Autowired
    private FootballTeamRepository footballTeamRepository;

    @Override
    public ChoiceTeam query(Long no, String username) {

        //查出开奖内容
        FootballTeam team = footballTeamRepository.findById(no).orElseThrow(() -> new EntityNotFoundException("错误的期号"));

        //查询用户下注内容
        ChoiceTeam user = choiceTeamRepository.findByUsernameAndNo(username, no).orElseThrow(() -> new EntityNotFoundException("用户本期未下注"));

        team.getWinners().retainAll(user.getPlayerNumbers());
        if (team.getWinners().size() >= 3) {
            log.debug("用户中奖了。。{}", user);
        }
        return user;

    }
}
