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


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol c) {
        log.debug("MessageHandler -> {}", c);


        Server.group.add(ctx.channel());
        Response r = new Response();
        r.setCode(0);
        r.setMessage("登陆成功");
        Server.group.writeAndFlush(r);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
