package com.ah3nong.wd.action.probing;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.util.StringHelper;

public class EditProbingAction extends GenericActionSupport<Probing> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ProbingService probingService;
	private KeywordService keywordService;
	private SysdataService sysdataService;
	private Probing probing;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		probing.setContent(StringHelper.encodeHTML(probing.getContent()));
		try {
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

			probing.setUpdatedTime(new Date());
			probingService.updateProbingByPrimaryKey(probing, true);
			probing = probingService.findProbingByPrimaryKey(probing.getId());
			request.setAttribute("questionId", probing.getQuestionId());
			if(probing.getStatus()==Config.getInt("PROBING_AUDITING")){
				request.setAttribute("tips", "成功修改追问，追问将在审核通过后在页面显示！");
			}else{
				request.setAttribute("tips", "成功修改追问！");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}

}
