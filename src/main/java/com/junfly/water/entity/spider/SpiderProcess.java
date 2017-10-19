package com.junfly.water.entity.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-19 21:37:49
 */
@Data
public class SpiderProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//1 未处理 2 处理中 3 已处理
	private String imageProcess;
}
