package com.cn.im.common.utils;

import com.cn.im.session.IoSession;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * channel 工具类
 *
 * @ClassName ChannelUtils
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:36
 **/
public final class ChannelUtils {
    public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

    /**
     * 添加新的会话
     *
     * @param channel
     * @param session
     * @return
     */
    public static boolean addChannelSession(Channel channel, IoSession session) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.compareAndSet(null, session);
    }

    /**
     * 获取session
     *
     * @param channel
     * @return
     */
    public static IoSession getSessionBy(Channel channel) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.get();
    }

    /**
     * 获取IP
     *
     * @param channel
     * @return
     */
    public static String getIp(Channel channel) {
        return ((InetSocketAddress) channel.remoteAddress()).getAddress().toString().substring(1);
    }
}
