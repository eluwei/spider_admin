package com.junfly.water.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/8/30 11:02
 */
@Data
public class PageResultBean<T> implements Serializable {

    private static final long serialVersionUID = -5177186316247521261L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<T> data;

    public PageResultBean() {
        super();
    }

    public PageResultBean(List<T> data, int totalCount, int pageSize, int currPage) {
        super();
        this.data = data;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double)totalCount/pageSize);
    }

    public PageResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
