package com.ah3nong.wd.bean;

import java.io.Serializable;

public class QuestionImg implements Serializable{
	private static final long serialVersionUID = 4193915556574668987L;
	private Integer id;
	private Integer questionId;
	private Integer imgOrder;
	private String url;
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

	public Integer getImgOrder() {
		return imgOrder;
	}

	public void setImgOrder(Integer imgOrder) {
		this.imgOrder = imgOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QUESTIONIMG: (QUESTIONID=" + this.getQuestionId() + ",imgOrder="+this.getImgOrder()+",url=" + this.getUrl()+",status="+this.getStatus();
	}
	
}
