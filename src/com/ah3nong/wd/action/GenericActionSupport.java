package com.ah3nong.wd.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.common.Pager;
import com.opensymphony.xwork2.ActionSupport;

public abstract class GenericActionSupport<T> extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, SessionAware {

	/**
	 * Logger object.
	 */
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected Map<String, Object> session;

	protected ServletContext servletContext = ServletActionContext
			.getServletContext();

	protected int numPerPage; // 每页显示多少条 可以不用Set

	public int pageNumShown = 5;// 页标数字多少个 可以不用Set

	protected int pageNum=1; // 当前页码 上一页加1
	
	protected String message; // 生成Set Get 略

	protected Pager<T> pager;

	// 要删除的对象的ID,以逗号分割
	protected String entityIds;

	public String getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(String entityIds) {
		this.entityIds = entityIds;
	}

	public int getPageNum() {

		return pageNum;

	}

	public void setPageNum(int pageNum) {

		this.pageNum = pageNum;

	}

	public int pageStart() {

		if (getPageNum() <= 0) {
			pageNum = 1;
		}

		return (getPageNum() - 1) *numPerPage;

	}

	public Pager<T> getPager() {
		return pager;
	}

	public void setPager(Pager<T> pager) {
		this.pager = pager;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 *      (javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * debug log
	 */
	protected void debug(String method, String message) {
		logger.debug(method, message);
	}

	/**
	 * warn log
	 */
	protected void warn(String method, String message) {
		logger.warn(method, message);
	}

	/**
	 * fatal log
	 */
	protected void error(String method, String message) {
		logger.error(method, message);
	}

	/**
	 * fatal log
	 */
	protected void error(String method, String message, Throwable t) {
		logger.error(method, message, t);
	}

	/**
	 * info log
	 */
	protected void info(String method, String message) {
		logger.info(method, message);
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
