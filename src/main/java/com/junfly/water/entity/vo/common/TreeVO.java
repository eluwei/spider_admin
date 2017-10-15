package com.junfly.water.entity.vo.common;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/31 14:17
 */
public class TreeVO implements Serializable {
    private static final long serialVersionUID = 2189346757626260002L;

    private String id;

    private String label;

    private List<TreeVO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVO> children) {
        this.children = children;
    }
}
