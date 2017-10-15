package com.junfly.water.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 10:50:24
 */
public class SRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String rolecode;
	//
	private String rolename;
	//
	private String describe;
	//
	private String isactive;
	//
	private String isdelete;

	private List<String> menuCodeList;

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
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	/**
	 * 获取：
	 */
	public String getRolename() {
		return rolename;
	}
	/**
	 * 设置：
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：
	 */
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	/**
	 * 获取：
	 */
	public String getIsactive() {
		return isactive;
	}
	/**
	 * 设置：
	 */
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	/**
	 * 获取：
	 */
	public String getIsdelete() {
		return isdelete;
	}

	public List<String> getMenuCodeList() {
		return menuCodeList;
	}

	public void setMenuCodeList(List<String> menuCodeList) {
		this.menuCodeList = menuCodeList;
	}
}
