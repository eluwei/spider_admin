package com.junfly.water.entity;

import java.io.Serializable;

/**
 * Created by bj on 2016/12/7.
 */
public class CallResult implements Serializable{

    private static final long serialVersionUID = -5766956554690720874L;

    /**
     * 1 调用成功 -1 调用失败
     */
    private int code;

    /**
     * 调用返回说明
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
