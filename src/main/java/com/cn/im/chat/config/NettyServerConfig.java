package com.cn.im.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName NettyServerConfig
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:18
 **/
@Component
@PropertySource(value = "classpath:serverConfig.properties")
public class NettyServerConfig {

    /**
     * 服务器ip
     */
    @Value("${socket.serverIp}")
    private   String socketIp;
    /**
     * 服务器端口
     */
    @Value("${socket.port}")
    private int socketPort;
    /**
     * 服务端口
     */
    @Value("${http.port}")
    private int httpPort;

    public String getSocketIp() {
        return socketIp;
    }

    public int getSocketPort() {
        return socketPort;
    }

    public int getHttpPort() {
        return httpPort;
    }
}
