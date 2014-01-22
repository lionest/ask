package com.ah3nong.wd.action.expertquestion;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.MethodFailedException;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class RecommendToExpertQuestionAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 5608917400472185781L;
	private QuestionService questionService;
	private UserService userService;
	private ExpertService expertService;
	private DomainService domainService;
	private int size ; //页面显示信息条数
	private int p; //客户端传来的需要获得的页数
	private String username; //客户端传来的用户名
	private List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>(); //返回给客户端的问题列表
	private int count; //返回给客户端的问题总数
	private boolean usernameExisted = true; //用户名是否存在
	private boolean pageSizeIsNotNull = true; //size是否为空

	public String execute() {
		try {
			//处理乱码
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
			//如果p没传值则为第一页
			if (p == 0) {
				p = 1;
			}
			List<Map<String,Object>> domains = new ArrayList<Map<String,Object>>() ;
			
			//获得用户的擅长领域
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("expertId",user.getId());
			List<Map<String,Object>> pdomains = expertService.findDomainsByExpertId(map);
			
			//如果擅长领域有子领域则获得所有子领域
			if(pdomains.size()>0){
				for(int i=0;i<pdomains.size();i++){
					//获得擅长领域nodepath
					int dId = Integer.parseInt((String) pdomains.get(i).get("id"));
					String dnodePath = domainService.findDomainByPrimaryKey(dId).getNodePath();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("nodePath", dnodePath);
					
					//获得该领域的所有子领域及自己。并把所有获得的分类id存在domains里
					List<Domain> domainls = domainService.selectAllChildDomains(params);
					if(domainls.size()>0){
						for(int j =0;j<domainls.size();j++){
							Map<String, Object> hm = new HashMap<String, Object>();
							hm.put("id", domainls.get(j).getId());
							domains.add(hm);
						}
					}
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("size", size);
			params.put("start", (p - 1) * size);
			
			//如果用户没有设置擅长领域则选出最新的未解决问题 ，否则在用户擅长领域选择问题
			if(pdomains.size()==0){
				questions = questionService.getQuestionsRecommendToExpert(params);
				count = questionService.getQuestionsRecommendToExpertNum(params);
			}else{
				//获得用户领域内问题
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<domains.size();i++){
					int domainId = Integer.parseInt(domains.get(i).get("id").toString());
					if(i==0){
						sb.append("(");
						sb.append(domainId);
					}else{
						sb.append(",");
						sb.append(domainId);
					}
				}
				sb.append(")");
				String domainIds = sb.toString();
				params.put("domainIds", domainIds);
				questions = questionService.getQuestionsRecommendToExpert(params);
				count = questionService.getQuestionsRecommendToExpertNum(params);
			}
			//如果推荐问题超过50条则只取50条作为信息总数否则取真实条数
			if(count>50){
				count = 50;
			}
			return SUCCESS;
		} catch (ServiceException e) {
			logger.error("execute, service exception", e);
			this.addActionError("ServiceException");
			e.printStackTrace();
			return ERROR;
		} catch (UnsupportedEncodingException e) {
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

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
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

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}	
	
}
