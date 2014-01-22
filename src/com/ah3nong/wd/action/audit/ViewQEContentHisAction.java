package com.ah3nong.wd.action.audit;

import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class ViewQEContentHisAction extends GenericActionSupport<QuestionExtraContentHis> {

	private static final long serialVersionUID = -3729646257086353903L;
	private QuestionService questionService;
	private QuestionExtraContentHis qEContentHis;
	
	private Map<String,Integer> statusMap;
	
	public String execute(){
		String method = "query questionExtraContentHis by page Action!";
		try {
			statusMap = new HashMap<String,Integer>();
			statusMap.put("未审核", Config.getInt("AUDIT_AUDITING"));
			statusMap.put("审核通过", Config.getInt("AUDIT_PASSED"));
			statusMap.put("审核未通过", Config.getInt("AUDIT_NOT_PASS"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			if(qEContentHis!=null){
				if(qEContentHis.getStatus()!=0){
					logger.info("Got the param with status="+qEContentHis.getStatus());
					params.put("status", qEContentHis.getStatus());
				}
				if(qEContentHis.getExtraContent()!=null&&!qEContentHis.getExtraContent().equals("")){
					logger.info("Got the param with content="+qEContentHis.getExtraContent());
					params.put("content",qEContentHis.getExtraContent());
				}
			}
			pager = questionService.getQuestionExtraContentHisPagerByParam(params, pageNum, 10);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public QuestionExtraContentHis getQEContentHis() {
		return qEContentHis;
	}


	public void setQEContentHis(QuestionExtraContentHis contentHis) {
		qEContentHis = contentHis;
	}


	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, Integer> statusMap) {
		this.statusMap = statusMap;
	}
	
	

}
