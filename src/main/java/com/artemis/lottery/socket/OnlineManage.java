package com.artemis.lottery.socket;

import com.artemis.lottery.domain.Response;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author zhengenshen
 * @date 2018-05-16 11:01
 */
@Slf4j
public class OnlineManage {

    private static ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private static Set<String> checked = new ConcurrentSkipListSet<>();


    public static void add(Channel channel) {
        group.add(channel);
        checked.add(channel.id().asLongText());
    }

    public static boolean contains(Channel channel) {
        String id = channel.id().asLongText();
        return checked.contains(id);
    }

    public static boolean notContains(Channel channel) {
        return !contains(channel);
    }

    public static void close() {
        checked.clear();
        group.close();
    }

    /**
     * 广播
     *
     * @param o
     */
    public static void broadcast(Object o) {
        group.writeAndFlush(o);
    }

    public static void remove(Channel channel) {
        group.remove(channel);
        checked.remove(channel.id().asLongText());
    }

    public static void loginSuccessReturn() {

    }

    /**
     * 鉴权成功上线
     */
    public static void online(Channel channel) {
        add(channel);
        Response r = new Response();
        r.setCode(0);
        r.setMessage("登陆成功");
        channel.writeAndFlush(r);
    }

    public static void offline(Channel channel) {
        remove(channel);
    }
}
