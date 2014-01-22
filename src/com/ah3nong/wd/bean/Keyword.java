package com.ah3nong.wd.bean;

import java.io.Serializable;

public class Keyword implements Serializable{
	private static final long serialVersionUID = 7087670897126253953L;
	private Integer id;
	private String content;
	private int status;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "KEYWORD: (id=" + this.getId() + ",keyword="+this.getContent()+",status=" + this.getStatus();
	}
	
	
}
