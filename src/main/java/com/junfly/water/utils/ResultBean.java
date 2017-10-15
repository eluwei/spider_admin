package com.junfly.water.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回封装
 * @Author: pq
 * @Description:
 * @Date: 2017/8/29 19:34
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 9130069133004268754L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
