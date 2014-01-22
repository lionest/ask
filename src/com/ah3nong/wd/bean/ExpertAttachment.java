package com.ah3nong.wd.bean;

import java.io.Serializable;

public class ExpertAttachment implements Serializable{
	private static final long serialVersionUID = -6685081521523129163L;
	
	private int id;
	private int userId;
	private String name;
	private String path;
	private int type;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ExpertAttachment [id=" + id + ", userId=" + userId + ", name=" + name + ", path=" + path + ", type=" + type + "]";
	}
	
}
