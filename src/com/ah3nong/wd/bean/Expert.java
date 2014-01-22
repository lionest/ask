package com.ah3nong.wd.bean;

import java.io.Serializable;

/**
 * 专家
 * 
 */
public class Expert extends User implements Serializable{
	private static final long serialVersionUID = -6049336639053957127L;

	private static final String DEFAULT_PHOTO_URL = "images/70X91.jpg";

	// 简历
	private String resume;
	// 单位
	private String organization;
	// 摘要
	private String summary;
	// 是否推荐
	private int recommended;
	// 头像url
	private String photoUrl;

	public int getRecommended() {
		return recommended;
	}

	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPhotoUrl() {
		return photoUrl == null ? DEFAULT_PHOTO_URL : photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "EXPERT: (userid=" + this.getId() + ",resume=" + this.getResume() + ",organzation="
				+ this.getOrganization();
	}
}
