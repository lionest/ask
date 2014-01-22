package com.ah3nong.wd.bean;

import java.io.Serializable;

public class Link implements Serializable{
	private static final long serialVersionUID = -8085835750520067427L;
	private int id;
	private String name;
	private String url;
	private int status;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Link [id=" + id + ", name=" + name + ", url=" + url + ", status=" + status + "]";
	}
}
