package com.cn.im.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程组
 *
 * @ClassName IMThreadFactory
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 21:33
 **/
public class IMThreadFactory implements ThreadFactory {
    private ThreadGroup threadGroup;

    private String groupName;

    private final boolean daemon;

    private AtomicInteger idGenerator = new AtomicInteger(1);

    public IMThreadFactory(String group) {
        this(group, false);
    }

    public IMThreadFactory(String group, boolean daemon) {
        this.groupName = group;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = getNextThreadName();
        Thread ret = new Thread(threadGroup, r, name, 0);
        ret.setDaemon(daemon);
        return ret;
    }

    private String getNextThreadName() {
        return this.groupName + "-thread-" + this.idGenerator.getAndIncrement();
    }
}
