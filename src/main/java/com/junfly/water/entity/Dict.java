package com.junfly.water.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by bj on 2016/11/24.
 */
@Alias(value="s_dict")
public class Dict implements Serializable{
    private static final long serialVersionUID = 5751609801751940154L;
    private String dictcode;
    private String dictname;
    private String pdictcode;
    private int orderindex;
    private String isdelete;
    private String dictSql;

    public String getDictcode() {
        return dictcode;
    }

    public void setDictcode(String dictcode) {
        this.dictcode = dictcode;
    }

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname;
    }

    public String getPdictcode() {
        return pdictcode;
    }

    public void setPdictcode(String pdictcode) {
        this.pdictcode = pdictcode;
    }

    public int getOrderindex() {
        return orderindex;
    }

    public void setOrderindex(int orderindex) {
        this.orderindex = orderindex;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public String getDictSql() {
        return dictSql;
    }

    public void setDictSql(String dictSql) {
        this.dictSql = dictSql;
    }
}
