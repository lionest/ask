package com.ah3nong.wd.service.impl;

import java.util.List;

import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.dao.ExpertAttachmentDao;
import com.ah3nong.wd.service.ExpertAttachmentService;

public class ExpertAttachmentServiceImpl implements ExpertAttachmentService {
	
	private ExpertAttachmentDao expertAttachmentDao;
	
	public void setExpertAttachmentDao(ExpertAttachmentDao expertAttachmentDao) {
		this.expertAttachmentDao = expertAttachmentDao;
	}

	@Override
	public void addExpertAttachment(ExpertAttachment expertAttachment) {
		expertAttachmentDao.addExpertAttachment(expertAttachment);
	}

	@Override
	public ExpertAttachment findByPrimarykey(int id) {
		return expertAttachmentDao.findByPrimarykey(id);
	}

	@Override
	public List<ExpertAttachment> findByUserId(int userId ) {
		return expertAttachmentDao.findByUserId(userId );
	}

	@Override
	public void deleteByPrimarykey(int id) {
		expertAttachmentDao.deleteByPrimarykey(id);
	}

}
