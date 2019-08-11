package com.cn.im.chat;

import com.cn.im.ImApplicationTests;
import com.cn.im.chat.config.NettyServerConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName NettyServerConfigTest
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:28
 **/
public class NettyServerConfigTest extends ImApplicationTests {

    @Autowired
    NettyServerConfig nettyServerConfig;


    @Test
    public void test(){
        System.err.println(nettyServerConfig.getHttpPort());
    }
}
