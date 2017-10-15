package com.junfly.water.entity.sys;

import org.apache.http.cookie.SM;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:15:08
 */
public class SMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String menucode;
	//
	private String menuname;
	//
	private String pmenucode;
	//
	private String url;
	//
	private String imgurl;
	//
	private String orderindex;
	//
	private String isdelete;
	//父节点字符串，','分隔
	private String pMenuCodes;
	//新后端URL
	private String urlApi;
	//父菜单名称
	private String pmenuname;

	private List<SMenu> childList;
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
	/**
	 * 设置：
	 */
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	/**
	 * 获取：
	 */
	public String getMenuname() {
		return menuname;
	}
	/**
	 * 设置：
	 */
	public void setPmenucode(String pmenucode) {
		this.pmenucode = pmenucode;
	}
	/**
	 * 获取：
	 */
	public String getPmenucode() {
		return pmenucode;
	}
	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	/**
	 * 获取：
	 */
	public String getImgurl() {
		return imgurl;
	}
	/**
	 * 设置：
	 */
	public void setOrderindex(String orderindex) {
		this.orderindex = orderindex;
	}
	/**
	 * 获取：
	 */
	public String getOrderindex() {
		return orderindex;
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
	/**
	 * 设置：父节点字符串，','分隔
	 */
	public void setPMenuCodes(String pMenuCodes) {
		this.pMenuCodes = pMenuCodes;
	}
	/**
	 * 获取：父节点字符串，','分隔
	 */
	public String getPMenuCodes() {
		return pMenuCodes;
	}
	/**
	 * 设置：新后端URL
	 */
	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}
	/**
	 * 获取：新后端URL
	 */
	public String getUrlApi() {
		return urlApi;
	}

	public String getPmenuname() {
		return pmenuname;
	}

	public void setPmenuname(String pmenuname) {
		this.pmenuname = pmenuname;
	}

	public String getpMenuCodes() {
		return pMenuCodes;
	}

	public void setpMenuCodes(String pMenuCodes) {
		this.pMenuCodes = pMenuCodes;
	}

	public List<SMenu> getChildList() {
		return childList;
	}

	public void setChildList(List<SMenu> childList) {
		this.childList = childList;
	}
}
