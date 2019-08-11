package com.cn.im.listener;

import com.cn.im.listener.enums.EventType;

/**
 * 监听器抽象事件
 *
 * @ClassName BaseEvent
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 19:17
 **/
public abstract class BaseEvent {
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 事件类型
     */
    private final EventType eventType;

    public BaseEvent(EventType evtType) {
        this.createTime = System.currentTimeMillis();
        this.eventType = evtType;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    /**
     * 是否在消息主线程同步执行
     *
     * @return
     */
    public boolean isSynchronized() {
        return true;
    }

}
