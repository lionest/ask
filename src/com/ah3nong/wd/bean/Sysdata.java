package com.ah3nong.wd.bean;

import java.io.Serializable;

public class Sysdata implements Serializable{
	private static final long serialVersionUID = -6133936849950600802L;
	private int id;
	private String name;
	private String cname;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "System [id=" + id + ", name=" + name + ", cname=" + cname + ", content=" + content + "]";
	}
	
}
