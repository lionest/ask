package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable{
	private static final long serialVersionUID = -8702679299569478608L;
	private int id;
	private String title;
	private String imgUrl;
	private String questionIds;
	private Date createdTime;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
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
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + ", imgUrl=" + imgUrl + ", questionIds=" + questionIds + ", createdTime=" + createdTime + ", status=" + status + "]";
	}
	
}
