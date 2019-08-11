package com.cn.im.chat.http;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求
 * @ClassName HttpResult
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:05
 **/
public class HttpResult {
    private byte code;

    private Map<String, String> params = new HashMap<>();

    public static HttpResult valueOf(byte code) {
        HttpResult result = new HttpResult();
        result.code = code;
        return result;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}
