package com.junfly.water.entity.spider;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-11-15 22:34:54
 */
@Data
public class PybbsUser implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer attempts;
	//
	private Date attemptsTime;
	//
	private String avatar;
	//
	private int block;
	//
	private String email;
	//
	private Date inTime;
	//
	private String password;
	//
	private Integer score;
	//
	private String signature;
	//
	private Long spaceSize;
	//
	private String token;
	//
	private String url;
	//
	private String username;
	//
	private String nickname;
}
