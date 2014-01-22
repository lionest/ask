package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class QuestionHis implements Serializable{
	private static final long serialVersionUID = -7123687807059328956L;
	private Integer id;
	private Integer questionId;
	private String subject;
	private String content;
	private Integer domainId;
	private Integer userId;
	private Date createdTime;
	private Integer status;
	
	private User user;
	private Domain domain;
	

	

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




	public String getSubject() {
		return subject;
	}




	public void setSubject(String subject) {
		this.subject = subject;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Integer getDomainId() {
		return domainId;
	}




	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
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




	public Domain getDomain() {
		return domain;
	}




	public void setDomain(Domain domain) {
		this.domain = domain;
	}




	public String toString() {
		return "QUESTION: (id=" + this.getId() + ",questionId="
				+ this.getQuestionId() + ",subject=" + this.getSubject()
				+ ",content=" + this.getContent() +",domainId=" + this.getDomainId() + ",userId="
					+ this.getUserId() + ",createdTime=" + this.getCreatedTime()+",status=" + this.getStatus() + ")";
	}
}
