package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题
 * 
 */
public class Question implements Serializable{
	private static final long serialVersionUID = -6844342615623027706L;
	private Integer id;
	// 主题
	private String subject;
	// 内容
	private String content;
	// 提问者的用户id
	private Integer userId;
	// 提问时间
	private Date createdTime;
	// 领域id
	private Integer domainId;
	// 解决时间
	private Date solvedTime;
	// 补充内容
	private String extraContent;
	// 回答的数量
	private Integer replyNum;
	// 状态
	private Integer status;
	// 是否推荐
	private Integer recommended;
	// 浏览次数
	private Integer viewCount;
	//是否为头条
	private Integer  head;
	//关键字
	private String keywords;
	//关键字描述
	private String summary;
	//是否匿名提问
	private int anonymous;
	//问题悬赏分
	private int experience;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getHead() {
		return head;
	}

	public void setHead(Integer head) {
		this.head = head;
	}

	private User user;

	private Domain domain;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public Date getSolvedTime() {
		return solvedTime;
	}

	public void setSolvedTime(Date solvedTime) {
		this.solvedTime = solvedTime;
	}

	public String getExtraContent() {
		return extraContent;
	}

	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	public Integer getRecommended() {
		return recommended;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
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
	

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", subject=" + subject + ", content=" + content + ", userId=" + userId + ", createdTime=" + createdTime + ", domainId=" + domainId + ", solvedTime=" + solvedTime
				+ ", extraContent=" + extraContent + ", replyNum=" + replyNum + ", status=" + status + ", recommended=" + recommended + ", viewCount=" + viewCount + ", head=" + head + ", keywords="
				+ keywords + ", summary=" + summary + ", anonymous=" + anonymous + ", experience=" + experience + ", user=" + user + ", domain=" + domain + "]";
	}

}
