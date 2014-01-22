package com.ah3nong.wd.action.audit;

import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ReplyService;

public class ViewReplyHisAction extends GenericActionSupport<Reply> {

	private static final long serialVersionUID = -3729646257086353903L;
	private ReplyService replyService;
	private String status;

	public String execute(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status",status);
			pager = replyService.getReplyHisPagerByParam(params, pageNum, 10);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
}
