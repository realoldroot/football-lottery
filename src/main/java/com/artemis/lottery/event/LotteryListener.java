package com.artemis.lottery.event;

import com.artemis.lottery.common.RandomTools;
import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.Response;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BonusPool;
import com.artemis.lottery.socket.OnlineManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    private ChoiceTeamRepository choiceTeamRepository;

    @Autowired
    private FootballTeamRepository footballTeamRepository;

    @Autowired
    private BonusPool bonusPool;

    @Async
    @EventListener
    public void lottery(LotteryEvent event) {

        FootballTeam team = event.getFootballTeam();

        List<ChoiceTeam> win = choiceTeamRepository.findByNoAndTeamNameAndPlayerNumbers(team.getId(), team.getWinnerTeam(), team.getWinners());

        if (win == null || win.size() == 0) {
            log.debug("没有人中奖，随机中奖人");
            List<String> random = RandomTools.phoneNumber();

            team.setUsers(random);
            team.setBet(RandomTools.number(10));
        } else {
            log.debug("中奖的人有 {}", win);
            List<String> users = win.stream().map(ChoiceTeam::getUsername).collect(Collectors.toList());
            int bet = win.stream().map(ChoiceTeam::getPet).reduce((a, b) -> a + b).orElse(0);

            team.setUsers(users);
            team.setBet(bet);
        }

        //拿到总金额
        int totalAmount = bonusPool.get();
        team.setCurrentAmount(totalAmount);

        OnlineManage.broadcast(new Response(team));

        footballTeamRepository.save(team);
    }
}
