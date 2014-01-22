package com.ah3nong.wd.service;

import java.util.List;

import com.ah3nong.wd.bean.ExpertAttachment;

public interface ExpertAttachmentService {
	// 增
	public void addExpertAttachment(ExpertAttachment expertAttachment);

	// 根据ID查询
	public ExpertAttachment findByPrimarykey(int id);

	// 根据用户id和type查询
	public List<ExpertAttachment> findByUserId(int userId );

	// 根据ID删除
	public void deleteByPrimarykey(int id);
}
