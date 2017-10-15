package com.junfly.water.entity.sys;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-26 16:42:35
 */
public class SCity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private BigDecimal regionId;
	//
	private BigDecimal parentId;
	//
	private String regionName;
	//
	private BigDecimal regionType;
	//
	private BigDecimal agencyId;
	//
	private String py;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SCity> childList;

	/**
	 * 设置：
	 */
	public void setRegionId(BigDecimal regionId) {
		this.regionId = regionId;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getRegionId() {
		return regionId;
	}

	/**
	 * 设置：
	 */
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getParentId() {
		return parentId;
	}

	/**
	 * 设置：
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 获取：
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * 设置：
	 */
	public void setRegionType(BigDecimal regionType) {
		this.regionType = regionType;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getRegionType() {
		return regionType;
	}

	/**
	 * 设置：
	 */
	public void setAgencyId(BigDecimal agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getAgencyId() {
		return agencyId;
	}

	/**
	 * 设置：
	 */
	public void setPy(String py) {
		this.py = py;
	}

	/**
	 * 获取：
	 */
	public String getPy() {
		return py;
	}

	public List<SCity> getChildList() {
		return childList;
	}

	public void setChildList(List<SCity> childList) {
		this.childList = childList;
	}

}