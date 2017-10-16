package com.junfly.water.entity.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-16 21:03:58
 */
@Data
public class SpiderSource implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//名称
	private String name;
	//编码
	private String code;
	//渠道 1 搜狗微信
	private String channel;
}
