package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.dao.NoticeDao;
import com.ah3nong.wd.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao;
	@Override
	public void addNotice(Notice notice) {
		noticeDao.addNotice(notice);
	}

	@Override
	public void updateStatusByQuestionIdAndUserId(Notice notice) {
		noticeDao.updateStatusByQuestionIdAndUserId(notice);
	}

	@Override
	public List<Map<String, Object>> findQuestionByUserIdAndType(Notice notice) {
		return noticeDao.findQuestionByUserIdAndType(notice);
	}

	@Override
	public int countByUserIdAndType(Notice notice) {
		return noticeDao.countByUserIdAndType(notice);
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

}
