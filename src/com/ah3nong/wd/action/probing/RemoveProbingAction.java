package com.ah3nong.wd.action.probing;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.ReplyService;

public class RemoveProbingAction extends GenericActionSupport<Probing> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ProbingService probingService;
	private ReplyService replyService;
	private Probing probing;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method="remove probing action!";		
		try {
			
			
			probing.setStatus(Config.getInt("PROBING_REMOVE"));
			probingService.updateProbingByPrimaryKey(probing, true);
			probing=probingService.findProbingByPrimaryKey(probing.getId());
			//删除追问，则把这则追问所对应的上层回复的状态改为“未追问”
			Reply reply = new Reply();
			reply.setId(probing.getReplyId());
			reply.setStatus(Config.getInt("REPLY_UNPROBING"));
			replyService.updateReplyByPrimaryKey(reply, true);
			request.setAttribute("questionId", probing.getQuestionId());
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
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

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	

}
