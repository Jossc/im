package com.cn.im.message.enums;

import com.cn.im.message.AbstractPacket;
import com.cn.im.session.IoSession;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @ClassName PacketManager
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 17:14
 **/
@Slf4j
public enum PacketManager {
    INSTANCE;

    public void execPacket(IoSession session, AbstractPacket pact) {
        if (pact == null) return;
        try {
            Method m = pact.getClass().getMethod("execPacket", IoSession.class);
            m.invoke(pact, session);
        } catch (Exception e) {
            log.error("exception {}", e);
        }
    }

}
