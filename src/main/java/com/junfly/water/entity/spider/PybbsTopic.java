package com.junfly.water.entity.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-14 21:24:07
 */
@Data
public class PybbsTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String content;
	//
	private int good;
	//
	private Date inTime;
	//
	private String labelId;
	//
	private Date lastReplyTime;
	//
	private int topicLock;
	//
	private Date modifyTime;
	//
	private Integer replyCount;
	//
	private String tab;
	//
	private String title;
	//
	private int top;
	//
	private String upIds;
	//
	private Integer view;
	//
	private Integer userId;

	private String coverImage;

	private int pass;

	private String channel;

	private String subTitle;
}
