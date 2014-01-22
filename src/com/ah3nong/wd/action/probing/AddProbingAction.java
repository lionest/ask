package com.ah3nong.wd.action.probing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.ReplyService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.util.StringHelper;

public class AddProbingAction extends GenericActionSupport<Probing> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ProbingService probingService;
	private ReplyService replyService;
	private KeywordService keywordService;
	private NoticeService noticeService;
	private SysdataService sysdataService;
	private Probing probing;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		probing.setContent(StringHelper.encodeHTML(probing.getContent()));
		request.setAttribute("questionId", probing.getQuestionId());

		try {
			// 判断追问是否重复
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", probing.getQuestionId());
			List<Probing> proList = probingService.findProbingByQuestionId(map);
			if (proList != null && proList.size() > 0) {
				for (int i = 0; i < proList.size(); i++) {
					int rid = probing.getReplyId();
					int tmp = proList.get(i).getReply().getId();
					if (rid == tmp) {
						request.setAttribute("tips", "请勿重复提交追问...");
						return SUCCESS;
					}
				}
			}
			
			int audit = Integer.parseInt(sysdataService.findByName("auditProbing").getContent());
			// 1.如果用户选择的为关键字审核
			if (audit == 2) {
				// 如果包含关键字，设置追问状态为审核中
				if (probing.getContent() != null && keywordService.findKeywordInString(probing.getContent())) {
					probing.setStatus(Config.getInt("PROBING_AUDITING"));
					// 否则设置为正常状态
				} else {
					probing.setStatus(Config.getInt("PROBING_UNREPLIED"));
				}

			// 2.如果用户选择的为全部审核
			} else if (audit == 1) {
				probing.setStatus(Config.getInt("PROBING_AUDITING"));

			// 3.如果用户选择的为全部不审核
			} else {
				probing.setStatus(Config.getInt("PROBING_UNREPLIED"));
			}

			Reply reply = new Reply();
			reply.setId(probing.getReplyId());
			reply.setStatus(Config.getInt("REPLY_PROBINGED"));
			replyService.updateReplyByPrimaryKey(reply, true);

			probing.setCreatedTime(new Date());
			probingService.addProbing(probing);
			
			if(probing.getStatus()==Config.getInt("PROBING_AUDITING")){
				request.setAttribute("tips", "追问添加成功，审核通过后将会在页面显示！");
			}else{
				request.setAttribute("tips", "追问添加成功！");
				
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

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}

	public Probing getProbing() {
		return probing;
	}

	public void setProbing(Probing probing) {
		this.probing = probing;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public ReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
