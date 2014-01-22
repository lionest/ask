package com.ah3nong.wd.bean;

import java.io.Serializable;

public class QuestionExpert implements Serializable{
	private static final long serialVersionUID = 5046835465274876112L;
	private int questionId;
	private int expertId;
	private int status;
	
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getExpertId() {
		return expertId;
	}
	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "QUESTIONEXPERT: (questionId=" + this.getQuestionId() + ",expertid="+this.getExpertId()+",status=" + this.getStatus();
	}
	
	
}
