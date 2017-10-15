package com.junfly.water.entity;

import java.io.Serializable;

/**
 * rest调用公共实体
 * @Author: pq
 * @Description:
 * @Date: 2017/3/2 17:30
 */
public class BaseRestEntity implements Serializable{

    private String token;

    private int pageNum;

    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
