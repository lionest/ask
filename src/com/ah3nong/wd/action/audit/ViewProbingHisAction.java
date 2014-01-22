package com.ah3nong.wd.action.audit;

import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ProbingService;

public class ViewProbingHisAction extends GenericActionSupport<Probing> {

	private static final long serialVersionUID = -3729646257086353903L;
	private ProbingService probingService;
	private String status;
	public String execute(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", status);
			pager=probingService.findProbingByStatusPager(params, pageNum, 10);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
