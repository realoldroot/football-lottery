package com.artemis.lottery.event;

import lombok.extern.slf4j.Slf4j;
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


    @Async
    @EventListener
    public void lottery(LotteryEvent event) {

        //TODO 这里是中奖发布消息的地方
        log.debug("中奖发布消息了。。。。");
    }
}
