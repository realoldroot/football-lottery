package com.artemis.lottery.socket;

import com.artemis.lottery.common.JsonTools;
import com.artemis.lottery.domain.Connection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * json 转换器
 *
 * @author zhengenshen
 * @date 2018-05-09 18:16
 */

@Slf4j
public class JsonInboundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.debug("JsonHandler -> {}", msg);
        Connection c;
        try {
            c = JsonTools.toBean(msg.toString(), Connection.class);
            ctx.fireChannelRead(c);
        } catch (IOException e) {
            log.error("json解析错误 {}", msg);
            ctx.close();
        }
    }
}
