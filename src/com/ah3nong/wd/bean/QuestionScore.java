package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

public class QuestionScore implements Serializable{
	private static final long serialVersionUID = -8285881082733267576L;
	private int id;
	private int questionId;
	private int replyUserId;
	private int score;
	private Date createdTime;
	private int experience;
	
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
	
	public int getReplyUserId() {
		return replyUserId;
	}
	
	public void setReplyUserId(int replyUserId) {
		this.replyUserId = replyUserId;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "QuestionScore [id=" + id + ", questionId=" + questionId + ", replyUserId=" + replyUserId + ", score=" + score + ", createdTime=" + createdTime + ", experience=" + experience + "]";
	}
	
}
