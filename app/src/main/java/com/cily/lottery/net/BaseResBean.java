package com.cily.lottery.net;

import java.io.Serializable;

/**
 * Created by 123 on 2018/4/15.
 */

public class BaseResBean<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
