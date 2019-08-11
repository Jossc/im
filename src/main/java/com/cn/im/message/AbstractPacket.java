package com.cn.im.message;

import com.cn.im.message.enums.PacketType;
import com.cn.im.session.IoSession;

/**
 * @ClassName AbstractPacket
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:54
 **/
public abstract class AbstractPacket {

    /**
     * 获取数据包
     *
     * @return
     */
    abstract public PacketType getPacketType();

    /**
     * 业务处理
     */
    abstract public void execPacket(IoSession session);

    /**
     * 是否开启gzip压缩(默认关闭)
     * 消息体数据大的时候才开启，非常小的包压缩后体积反而变大，而且耗时
     */
    public boolean isUseCompression() {
        return false;
    }
}
