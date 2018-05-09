package com.artemis.lottery.config;

import com.artemis.lottery.repository.FootballTeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhengenshen
 * @date 2018-05-09 10:40
 */
@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FootballTeamRepository repository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("ApplicationStartup --------------> ");
        // repository.deleteAll();
        // repository.saveAll(BuildData.build());
        // log.debug("初始化执行。。。");
    }
}
