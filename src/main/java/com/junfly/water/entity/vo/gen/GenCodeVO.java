package com.junfly.water.entity.vo.gen;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/9/8 10:18
 */
@Data
public class GenCodeVO implements Serializable {

    private static final long serialVersionUID = 7950857242472027572L;
    /**
     * 自定义包名
     */
    private String packageName;
}
