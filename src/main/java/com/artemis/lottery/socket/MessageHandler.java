package com.artemis.lottery.socket;

import com.artemis.lottery.domain.Connection;
import com.artemis.lottery.domain.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息处理器
 *
 * @author zhengenshen
 * @date 2018-05-09 17:29
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<Connection> {

    private final ChannelGroup group;

    MessageHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Connection c) {
        log.debug("MessageHandler -> {}", c);
        //TODO 校验数据

        group.add(ctx.channel());
        Response r = new Response();
        r.setCode(0);
        r.setMessage("登陆成功");
        group.writeAndFlush(r);
    }
}
