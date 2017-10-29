package com.junfly.water.entity.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-15 22:13:30
 */
@Data
public class SpiderHis implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String hisTitle;

	private String imageProcess;
}
