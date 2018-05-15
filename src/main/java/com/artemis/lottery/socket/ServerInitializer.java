package com.artemis.lottery.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengenshen
 * @date 2018-05-09 17:00
 */
@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private JsonInboundHandler jsonInboundHandler;

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //处理日志
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        //处理心跳
        pipeline.addLast(new IdleStateHandler(400, 400, 400, TimeUnit.SECONDS));
        pipeline.addLast(new HeartbeatHandler());

        pipeline.addLast(new StringDecoder(Charset.forName("UTF-8")));
        pipeline.addLast(new StringEncoder(Charset.forName("UTF-8")));

        pipeline.addLast(jsonInboundHandler);

        pipeline.addLast(messageHandler);

        pipeline.addLast(new JsonOutboundHandler());


        // pipeline.addLast(new HttpServerCodec());
        // pipeline.addLast(new ChunkedWriteHandler());
        // pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        // pipeline.addLast(new HttpRequestHandler("/ws"));
        // pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
