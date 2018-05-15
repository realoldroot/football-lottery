package com.artemis.lottery.config;

import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BuildData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zhengenshen
 * @date 2018-05-09 10:40
 */
@Slf4j
// @Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FootballTeamRepository repository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("ApplicationStartup --------------> ");
        repository.deleteAll();
        repository.saveAll(BuildData.build());
    }
}
