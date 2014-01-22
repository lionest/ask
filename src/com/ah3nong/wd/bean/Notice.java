package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{
	private static final long serialVersionUID = 4003116609354334806L;
	private int id;
	private int questionId;
	private int userId;
	private int type;
	private int anchor;
	private Date createdTime;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getAnchor() {
		return anchor;
	}
	public void setAnchor(int anchor) {
		this.anchor = anchor;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", questionId=" + questionId + ", userId=" + userId + ", type=" + type + ", anchor=" + anchor + ", createdTime=" + createdTime + ", status=" + status + "]";
	}
	
}
