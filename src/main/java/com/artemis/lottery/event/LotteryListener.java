package com.artemis.lottery.event;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 抽奖事件监听器
 *
 * @author zhengenshen
 * @date 2018-05-08 18:27
 */
@Slf4j
@Component
public class LotteryListener {

    @Autowired
    private ChoiceTeamRepository repository;

    @Async
    @EventListener
    public void lottery(LotteryEvent event) {

        FootballTeam team = event.getFootballTeam();

        ChoiceTeam win = repository.findByNoAndTeamNameAndPlayerNumbers(team.getId(), team.getWinnerTeam(), team.getWinners());

        if (win != null) {
            log.debug("中奖的人有 {}", win);
        } else {
            log.debug("没有人中奖");
        }

        //TODO 广播中奖人是谁。。中多少钱。。
    }
}
