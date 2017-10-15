package com.junfly.water.entity.vo.common;

import java.io.Serializable;
import java.util.List;

/**
 * 级联下拉框VO
 * @Author: pq
 * @Description:
 * @Date: 2017/5/27 15:56
 */
public class CascaderSelectVO implements Serializable {
    /**
     * 值
     */
    private String value;
    /**
     * 显示值
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 子节点
     */
    private List<CascaderSelectVO> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CascaderSelectVO> getChildren() {
        return children;
    }

    public void setChildren(List<CascaderSelectVO> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
