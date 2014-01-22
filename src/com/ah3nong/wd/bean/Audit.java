package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class Audit implements Serializable{
	private static final long serialVersionUID = -3029159004400389550L;
	private Integer id;
	private Integer type;
	private Integer recordId;
	private Integer userId;
	private Date createdTime;
	private String reason;
	private Integer status;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getRecordId() {
		return recordId;
	}



	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Date getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "REPLY: (id=" + this.getId() + ",type="+this.getType()+",recordId=" + this.getRecordId()
				+ ",userId=" + this.getUserId()+ ",createdTime="+this.getCreatedTime()+")";
	}
	
}
