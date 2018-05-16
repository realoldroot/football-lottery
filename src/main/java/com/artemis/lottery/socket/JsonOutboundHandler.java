package com.artemis.lottery.socket;

import com.artemis.lottery.common.JsonTools;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengenshen
 * @date 2018-05-10 11:05
 */
@Slf4j
@ChannelHandler.Sharable
public class JsonOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String json = JsonTools.toJson(msg);
        super.write(ctx, json, promise);
    }
}
