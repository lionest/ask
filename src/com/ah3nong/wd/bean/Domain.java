package com.ah3nong.wd.bean;

import java.io.Serializable;

/**
 * 领域
 * 
 */
public class Domain implements Serializable{
	private static final long serialVersionUID = -1513138379113628201L;
	private Integer id;
	private String name;
	private Integer parentId;
	private String nodePath;
	private boolean recommended;
	private int hasChild;
	private int expertDomain;

	public boolean hasChild() {
		return hasChild == 1;
	}

	/**
	 * 获取结点的层级。
	 * @return
	 */
	public int getLayer() {
		return nodePath.length() / 2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	public int getHasChild() {
		return hasChild;
	}

	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}

	public int getExpertDomain() {
		return expertDomain;
	}

	public void setExpertDomain(int expertDomain) {
		this.expertDomain = expertDomain;
	}

	@Override
	public String toString() {
		return "Domain [id=" + id + ", name=" + name + ", parentId=" + parentId + ", nodePath=" + nodePath + ", recommended=" + recommended + ", hasChild=" + hasChild + ", expertDomain="
				+ expertDomain + "]";
	}

}
