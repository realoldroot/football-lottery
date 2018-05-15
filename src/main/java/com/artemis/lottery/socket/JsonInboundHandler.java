package com.artemis.lottery.socket;

import com.artemis.lottery.common.JsonTools;
import com.artemis.lottery.config.TokenTools;
import com.artemis.lottery.domain.Protocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * json 转换器
 *
 * @author zhengenshen
 * @date 2018-05-09 18:16
 */

@Slf4j
@Component
@ChannelHandler.Sharable
public class JsonInboundHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private TokenTools tokenTools;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.debug("JsonHandler -> {}", msg);
        Channel channel = ctx.channel();
        Protocol c;
        try {
            c = JsonTools.toBean(msg.toString(), Protocol.class);
            if (c.getType() == 1) {
                if (tokenTools.has(c.getToken())) {
                    //校验成功 保存连接
                    Server.group.add(channel);
                } else {
                    log.error("token不存在 {}", c);
                    channel.close();
                }
            } else {
                ctx.fireChannelRead(c);
            }

        } catch (IOException e) {
            log.error("json解析错误 {}", msg);
            channel.close();
        }
    }
}
