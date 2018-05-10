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
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 调度任务
 *
 * @author zhengenshen
 * @date 2018-05-08 15:34
 */
@Slf4j
@Component
@Lazy(value = false)
public class ScheduleTask {

    @Autowired
    private FootballTeamService footballTeamService;

    @Autowired
    private ApplicationContext context;

    /**
     * 初始化奖励
     * <p>
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
     * 注意： 30 * * * * * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行
     */
    // @Scheduled(cron = "0 0 3 * * ?")
    public void initTeam() {
        footballTeamService.saveAll(BuildData.build());
    }

    /**
     * 开奖
     * 查询status = 0 的数据 （未开奖的数据）
     * 取出来 选手的号码。
     * 随机取出来3个号码 和1个获胜队伍，更新数据
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void lottery() {

        String yyyyMMddHHmm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        long id = Long.parseLong(yyyyMMddHHmm) / 10 * 10;
        log.debug(id + "");
        FootballTeam footballTeam = footballTeamService.findById(id);
        if (footballTeam == null) {
            log.debug("没有待开奖信息");
            return;
        }
        if (footballTeam.getStatus() == 1) {
            log.debug("第{}期已经开奖", id);
            return;
        }
        log.debug("开奖，第{}期", id);

        List<String> list = footballTeam.getPlayers();
        Set<String> set = new HashSet<>();

        while (set.size() < 3) {
            int i = RandomUtils.nextInt(0, list.size());
            set.add(list.get(i));
        }

        //随机获胜球队
        int i = RandomUtils.nextInt(0, 2);

        footballTeam.setWinners(set);
        footballTeam.setWinnerTeam(TeamEnum.getName(i));
        footballTeam.setStatus(1);

        log.debug("本期开奖结果是 {}", footballTeam);

        footballTeamService.save(footballTeam);

        // 广播开奖数据 查找谁开奖了
        context.publishEvent(new LotteryEvent(this, footballTeam));
    }
}
