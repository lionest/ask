package com.ah3nong.wd.action.probing;

import java.util.Date;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.ReplyService;

public class UpdateProbingStatusAction extends GenericActionSupport<Probing> {
	private static final long serialVersionUID = -3312887913079616232L;
	private ProbingService probingService;
	private ReplyService replyService;
	private NoticeService noticeService;

	private int id;
	private int status;
	
	public String execute() {
		try {
			Probing probing = new Probing();
			probing.setId(id);
			probing.setStatus(status);
			probingService.updateProbingByPrimaryKey(probing, true);
			
			probing = probingService.findProbingByPrimaryKey(id);
			if(status==Config.getInt("PROBING_UNREPLIED")){
				// 增加一个Notice
				Reply tmp = replyService.findReplyByPrimaryKey(probing.getReplyId());
				Notice notice = new Notice();
				notice.setQuestionId(tmp.getQuestionId());
				notice.setUserId(tmp.getUser().getId());
				notice.setType(Config.getInt("NOTICE_PROBING"));
				notice.setCreatedTime(new Date());
				noticeService.addNotice(notice);
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

}
