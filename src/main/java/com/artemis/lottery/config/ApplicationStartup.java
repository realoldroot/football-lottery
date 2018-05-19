package com.artemis.lottery.config;

import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.socket.Server;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author zhengenshen
 * @date 2018-05-09 10:40
 */
@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FootballTeamRepository repository;

    @Autowired
    private Server server;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("ApplicationStartup --------------> ");
        // repository.deleteAll();
        // repository.saveAll(BuildData.build());

        taskScheduler.execute(() -> {
            InetSocketAddress address = new InetSocketAddress("192.168.0.120", 9999);
            ChannelFuture future = server.start(address);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> server.destroy()));
            future.channel().closeFuture().syncUninterruptibly();
        });
    }

}
