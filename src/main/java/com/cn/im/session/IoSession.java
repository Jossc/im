package com.cn.im.session;

import com.cn.im.common.utils.ChannelUtils;
import com.cn.im.message.AbstractPacket;
import com.cn.im.model.FriendModel;
import com.cn.im.session.enums.SessionCloseReason;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName IoSession
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:38
 **/
@Slf4j
public class IoSession {

    /**
     * distributeKey auto generator
     */
    private AtomicInteger dispatchKeyGenerator = new AtomicInteger();

    /**
     * 网络连接channel
     */
    private Channel channel;

    private FriendModel friendModel;

    /**
     * ip地址
     */
    private String ipAddr;

    private boolean reconnected;

    /**
     * 业务分发索引
     */
    private int dispatchKey;

    /**
     * 拓展用，保存一些个人数据
     */
    private Map<String, Object> attrs = new HashMap<>();


    public IoSession() {

    }

    public IoSession(Channel channel) {
        this.channel = channel;
        this.ipAddr = ChannelUtils.getIp(channel);
        this.dispatchKey = dispatchKeyGenerator.getAndIncrement();
    }

    public void setUser(FriendModel friendModel) {
        this.friendModel = friendModel;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getDispatchKey() {
        return dispatchKey;
    }

    /**
     * 向客户端发送消息
     *
     * @param packet
     */
    public void sendPacket(AbstractPacket packet) {
        if (packet == null) {
            return;
        }
        if (channel != null) {
            channel.writeAndFlush(packet);
        }
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public boolean isReconnected() {
        return reconnected;
    }

    public void setReconnected(boolean reconnected) {
        this.reconnected = reconnected;
    }

    public FriendModel getUser() {
        return friendModel;
    }

    public boolean isClose() {
        if (channel == null) {
            return true;
        }
        return !channel.isActive() ||
                !channel.isOpen();
    }

    /**
     * 关闭session
     *
     * @param reason {@link SessionCloseReason}
     */
    public void close(SessionCloseReason reason) {
        try {
            if (this.channel == null) {
                return;
            }
            if (channel.isOpen()) {
                channel.close();
                log.info("close session[{}], reason is {}", getUser().getUserId(), reason);
            } else {
                log.info("session[{}] already close, reason is {}", getUser().getUserId(), reason);
            }
        } catch (Exception e) {
        }
    }

}
