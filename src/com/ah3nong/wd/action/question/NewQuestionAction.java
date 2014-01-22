package com.ah3nong.wd.action.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.UserService;

public class NewQuestionAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 8287527356649789271L;

	private DomainService domainService;
	private ExpertService expertService;
	private UserService userService;
	private int t;
	private int domainId;
	private Domain domain;
	private int askAll;
	/*
	 * 格式样例： [{parentId:0,id:1,name:"a1"}, {parentId:1,id:2,name:"a2"},.....]
	 */
	private String domainJson;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		String method = "findDomainListToAddQuestion";
		
		logger.info(method+",the method is begin!");
		try {
			//判断是否已经选择分类
			if(domainId>0){
				domain = domainService.findDomainByPrimaryKey(domainId);
			}
			
			if(t!=0){
				Expert e=expertService.findExpertByPrimaryKey(t);
				
				if(e!=null){
					User user=userService.findUserByPrimaryKey(e.getId());
					if(user.getFullName()!=null&&!"".equals(user.getFullName())){
						e.setFullName(user.getFullName());
					}else{
						e.setFullName(user.getUsername());
					}					
					request.setAttribute("expert", e);
					
					List<Map<String,Object>> expertDomains = domainService.findDomainByUserId(t);
					request.setAttribute("expertDomains", expertDomains);
				}
			}
			
			List<Domain> mains = new ArrayList<Domain>();
			int parentId = 0;
			if(request.getSession().getAttribute("domainParentId")!=null){
				parentId = (Integer) request.getSession().getAttribute("domainParentId");
			}
			
			if (domainId==0 && parentId < 0 && askAll!=1){
				mains = domainService.findAllDomainsByParentId(parentId);
			}else{
				mains = domainService.findAllDomain();
			}
			logger.info("find the domains success!"+mains);
			domainJson= domainService
					.convertDomainToJson(mains);
			request.setAttribute("domainJson", domainJson);
		} catch (ServiceException e) {
			logger.error("find the domains failed!",e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getDomainJson() {
		return domainJson;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setT(int t) {
		this.t = t;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setAskAll(int askAll) {
		this.askAll = askAll;
	}
	
}
