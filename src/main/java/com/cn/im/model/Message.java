package com.cn.im.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Message
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 22:12
 **/
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 6312554935312300966L;

    private int id;

    private int messageType;

    private int fromUserId;

    private int toUserId;

    private Date sendTime;

    private Date updateTime;

    private String content;

    private long version;

}
