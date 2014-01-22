package com.ah3nong.wd.bean;

import java.io.Serializable;

public class UserDomain implements Serializable{
	private static final long serialVersionUID = -4791755067224610378L;
	private int userId;
	private int domainId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	@Override
	public String toString() {
		return "USER DOMAIN: (userid=" + this.getUserId() + ",domianid="
				+ this.getDomainId();
	}

}
