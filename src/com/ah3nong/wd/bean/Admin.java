package com.ah3nong.wd.bean;

import java.io.Serializable;

public class Admin implements Serializable {
	private static final long serialVersionUID = 6335904058851978871L;
	private int id;
	private String name;
	private String password;
	private String salt;
	private String role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", salt=" + salt + ", role=" + role + "]";
	}
	
}
