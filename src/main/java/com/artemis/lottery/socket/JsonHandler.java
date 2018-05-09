package com.artemis.lottery.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * json 转换器
 *
 * @author zhengenshen
 * @date 2018-05-09 18:16
 */

public class JsonHandler extends ChannelInboundHandlerAdapter {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
