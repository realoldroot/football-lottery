package com.artemis.lottery.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 消息处理器
 *
 * @author zhengenshen
 * @date 2018-05-09 17:29
 */

public class MessageHandler extends SimpleChannelInboundHandler<String> {

    private final ChannelGroup group;

    MessageHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {

    }
}
