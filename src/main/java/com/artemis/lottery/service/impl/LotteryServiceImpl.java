package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ChoiceTeam> query(Long no, String username) {

        //查出开奖内容
        // FootballTeam team = footballTeamRepository.findById(no).orElseThrow(() -> new EntityNotFoundException("错误的期号"));

        // if (no != null && username != null) {
        //     //查询用户下注内容
        //     ChoiceTeam user = choiceTeamRepository.findByUsernameAndNo(username, no).orElseThrow(() -> new EntityNotFoundException("用户本期未下注"));
        //     return Stream.of(user).collect(Collectors.toList());
        // } else {
        //     // return choiceTeamRepository.findByUsername(username);
        //     return null;
        // }
        return null;

        // team.getWinners().retainAll(user.getPlayerNumbers());
        // if (team.getWinners().size() >= 3) {
        //     log.debug("用户中奖了。。{}", user);
        // }
        // return user;

    }

    @Override
    public Page<FootballTeam> query() {
        // return footballTeamRepository.findAll(PageRequest.of(0, 10));
        return null;
    }
}
