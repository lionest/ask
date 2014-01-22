package com.ah3nong.wd.bean;

import java.io.Serializable;

public class Article implements Serializable{
	private static final long serialVersionUID = 2051367025499096121L;
	private int id;
	private String title;
	private String content;
	private String keyword;
	private int domainId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", keyword=" + keyword + ", domainId=" + domainId + "]";
	}
}
