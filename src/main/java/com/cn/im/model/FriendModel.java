package com.cn.im.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FriendModel
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 22:04
 **/
@Data
public class FriendModel implements Serializable {

    private static final long serialVersionUID = -7080895538410747078L;

    private int id;

    private int userId;

    private int friendId;

    private Date createTime;

    private Date updateTime;


}
