package com.junfly.water.entity.vo;

import com.junfly.water.entity.BaseRestEntity;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/3/3 11:00
 */
public class MenuInVO extends BaseRestEntity{

    private static final long serialVersionUID = 5029513144443044734L;

    /**
     * 父菜单
     */
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
