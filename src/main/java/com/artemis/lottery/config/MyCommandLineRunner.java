package com.artemis.lottery.config;

import com.artemis.lottery.socket.Server;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author zhengenshen
 * @date 2018-05-09 16:53
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private Server server;

    @Override
    public void run(String... args) {

        log.debug("启动时加载----------------------->");
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        ChannelFuture future = server.start(address);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.destroy()));

        future.channel().closeFuture().syncUninterruptibly();

    }
}
