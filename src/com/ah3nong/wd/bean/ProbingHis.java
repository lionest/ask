package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class ProbingHis implements Serializable{
	private static final long serialVersionUID = -8869958151356721301L;
	private Integer id;
	private Integer probingId;
	private Integer questionId;
	private Integer replyId;
	private String content;
	private Date creatTime;
	private Integer status;

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getProbingId() {
		return probingId;
	}



	public void setProbingId(Integer probingId) {
		this.probingId = probingId;
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



	public Date getCreatTime() {
		return creatTime;
	}



	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}


//	@Override
//	public String toString() {
//		return "PROBINGHIS: (id=" + this.getId() + ",questionId="
//				+ this.getQuestionId() + ",REPLYID=" + this.getReplyId()
//				+ ",content=" + this.getContent().substring(0, 50) + ")";
//	}

}
