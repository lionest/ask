package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class QuestionExtraContentHis implements Serializable{
	private static final long serialVersionUID = 6328437925579093979L;
	private Integer id;
	private Integer questionId;
	private String extraContent;
	private Date createdTime;
	private Integer status;
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
	public String getExtraContent() {
		return extraContent;
	}
	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
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

	
	


//	@Override
//	public String toString() {
//		return "QUESTIONEXPERT: (questionId=" + this.getQuestionId()
//				+ ",extraContent=" + this.getExtraContent() + ",status="
//				+ this.getStatus();
//	}

}
