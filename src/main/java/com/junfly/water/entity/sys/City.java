package com.junfly.water.entity.sys;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class City implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String table="S_CITY";

    /**
     * regionId db_column: REGION_ID 
     */	
	private Long regionId;

    /**
     * parentId db_column: PARENT_ID
     */
	private Long parentId;

    /**
     * regionName db_column: REGION_NAME
     */
	private String regionName;

    /**
     * regionType db_column: REGION_TYPE
     */
	private Integer regionType;

    /**
     * agencyId db_column: AGENCY_ID
     */
	private Integer agencyId;
	private String py;
	private boolean ischecked;

	//columns END

	public City(){
	}

	public City(
		Long regionId
	)
	{
		this.regionId = regionId;
	}
	public City(
		Long regionId,
			Long parentId,
			String regionName,
			Integer regionType,
			Integer agencyId,
			String py,
			boolean ischecked
	)
	{
		this.regionId = regionId;
		this.parentId = parentId;
		this.regionName = regionName;
		this.regionType = regionType;
		this.agencyId = agencyId;
		this.py = py;
		this.ischecked = ischecked;
	}

	public void setRegionId(Long value) {
		this.regionId = value;
	}
	public Long getRegionId() {
		return this.regionId;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}
	public Long getParentId() {
		return this.parentId;
	}
	public void setRegionName(String value) {
		this.regionName = value;
	}
	public String getRegionName() {
		return this.regionName;
	}
	public void setRegionType(Integer value) {
		this.regionType = value;
	}
	public Integer getRegionType() {
		return this.regionType;
	}
	public void setAgencyId(Integer value) {
		this.agencyId = value;
	}
	public Integer getAgencyId() {
		return this.agencyId;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("RegionId",getRegionId())
			.append("ParentId",getParentId())
			.append("RegionName",getRegionName())
			.append("RegionType",getRegionType())
			.append("AgencyId",getAgencyId())
			.append("Py",getPy())
			.append("Ischecked",isIschecked())
			.toString();
	}

}

