package com.junfly.water.entity.sys;

import com.junfly.water.entity.BaseRestEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Userrole extends BaseRestEntity {

	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String table="S_USERROLE";

    /**
     * 用户编码 db_column: USERID 
     */	
	private String userid;

    /**
     * 角色编码 db_column: ROLECODE
     */
	private String rolecode;

	//columns END

	public Userrole(){
	}
	public Userrole(
		String userid
	)
	{
		this.userid = userid;
	}

	public Userrole(
		String userid,
			String rolecode
	)
	{
		this.userid = userid;
		this.rolecode = rolecode;
	}

	public void setUserid(String value) {
		this.userid = value;
	}
	public String getUserid() {
		return this.userid;
	}
	public void setRolecode(String value) {
		this.rolecode = value;
	}
	public String getRolecode() {
		return this.rolecode;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Userid",getUserid())
			.append("Rolecode",getRolecode())
			.toString();
	}
	
}

