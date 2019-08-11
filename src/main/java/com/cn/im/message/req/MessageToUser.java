package com.cn.im.message.req;

import com.cn.im.message.AbstractPacket;
import com.cn.im.message.enums.PacketType;
import com.cn.im.session.IoSession;
import lombok.Data;

/**
 * 发送消息的
 *
 * @ClassName MessageToUser
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 17:19
 **/
@Data
public class MessageToUser extends AbstractPacket {

    private int toUserId;

    private String content;

    @Override
    public PacketType getPacketType() {
        return null;
    }

    @Override
    public void execPacket(IoSession session) {

    }
}
