package com.ah3nong.wd.bean;

import java.io.Serializable;

public class QuestionKeyword implements Serializable{
	private static final long serialVersionUID = 8630309318625748565L;
	private int id;
	private int questionId;
	private String keyword;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "QuestionKeyword [id=" + id + ", questionId=" + questionId + ", keyword=" + keyword + "]";
	}
	
}
