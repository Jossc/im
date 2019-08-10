package com.cn.im.dao;

import com.cn.im.model.FriendModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao {

    /**
     * 保存用户
     *
     * @param friendModel 用户model
     * @return
     */
    int saveFriend(@Param("friend") FriendModel friendModel);


    /**
     * 根据userId查询朋友
     *
     * @param userId
     * @return
     */
    List<FriendModel> findByUserId(@Param("userId") int userId);
}
