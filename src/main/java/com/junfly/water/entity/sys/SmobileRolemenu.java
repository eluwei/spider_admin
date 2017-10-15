package com.junfly.water.entity.sys;

import com.junfly.water.entity.BaseRestEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class SmobileRolemenu extends BaseRestEntity{
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String table="s_mobile_rolemenu";

	/**
	 * roleCode db_column: ROLE_CODE
	 */
	private String roleCode;
	/**
	 * menuCode db_column: MENU_CODE
	 */
	private String menuCode;
	//columns END

	private String pmenuCode;

	private  SmobileMenu smobileMenu;

	private List<SmobileRolemenu> childrolemenus;

	public SmobileRolemenu(){
	}

	public SmobileRolemenu(
			String roleCode
	)
	{
		this.roleCode = roleCode;

	}

	public SmobileRolemenu(
			String roleCode,
			String menuCode
	)
	{
		this.roleCode = roleCode;
		this.menuCode = menuCode;
	}
	public SmobileRolemenu(
			String roleCode,
			String menuCode,
			String pmenuCode,
			SmobileMenu smobileMenu,
			List<SmobileRolemenu> childrolemenus

	)
	{
		this.roleCode = roleCode;
		this.menuCode = menuCode;
		this.pmenuCode= pmenuCode;
		this.smobileMenu=smobileMenu;
		this.childrolemenus=childrolemenus;

	}

	public void setPmenuCode(String pmenuCode) {
		this.pmenuCode = pmenuCode;
	}

	public void setSmobileMenu(SmobileMenu smobileMenu) {
		this.smobileMenu = smobileMenu;
	}

	public void setChildrolemenus(List<SmobileRolemenu> childrolemenus) {
		this.childrolemenus = childrolemenus;
	}

	public String getPmenuCode() {
		return pmenuCode;
	}

	public SmobileMenu getSmobileMenu() {
		return smobileMenu;
	}

	public List<SmobileRolemenu> getChildrolemenus() {
		return childrolemenus;
	}

	public void setRoleCode(String value) {
		this.roleCode = value;
	}
	public String getRoleCode() {
		return this.roleCode;
	}
	public void setMenuCode(String value) {
		this.menuCode = value;
	}
	public String getMenuCode() {
		return this.menuCode;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RoleCode",getRoleCode())
				.append("MenuCode",getMenuCode())
				.append("pmenuCode",getPmenuCode())
				.append("smobileMenu",getSmobileMenu())
				.append("childrolemenus",getChildrolemenus())
				.toString();
	}

}

