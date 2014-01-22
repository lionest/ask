package com.ah3nong.wd.action.center;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class UserCenterAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -3729646257086353903L;
	private int pageSize = 12;
	private Pager<Question> pager;
	private QuestionService questionService;
	private UserService userService;
	private String s;
	private String t1;
	private int currentPage;

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String execute() {
		logger.info("some one wanna enter user center...");
		try {
			Object userObj = (User)SecurityContext.getUser();
			User currentUser = (User) userObj;
			int userid = currentUser.getId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userid);

			params.put("size", pageSize);
			if (currentPage == 0) {
				currentPage = 1;
			}
			pager = new Pager<Question>(currentPage, pageSize);
			if (t1 == null) {
				t1 = "q";
			}
			if (t1.equals("q")) {
				if (s == null) {
					pager.setTotalRecords(questionService
							.countQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findQuestionsByUserId(params));
				} else if(Integer.parseInt(s)==2){
					pager.setTotalRecords(questionService
							.countResolvedQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findResolvedQuestionsByUserId(params));
					request.setAttribute("s", 2);
				}else if(Integer.parseInt(s)==1){
					pager.setTotalRecords(questionService
							.countNoResolvedQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findNoResolvedQuestionsByUserId(params));
					request.setAttribute("s", 1);
				}
			} else if(t1.equals("e")){
				if (s == null) {
					//int count = questionService.countQuestionsAskForExpert(params);
					pager.setTotalRecords(questionService
							.countQuestionsAskForExpert(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findQuestionsAskForExpert(params));
				} else if (Integer.parseInt(s) == 2){
					pager.setTotalRecords(questionService
							.countResoveQuestionsAskForExpert(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findResoveQuestionsAskForExpert(params));
					request.setAttribute("s", 2);
				}else if (Integer.parseInt(s) ==1){
					pager.setTotalRecords(questionService
							.countNoResoveQuestionsAskForExpert(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findNoResoveQuestionsAskForExpert(params));
					request.setAttribute("s", 1);
				}
			} else if(t1.equals("c")){
				if (s == null) {
					pager.setTotalRecords(questionService
							.countCommentQuestionByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findCommentQuestionByUserId(params));
				} else if(Integer.parseInt(s) == 2){
					pager.setTotalRecords(questionService
							.countCommentQuestionResovedByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findCommentQuestionResovedByUserId(params));
					request.setAttribute("s", 2);
				}else if(Integer.parseInt(s) == 1){
					pager.setTotalRecords(questionService
							.countCommentQuestionNoResovedByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findCommentQuestionNoResovedByUserId(params));
					request.setAttribute("s", 1);
				}
			}else{
				if (s == null) {
					pager.setTotalRecords(questionService
							.countReplyQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findReplyQuestionsByUserId(params));
				} else if(Integer.parseInt(s) == 2){
					pager.setTotalRecords(questionService
							.countReplyResolvedQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findReplyResolvedQuestionsByUserId(params));
					request.setAttribute("s", 2);
				}else if(Integer.parseInt(s) == 1){
					pager.setTotalRecords(questionService
							.countReplyNoResolvedQuestionsByUserId(params));
					params.put("start", (currentPage - 1) * pageSize);
					pager.setPageRecords(questionService
							.findReplyNoResolvedQuestionsByUserId(params));
					request.setAttribute("s", 1);
				}
			}
			//获取提问者详情
			List<Question> list = pager.getPageRecords();
			if(list.size()>0&&("c".equals(t1)||"r".equals(t1))){
				for(int k=0;k<list.size();k++){
					User tmp = userService.findUserByPrimaryKey(list.get(k).getUser().getId());
					list.get(k).setUser(tmp);
				}
				pager.setPageRecords(list);
			}
			request.setAttribute("pager", pager);
			request.setAttribute("t1", t1);
			
		} catch (ServiceException e) {
			logger.error("execute, service exception", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setS(String s) {
		this.s = s;
	}

	public void setPager(Pager<Question> pager) {
		this.pager = pager;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
