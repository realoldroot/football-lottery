package com.artemis.lottery.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengenshen
 * @date 2018-05-09 17:00
 */

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final ChannelGroup group;

    ServerInitializer(ChannelGroup channelGroup) {
        this.group = channelGroup;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //处理日志
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        //处理心跳
        pipeline.addLast(new IdleStateHandler(0, 0, 1800, TimeUnit.SECONDS));
        pipeline.addLast(new HeartbeatHandler());

        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

        pipeline.addLast(new JsonHandler());

        pipeline.addLast(new MessageHandler(group));


        // pipeline.addLast(new HttpServerCodec());
        // pipeline.addLast(new ChunkedWriteHandler());
        // pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        // pipeline.addLast(new HttpRequestHandler("/ws"));
        // pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
