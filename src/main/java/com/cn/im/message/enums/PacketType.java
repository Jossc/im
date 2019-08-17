package com.cn.im.message.enums;

import com.cn.im.message.AbstractPacket;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PacketType
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 17:16
 **/
@Getter
public enum PacketType {
    ;

   /* *//**
     * 请求--链接心跳包
     *//*
    ReqHeartBeat(1_000, ),

    *//**
     * 请求--单聊
     *//*
    ReqChatToUser(4_000, ),
    *//**
     * 请求--群聊
     *//*
    ReqChatToGroup(4_001 ),

    *//**
     * 推送--单聊
     *//*
    ResChatToUser(4_200, ),
    *//**
     * 推送--群聊
     *//*
    ResChatToGroup(4_201, );
*/
    private int type;
    private Class<? extends AbstractPacket> packetClass;
    private static Map<Integer, Class<? extends AbstractPacket>> PACKET_CLASS_MAP =
            new HashMap<>();

    PacketType(int type, Class<? extends AbstractPacket> packetClass) {
        this.type = type;
        this.packetClass = packetClass;
    }
}
