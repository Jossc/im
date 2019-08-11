package com.cn.im.dispatch;

/**
 * 任务分发
 *
 * @ClassName DispatchTask
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 16:01
 **/
public abstract class DispatchTask implements Runnable {

    /**
     * 分发任务的key
     */
    public int dispatchKey;

    public int getDispatchKey() {
        return dispatchKey;
    }

}
