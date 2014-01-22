package com.ah3nong.wd.dao.impl;

import java.util.List;

import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ExpertAttachmentDao;

public class ExpertAttachmentDaoImpl extends BaseDao<ExpertAttachment> implements ExpertAttachmentDao {

	@Override
	public void addExpertAttachment(ExpertAttachment expertAttachment) {
		getSqlMapClientTemplate().insert("wd_expert_attachment.insert",expertAttachment);
	}

	@Override
	public ExpertAttachment findByPrimarykey(int id) {
		ExpertAttachment ea = new ExpertAttachment();
		ea.setId(id);
		return (ExpertAttachment) getSqlMapClientTemplate().queryForObject("wd_expert_attachment.selectByPrimaryKey", ea);
	}

	@Override
	public List<ExpertAttachment> findByUserId(int userId) {
		ExpertAttachment ea = new ExpertAttachment();
		ea.setUserId(userId);
		return getSqlMapClientTemplate().queryForList("wd_expert_attachment.selectByUserId", ea);
	}

	@Override
	public void deleteByPrimarykey(int id) {
		ExpertAttachment ea = new ExpertAttachment();
		ea.setId(id);
		getSqlMapClientTemplate().delete("wd_expert_attachment.deleteByPrimaryKey", ea);
	}

}
