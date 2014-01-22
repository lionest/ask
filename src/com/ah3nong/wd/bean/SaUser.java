package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class SaUser implements Serializable{
	private static final long serialVersionUID = 8861543889411131977L;
	private int isExpert;
	private String userid;
	private String pwd;
	private Date lastLoginDate;
	private int logins;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	public int getLogins() {
		return logins;
	}

	public void setLogins(int logins) {
		this.logins = logins;
	}

	public int getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(int isExpert) {
		this.isExpert = isExpert;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
