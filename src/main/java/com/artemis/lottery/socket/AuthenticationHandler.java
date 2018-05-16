package com.artemis.lottery.socket;

import com.artemis.lottery.config.TokenTools;
import com.artemis.lottery.domain.Protocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 鉴权
 *
 * @author zhengenshen
 * @date 2018-05-16 15:16
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class AuthenticationHandler extends SimpleChannelInboundHandler<Protocol> {

    @Autowired
    private TokenTools tokenTools;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol c) {

        Channel ch = ctx.channel();
        if (OnlineManage.notContains(ch)) {
            ctx.executor().schedule(new ConnectionTerminator(ctx), 5, TimeUnit.SECONDS);
            if (tokenTools.verifyToken(c.getUsername(), c.getToken())) {
                //校验成功 保存连接
                OnlineManage.online(ch);
            }
        } else {
            ctx.fireChannelRead(c);
        }
    }
}
