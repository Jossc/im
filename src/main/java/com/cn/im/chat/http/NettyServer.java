package com.cn.im.chat.http;

import com.cn.im.chat.config.NettyServerConfig;
import com.cn.im.common.base.BeanProvider;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * netty 服务器
 *
 * @ClassName NettyServer
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:06
 **/
@Slf4j
public class NettyServer implements ChatServerNode {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private transient NettyServerConfig nettyServerConfig = BeanProvider.getBean(NettyServerConfig.class);

    private int port;

    @Override
    public void init() {
        this.port = nettyServerConfig.getHttpPort();
    }

    @Override
    public void start() throws Exception {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(1);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.TRACE)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("http-decorder", new HttpRequestDecoder());
                ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(512 * 1024));
                ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                ch.pipeline().addLast("serve-handler", new HttpServerHandler());
            }
        });

        log.info("http server start at [{}]", port);
        b.bind(new InetSocketAddress(port)).sync();
    }

    @Override
    public void shutDown() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}
