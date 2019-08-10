package com.cn.im.dao;

import com.cn.im.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {

    /**
     * 保存消息
     *
     * @param message
     * @return
     */
    int saveMessage(@Param("message") Message message);

    /**
     * 根据userId查询消息
     *
     * @param userId
     * @return
     */
    List<Message> findByUserId(@Param("userId") int userId);
}
