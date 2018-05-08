package com.artemis.lottery.event;

import com.artemis.lottery.domain.FootballTeam;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 开奖事件
 *
 * @author zhengenshen
 * @date 2018-05-08 18:26
 */

public class LotteryEvent extends ApplicationEvent {

    @Getter
    private FootballTeam footballTeam;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LotteryEvent(Object source) {
        super(source);
    }

    public LotteryEvent(Object source, FootballTeam footballTeam) {
        super(source);
        this.footballTeam = footballTeam;
    }


}
