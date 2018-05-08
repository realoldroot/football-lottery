package com.artemis.lottery.schedule;

import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.TeamEnum;
import com.artemis.lottery.event.LotteryEvent;
import com.artemis.lottery.service.BuildData;
import com.artemis.lottery.service.FootballTeamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 调度任务
 *
 * @author zhengenshen
 * @date 2018-05-08 15:34
 */
@Component
@Slf4j
public class ScheduleTask {

    @Autowired
    private FootballTeamService footballTeamService;

    @Autowired
    private ApplicationContext context;

    /**
     * 初始化奖励
     */
    public void initTeam() {
        FootballTeam footballTeam = BuildData.buildTeam();
        footballTeamService.save(footballTeam);
    }

    /**
     * 开奖
     * 查询status = 0 的数据 （未开奖的数据）
     * 取出来 选手的号码。
     * 随机取出来3个号码 和1个获胜队伍，更新数据
     */
    public void lottery() {

        FootballTeam footballTeam = footballTeamService.findFootballTeam();
        if (footballTeam == null) {
            log.debug("没有待开奖信息");
            return;
        }

        List<String> list = footballTeam.getPlayers();
        Set<String> set = new HashSet<>();

        while (set.size() < 3) {
            int i = RandomUtils.nextInt(0, list.size());
            set.add(list.get(i));
        }

        //随机获胜球队
        int i = RandomUtils.nextInt(0, 2);

        footballTeam.setWinners(new ArrayList<>(set));
        footballTeam.setWinnerTeam(TeamEnum.getName(i));
        footballTeam.setStatus(1);

        log.debug("本期开奖结果是 {}" + footballTeam);

        footballTeamService.save(footballTeam);

        //TODO 广播开奖数据 查找谁开奖了
        context.publishEvent(new LotteryEvent(this, footballTeam));
    }
}
