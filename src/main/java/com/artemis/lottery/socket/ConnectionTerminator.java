package com.artemis.lottery.socket;

import io.netty.channel.ChannelHandlerContext;

/**
 * 延迟5秒之后如果没有收到鉴权消息则关闭这个链接
 *
 * @author zhengenshen
 * @date 2018-05-16 15:15
 */

public class ConnectionTerminator implements Runnable {

    private ChannelHandlerContext ctx;

    ConnectionTerminator(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        if (OnlineManage.notContains(ctx.channel())) {
            ctx.close();
        }
    }
}