package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable{
	private static final long serialVersionUID = -4728480130820602678L;
	private Integer id;
	private Integer questionId;
	private Integer probingId;
	private Integer userId;
	private String content;
	private Integer accepted;
	private Integer status;
	private Date createdTime;
	private Date updatedTime;
	private User user;
	private int commentNum;
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	public Integer getProbingId() {
		return probingId;
	}


	public void setProbingId(Integer probingId) {
		this.probingId = probingId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	


	public Integer getAccepted() {
		return accepted;
	}


	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}


	@Override
	public String toString() {
		return "REPLY: (id=" + this.getId() + ",userid=" + this.getUserId()
				+ ",questionId=" + this.getQuestionId() + ")";
	}
}
