package com.junfly.water.entity.sys;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String table="S_USER";
	public static final String USER_TYPE_WATERWORK = "1";
	public static final String USER_TYPE_SYSTEM = "0";
	public static final String USER_TYPE_WATERMANAGE = "2";

    /**
     * userid db_column: USERID 
     */	
	private String userid;

    /**
     * 登录帐号 db_column: USERCODE
     */
	private String usercode;

    /**
     * 用户名称 db_column: USERNAME
     */
	private String username;

    /**
     * 登录密码 db_column: PASSWORD
     */
	private String password;

    /**
     * 手机号码 db_column: PHONENUM
     */
	private String phonenum;

    /**
     * 最后一次登录时间 db_column: LASTLOGINTIME
     */
	private java.util.Date lastlogintime;

    /**
     * 是否启用 db_column: ISACTIVE
     */
	private String isactive;

    /**
     * 是否删除 db_column: ISDELETE
     */
	private String isdelete;

	/**
     * 最后一次登录IP db_column: LOGINIP
     */
	private String lastloginip;

	private String userroles;

	private String usertype;
	private String pwd;
	private String orgcode;
	private String orgname;

	private String orgcodes;

	private String skin;

	private String wwid;     // 登录用户对应水厂id

	//columns END

	/**
	 * 角色ID列表
	 */
	private List<String> roleCodes;


	public User(){
	}

	public User(
		String userid
	)
	{
		this.userid = userid;
	}
	public User(
		String userid,
			String usercode,
			String username,
			String password,
			String phonenum,
			java.util.Date lastlogintime,
			String isactive,
			String isdelete,
			String lastloginip,
			String userroles,
			String usertype,
			String pwd,
			String orgcode
	)
	{
		this.userid = userid;
		this.usercode = usercode;
		this.username = username;
		this.password = password;
		this.phonenum = phonenum;
		this.lastlogintime = lastlogintime;
		this.isactive = isactive;
		this.isdelete = isdelete;
		this.lastloginip = lastloginip;
		this.userroles = userroles;
		this.usertype = usertype;
		this.pwd = pwd;
		this.orgcode = orgcode;
	}

	public void setUserid(String value) {
		this.userid = value;
	}
	public String getUserid() {
		return this.userid;
	}
	public void setUsercode(String value) {
		this.usercode = value;
	}
	public String getUsercode() {
		return this.usercode;
	}
	public void setUsername(String value) {
		this.username = value;
	}
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPhonenum(String value) {
		this.phonenum = value;
	}
	public String getPhonenum() {
		return this.phonenum;
	}
	public void setLastlogintime(java.util.Date value) {
		this.lastlogintime = value;
	}
	public java.util.Date getLastlogintime() {
		return this.lastlogintime;
	}
	public void setIsactive(String value) {
		this.isactive = value;
	}
	public String getIsactive() {
		return this.isactive;
	}
	public void setIsdelete(String value) {
		this.isdelete = value;
	}
	public String getIsdelete() {
		return this.isdelete;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getUserroles() {
		return userroles;
	}

	public void setUserroles(String userroles) {
		this.userroles = userroles;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	public String getWwid() {
		return wwid;
	}

	public void setWwid(String wwid) {
		this.wwid = wwid;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Userid",getUserid())
			.append("Usercode",getUsercode())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("Phonenum",getPhonenum())
			.append("Lastlogintime",getLastlogintime())
			.append("Isactive",getIsactive())
			.append("Isdelete",getIsdelete())
			.append("Lastloginip",getLastloginip())
			.append("Userroles",getUserroles())
			.append("Usertype",getUsertype())
			.append("Pwd",getPwd())
			.append("Orgcode",getOrgcode())
			.toString();
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getOrgcodes() {
		return orgcodes;
	}

	public void setOrgcodes(String orgcodes) {
		this.orgcodes = orgcodes;
	}
}

