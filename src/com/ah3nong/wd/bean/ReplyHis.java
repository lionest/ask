package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class ReplyHis implements Serializable{
	private static final long serialVersionUID = -1812974196052758687L;
	private Integer id;
	private Integer questionId;
	private Integer replyId;
	private Integer probingId;
	private Integer userId;
	private String content;
	private Integer status;
	private Date createdTime;
	
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




	public Integer getReplyId() {
		return replyId;
	}




	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
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

	@Override
	public String toString() {
		return "REPLY: (id=" + this.getId() + ",content="+this.getContent().substring(0,50)+",userid=" + this.getUserId()
				+ ",questionId=" + this.getQuestionId() + ")";
	}
}
