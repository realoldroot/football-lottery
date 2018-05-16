package com.artemis.lottery.socket;

import com.artemis.lottery.config.TokenTools;
import com.artemis.lottery.domain.Protocol;
import com.artemis.lottery.domain.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息处理器
 *
 * @author zhengenshen
 * @date 2018-05-09 17:29
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<Protocol> {


    @Autowired
    private TokenTools tokenTools;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol c) {

        log.debug("MessageHandler --------------->");
        ctx.channel().writeAndFlush(new Response("12313"));
        // Channel ch = ctx.channel();
        // if (OnlineManage.notContains(ch)) {
        //     ctx.executor().schedule(new ConnectionTerminator(ctx), 5, TimeUnit.SECONDS);
        //     if (tokenTools.verifyToken(c.getUsername(), c.getToken())) {
        //         //校验成功 保存连接
        //         OnlineManage.online(ch);
        //     }
        // } else {
        //     log.debug("123213");
        //     ctx.channel().writeAndFlush(new Response("12313"));
        // }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
