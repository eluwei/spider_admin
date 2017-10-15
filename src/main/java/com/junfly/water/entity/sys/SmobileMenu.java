package com.junfly.water.entity.sys;

import com.junfly.water.entity.BaseRestEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SmobileMenu extends BaseRestEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String table="s_mobile_menu";

    /**
     * menuCode db_column: menu_code 
     */	
	private String menuCode;
    /**
     * menuName db_column: menu_name 
     */	
	private String menuName;
    /**
     * pmenuCode db_column: pmenu_code 
     */	
	private String pmenuCode;
    /**
     * url db_column: url 
     */	
	private String url;
    /**
     * imgurl db_column: imgurl 
     */	
	private String imgurl;
    /**
     * menuClass db_column: menu_class 
     */	
	private String menuClass;
    /**
     * sn db_column: sn 
     */	
	private Long sn;
    /**
     * isdelete db_column: isdelete 
     */	
	private String isdelete;

	/**
	 * cornerlink db_column:Corner_link
	 */
	private  String cornerlink;

	private String componentName;

	public void setCornerlink(String cornerlink) {
		this.cornerlink = cornerlink;
	}

	public String getCornerlink() {
		return cornerlink;
	}
	//columns END

	public SmobileMenu(){
	}

	public SmobileMenu(
		String menuCode
	)
	{
		this.menuCode = menuCode;
	}
	public SmobileMenu(
		String menuCode,
			String menuName,
			String pmenuCode,
			String url,
			String imgurl,
			String menuClass,
			Long sn,
			String isdelete,
			String cornerlink
	)
	{
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.pmenuCode = pmenuCode;
		this.url = url;
		this.imgurl = imgurl;
		this.menuClass = menuClass;
		this.sn = sn;
		this.isdelete = isdelete;
		this.cornerlink=cornerlink;
	}

	public void setMenuCode(String value) {
		this.menuCode = value;
	}
	public String getMenuCode() {
		return this.menuCode;
	}
	public void setMenuName(String value) {
		this.menuName = value;
	}
	public String getMenuName() {
		return this.menuName;
	}
	public void setPmenuCode(String value) {
		this.pmenuCode = value;
	}
	public String getPmenuCode() {
		return this.pmenuCode;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	public String getUrl() {
		return this.url;
	}
	public void setImgurl(String value) {
		this.imgurl = value;
	}
	public String getImgurl() {
		return this.imgurl;
	}
	public void setMenuClass(String value) {
		this.menuClass = value;
	}
	public String getMenuClass() {
		return this.menuClass;
	}
	public void setSn(Long value) {
		this.sn = value;
	}
	public Long getSn() {
		return this.sn;
	}
	public void setIsdelete(String value) {
		this.isdelete = value;
	}
	public String getIsdelete() {
		return this.isdelete;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("MenuCode",getMenuCode())
			.append("MenuName",getMenuName())
			.append("PmenuCode",getPmenuCode())
			.append("Url",getUrl())
			.append("Imgurl",getImgurl())
			.append("MenuClass",getMenuClass())
			.append("Sn",getSn())
			.append("Isdelete",getIsdelete())
			.append("cornerlink",getCornerlink())
			.append("componentName",getComponentName())
			.toString();
	}
}

