package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class ReplyComment implements Serializable{
	private static final long serialVersionUID = 2950755974582520864L;
	private Integer id;
	private Integer replyId;
	private Integer userId;
	private String content;
	private Date createdTime;
	private Integer status;
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
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
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "ReplyComment [id=" + id + ", replyId=" + replyId + ", userId=" + userId + ", content=" + content + ", createdTime=" + createdTime + ", status=" + status + ", user=" + user + "]";
	}
}
