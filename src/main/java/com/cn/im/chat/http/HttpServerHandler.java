package com.cn.im.chat.http;

import com.cn.im.common.utils.ChannelUtils;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpServerHandler
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:34
 **/
@Slf4j
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String ipAddr = ChannelUtils.getIp(channel);
        log.info("channelActive ipAddress {}", ipAddr);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("channel read msg {}", msg);
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        Channel channel = ctx.channel();
        String ip = ChannelUtils.getIp(channel);
        if (httpRequest.getMethod().equals(HttpMethod.GET)) {
            QueryStringDecoder queryDecoder = new QueryStringDecoder(httpRequest.getUri());
            Map<String, List<String>> params = queryDecoder.parameters();
            log.info("收到Ip[{}]的http请求,参数为[{}]", ip, params);
        }
        ctx.writeAndFlush(createResponse(HttpResult.valueOf(HttpResultCode.SUCCESS)));
    }


    /**
     * 创建响应
     *
     * @param result
     * @return
     */
    private FullHttpResponse createResponse(HttpResult result) {
        String jsonData = new Gson().toJson(result);
        ByteBuf buf = Unpooled.copiedBuffer(jsonData, CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
                buf.readableBytes());

        return response;
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
