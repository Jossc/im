package com.cn.im.dao;

import com.cn.im.ImApplicationTests;
import com.cn.im.model.Message;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName MessageDaoTest
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 23:31
 **/
public class MessageDaoTest extends ImApplicationTests {

    @Autowired
    MessageDao dao;

    @Test
    public void testSave() {
        Message message = new Message();
        message.setContent("哈哈");
        message.setMessageType(1);
        message.setFromUserId(1);
        message.setToUserId(1);
        message.setSendTime(new Date());
        message.setUpdateTime(new Date());
        message.setVersion(1);
        dao.saveMessage(message);
    }
}
