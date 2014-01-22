package com.ah3nong.wd.action.expert;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.service.ExpertAttachmentService;

public class DeleteExpertAttachmentAction extends GenericActionSupport<ExpertAttachment>{
	private static final long serialVersionUID = 8122691188859792008L;
	private ExpertAttachmentService expertAttachmentService;
	
	private int id;
	
	public String execute(){
		expertAttachmentService.deleteByPrimarykey(id);
		return SUCCESS;
	}

	public void setExpertAttachmentService(ExpertAttachmentService expertAttachmentService) {
		this.expertAttachmentService = expertAttachmentService;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
