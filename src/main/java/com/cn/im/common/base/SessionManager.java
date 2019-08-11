package com.cn.im.common.base;

import com.cn.im.common.utils.ChannelUtils;
import com.cn.im.message.AbstractPacket;
import com.cn.im.model.FriendModel;
import com.cn.im.session.IoSession;
import com.cn.im.session.enums.SessionCloseReason;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName SessionManager
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 17:26
 **/
@Slf4j
public enum SessionManager {

    INSTANCE;


    /**
     * 缓存通信上下文环境对应的登录用户（主要用于服务）
     */
    private Map<IoSession, Integer> session2UserIds = new ConcurrentHashMap<>();

    /**
     * 缓存用户id与对应的会话
     */
    private ConcurrentMap<Integer, IoSession> userId2Sessions = new ConcurrentHashMap<>();


    /**
     * 向单一在线用户发送数据包
     */
    public void sendPacketTo(IoSession session, AbstractPacket pact) {
        if (pact == null || session == null) return;
        session.sendPacket(pact);
    }

    public void sendPacketTo(FriendModel user, AbstractPacket pact) {
        IoSession session = userId2Sessions.get(user.getUserId());
        if (session != null) {
            session.sendPacket(pact);
        }
    }

    public void sendPacketTo(Long userId, AbstractPacket pact) {
        if (pact == null || userId <= 0) return;

        IoSession session = userId2Sessions.get(userId);
        if (session != null) {
            session.sendPacket(pact);
        }
    }

    public void sendPacketTo(Channel channel, AbstractPacket pact) {
        if (pact == null || channel == null) return;
        channel.writeAndFlush(pact);
    }

    /**
     * 向所有在线用户发送数据包
     */
    public void notifyToAllOnlineUsers(AbstractPacket pact) {
        if (pact == null) return;

        userId2Sessions.values().forEach(session -> session.sendPacket(pact));
    }

    public IoSession getSessionBy(long userId) {
        return this.userId2Sessions.get(userId);
    }

    public boolean registerSession(FriendModel user, IoSession session) {
        session.setUser(user);
        userId2Sessions.put(user.getUserId(), session);
        session2UserIds.put(session, user.getUserId());

        log.info("[{}] registered...", user.getUserId());
        return true;
    }

    /**
     * 注销用户通信渠道
     */
    public void ungisterUserContext(Channel context, SessionCloseReason reason) {
        if (context == null) {
            return;
        }
        IoSession session = ChannelUtils.getSessionBy(context);
        Integer userId = session2UserIds.remove(session);
        userId2Sessions.remove(userId);
        if (session != null) {
            session.close(reason);
        }
    }
}
