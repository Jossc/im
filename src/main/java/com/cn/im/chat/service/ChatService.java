package com.cn.im.chat.service;

import com.cn.im.common.base.SessionManager;
import com.cn.im.message.req.MessageToUser;
import com.cn.im.session.IoSession;
import lombok.Setter;

import static com.cn.im.message.enums.PacketType.ResChatToUser;

/**
 * @ClassName ChatService
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 17:24
 **/
@Setter
public class ChatService {

    public void chat(IoSession fromUser, long toUserId, String content) {
        IoSession toUser = SessionManager.INSTANCE.getSessionBy(toUserId);
        if (fromUser == null || toUser == null) {
            return;
        }
        if (!checkDirtyWords(content)) {
            return;
        }

        //双方都推送消息
        MessageToUser response = new MessageToUser();
        response.setContent(content);
        response.setToUserId(fromUser.getUser().getUserId());
        toUser.sendPacket(response);

        fromUser.sendPacket(response);
    }

    private boolean checkDirtyWords(String content) {
        return true;
    }

}
