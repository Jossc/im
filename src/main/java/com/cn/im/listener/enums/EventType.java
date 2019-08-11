package com.cn.im.listener.enums;

import lombok.Getter;

/**
 * @ClassName EventType
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 19:38
 **/
@Getter
public enum EventType {

    /**
     * 登录事件
     */
    LOGIN,

    /**
     * 登出事件
     */
    LOGOUT,

    /**
     * 升级事件
     */
    LEVEL_UP;
}
