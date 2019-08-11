package com.cn.im.chat.http;

/**
 * 服务节点
 */
public interface ChatServerNode {
    /**
     * 服务初始化
     */
    void init();

    /**
     * 服务启动
     *
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * 服务关闭
     *
     * @throws Exception
     */
    void shutDown() throws Exception;

}
