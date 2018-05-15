package com.artemis.lottery.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengenshen
 * @date 2018-05-09 17:02
 */
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HB", CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            if (((IdleStateEvent) evt).state() == IdleState.READER_IDLE) {
                log.debug("没有收到客户端的心跳，关闭连接");
                ctx.channel().close();
            }
            // log.info("====>Heartbeat: greater than {}", 180);
            // ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
