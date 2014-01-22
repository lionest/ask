package com.ah3nong.wd.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户
 * 
 */
public class User implements UserDetails,Serializable{
	private static final long serialVersionUID = 2358856754860924181L;

	private Integer id;
	private String username;
	private String nickname;
	private String email;
	private String avatar;
	private int expert;
	private String sex;
	private String fullName;
	private String password;
	private int experience;

	private static final boolean enabled = true;
	private static final boolean accountNonExpired = true;
	private static final boolean accountNonLocked = true;
	private static final boolean credentialsNonExpired = true;

	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
	public String getScreenName() {
		return (nickname==null||"".equals(nickname)) ? username : nickname;
	}
	
	public String getExpertName() {
		if(fullName==null||"".equals(fullName)){
			return username;
		}else{
			return fullName ;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getExpert() {
		return expert;
	}

	public void setExpert(int expert) {
		this.expert = expert;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nickname=" + nickname + ", email=" + email + ", avatar=" + avatar + ", expert=" + expert + ", sex=" + sex + ", fullName=" + fullName
				+ ", password=" + password + ", experience=" + experience + ", authorities=" + authorities + "]";
	}

	public void addAuthority(GrantedAuthority authority) {
		authorities.add(authority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
