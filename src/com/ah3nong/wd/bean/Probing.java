package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class Probing implements Serializable{
	private static final long serialVersionUID = -1990416016906743635L;
	private Integer id;
	private Integer questionId;
	private Integer replyId;
	private String content;
	private Date createdTime;
	private Date updatedTime;
	private Integer status;
	private Reply reply;
	
	
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


	public Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Reply getReply() {
		return reply;
	}


	public void setReply(Reply reply) {
		this.reply = reply;
	}
	

//	@Override
//	public String toString() {
//		return "PROBING: (id=" + this.getId() + ",questionid="+this.getQuestionId()+",REPLYID=" + this.getReplyId()
//				+ ",content=" + this.getContent().substring(0,50) + ")";
//	}
}
