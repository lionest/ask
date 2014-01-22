package com.ah3nong.wd.action.expertquestion;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class ExpertAnswerAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -6715545311313152940L;
	private QuestionService questionService;
	private UserService userService;
	
	private int size ; //页面显示信息条数
	private int p; //客户端传来的需要获得的页数
	private String username; //客户端传来的用户名
	private List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>(); //返回给客户端的问题列表
	private int count; //返回给客户端的问题总数
	private boolean usernameExisted = true;
	private boolean pageSizeIsNotNull = true;
	
	public String execute() {
		try {
			//去掉乱码
			String newT = new String(username.getBytes("iso-8859-1"),"gbk");
			username = newT;
			if (username == null || "".equals(username)) {
				usernameExisted = false;
				this.addActionError("用户名为空");
				return ERROR;
			}
			User user = userService.findUserByUsername(username);
			if (user == null) {
				usernameExisted = false;
				this.addActionError("用户名不存在");
				return ERROR;
			}
			if(size == 0){
				pageSizeIsNotNull = false;
				this.addActionError("每页显示新闻条数size为空");
				return ERROR;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("size", size);
			if (p == 0) {
				p = 1;
			}
			params.put("start", (p - 1) * size);
			count = questionService.countReplyQuestionsByUserId(params);
			//获得问题列表
			List<Question> questionList = questionService.findReplyQuestionsByUserId(params);
			for (int i = 0; i < questionList.size(); i++) {
				Question q = questionList.get(i);
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("id", q.getId());
				hm.put("subject", q.getSubject());
				hm.put("status", q.getStatus());
				hm.put("replyNum", q.getReplyNum());
				hm.put("createdTime", q.getCreatedTime().toString());
				if(q.getUserId()!=null){
					hm.put("accepted", 1);
				}else{
					hm.put("accepted", 0);
				}
				questions.add(hm);
			}
			return SUCCESS;
		} catch (ServiceException e) {
			logger.error("execute, service exception", e);
			this.addActionError("ServiceException");
			e.printStackTrace();
			return ERROR;
		} catch (UnsupportedEncodingException e) {
			logger.error("execute, UnsupportedEncodingException ", e);
			this.addActionError("UnsupportedEncodingException");
			e.printStackTrace();
			return ERROR;
		}
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setP(int p) {
		this.p = p;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Map<String, Object>> getQuestions() {
		return questions;
	}

	public int getCount() {
		return count;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isUsernameExisted() {
		return usernameExisted;
	}

	public boolean isPageSizeIsNotNull() {
		return pageSizeIsNotNull;
	}	
	
}
