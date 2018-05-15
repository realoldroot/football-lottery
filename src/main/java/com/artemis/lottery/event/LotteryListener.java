package com.artemis.lottery.event;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.Response;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.socket.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<ChoiceTeam> win = repository.findByNoAndTeamNameAndPlayerNumbers(team.getId(), team.getWinnerTeam(), team.getWinners());

        if (win == null || win.size() == 0) {
            log.debug("没有人中奖");
            List<String> sss = Stream.of("183****0399", "183****0391").collect(Collectors.toList());

            Response r = new Response();
            r.setCode(1);
            r.setData(team);
            Server.getChannelGroup().write(r);

            Response r2 = new Response();
            r2.setCode(2);
            r2.setData(sss);
            Server.getChannelGroup().writeAndFlush(r2);

            return;
        }
        log.debug("中奖的人有 {}", win);

        List<String> users = win.stream().map(ChoiceTeam::getUsername).collect(Collectors.toList());
        Response r = new Response();
        r.setCode(1);
        r.setData(team);
        Server.getChannelGroup().write(r);

        Response r2 = new Response();
        r.setCode(2);
        r.setData(users);
        Server.getChannelGroup().writeAndFlush(r2);
    }

    private void notice(List<String> users) {


        Server.getChannelGroup().writeAndFlush(users);
    }
}
