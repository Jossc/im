package com.cn.im.dao;

import com.cn.im.ImApplicationTests;
import com.cn.im.model.FriendModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FriendDaoTest
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 22:29
 **/
public class FriendDaoTest extends ImApplicationTests {

    @Autowired
    FriendDao friendDao;

    @Test
    public void testSave() {
        FriendModel friendModel = new FriendModel();
        friendModel.setCreateTime(new Date());
        friendModel.setFriendId(1);
        friendModel.setUserId(1);
        friendModel.setUpdateTime(new Date());

        friendDao.saveFriend(friendModel);
        System.err.println(friendModel.getId());
    }

    @Test
    public void findByUserId(){
       List<FriendModel> friendModelList =  friendDao.findByUserId(1);
       System.err.println(friendModelList);
    }
}
