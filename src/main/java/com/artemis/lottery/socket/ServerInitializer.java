package com.artemis.lottery.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author zhengenshen
 * @date 2018-05-09 17:00
 */
@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {


    @Autowired
    private JsonInboundHandler jsonInboundHandler;

    @Autowired
    private AuthenticationHandler authenticationHandler;

    @Autowired
    private MessageHandler messageHandler;


    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //处理日志
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        //处理心跳
        // pipeline.addLast(new IdleStateHandler(400, 400, 400, TimeUnit.SECONDS));
        // pipeline.addLast(new HeartbeatHandler());
        // ch.pipeline().addLast(new ReadTimeoutHandler(120));

        pipeline.addLast(new StringDecoder(Charset.forName("UTF-8")));
        pipeline.addLast(new StringEncoder(Charset.forName("UTF-8")));

        pipeline.addLast(jsonInboundHandler);
        pipeline.addLast(authenticationHandler);
        pipeline.addLast(messageHandler);

        pipeline.addLast(new JsonOutboundHandler());


    }
}
