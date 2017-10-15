package com.junfly.water.entity.sys;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:24:17
 */
public class SRolemenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String rolecode;
	//
	private String menucode;

	/**
	 * 设置：
	 */
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	/**
	 * 获取：
	 */
	public String getRolecode() {
		return rolecode;
	}
	/**
	 * 设置：
	 */
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	/**
	 * 获取：
	 */
	public String getMenucode() {
		return menucode;
	}
}
