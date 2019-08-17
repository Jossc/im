package com.cn.im.listener;

import com.cn.im.listener.enums.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ListenerManager
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 19:39
 **/
@Component
@Slf4j
public class ListenerManager {
    /**
     * concurrentHashMap 存放监听
     */
    private Map<String, Method> concurrentHashMap = new ConcurrentHashMap<>();


    /**
     * 注册监听时间
     *
     * @param listener
     * @param eventType
     * @param method
     */
    public void registerEventListener(Object listener, EventType eventType, Method method) {
        concurrentHashMap.put(getKey(listener, eventType), method);
    }

    /**
     * 获取ke
     *
     * @param handler
     * @param eventType
     * @return
     */
    private String getKey(Object handler, EventType eventType) {
        return handler.getClass().getName() + "-" + eventType.toString();
    }


    /**
     * 分发事件
     *
     * @param handler object
     * @param event   事件
     */
    public void dispatcherEvent(Object handler, BaseEvent event) {
        log.info("handler {},even t{}", handler, event);
        try {
            Method method = concurrentHashMap.get(getKey(handler, event.getEventType()));
            method.invoke(handler, event);
        } catch (Exception e) {
            log.error("exception", e);
        }
    }


}
