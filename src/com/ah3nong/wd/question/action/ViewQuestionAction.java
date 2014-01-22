package com.ah3nong.wd.question.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class ViewQuestionAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -3729646257086353903L;
	
	private QuestionService questionService;
	private UserService userService;
	private int id;
	
	//查询条件
	private int expertId;
	private int status;
	private String content;
	
	//状态MAP
	private Map<String,Integer> statusMap;
	private List<User> expertList;
	public String execute() {
		try {
			statusMap = new HashMap<String,Integer>();
			statusMap.put("未解决", Config.getInt("UNRESOLVED"));
			statusMap.put("已解决", Config.getInt("RESOLVED"));
			statusMap.put("审核中", Config.getInt("AUDIT"));
			
			//从User表中查找专家状态为1的用户列表
			expertList=userService.findExperts(1);

			
			Map<String, Object> params = new HashMap<String, Object>();
			if(status!=0){
				params.put("status", status);
			}
			if(content!=null&&!content.equals("")){
				params.put("content", content);
			}
			if(expertId!=0){
				params.put("expertId", expertId);
			}
			pager = questionService.getQuestionPagerByParam(params, pageNum, Config.getInt("PAGESIZE"));
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getExpertId() {
		return expertId;
	}

	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}

	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, Integer> statusMap) {
		this.statusMap = statusMap;
	}

	public List<User> getExpertList() {
		return expertList;
	}

	public void setExpertList(List<User> expertList) {
		this.expertList = expertList;
	}
	
	
}
