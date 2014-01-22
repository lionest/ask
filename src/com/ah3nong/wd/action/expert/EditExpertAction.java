package com.ah3nong.wd.action.expert;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertAttachmentService;
import com.ah3nong.wd.service.ExpertService;

public class EditExpertAction extends GenericActionSupport<Expert>{
	private static final long serialVersionUID = 6444723142585453113L;
	/**
	 * 读取专家信息
	 */
	private int id ;
	private String username;
	private ExpertService expertService ;
	private Map<String, Object> expert;
	private List<Domain> domains;
	private DomainService domainService;
	private ExpertAttachmentService expertAttachmentService;
	private String domainsStr ;
	private List<ExpertAttachment> attachments;
	private String picType;
	private String movieType;
	private List<Map<String,Object>> selectedDomains;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			List<Map<String, Object>> list = expertService.getExpertsByExpertId(id);
			expert = list.get(0);
			selectedDomains = (List<Map<String, Object>>) expert.get("domains");
			for(int j = 0 ;j < selectedDomains.size() ;j++){
				Domain tmpd = domainService.findDomainByPrimaryKey(Integer.parseInt((String) selectedDomains.get(j).get("id")));
				if(tmpd.getNodePath().length()==2){
					selectedDomains.get(j).put("nodePath", tmpd.getNodePath()+"0000");
				}else if(tmpd.getNodePath().length()==4){
					selectedDomains.get(j).put("nodePath",tmpd.getNodePath()+"00");
				}else{
					selectedDomains.get(j).put("nodePath",tmpd.getNodePath());
				}
			}
			domains = domainService.findAllDomain();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<domains.size();i++){
				Domain d = domains.get(i);
				if(d.getNodePath().length()==2){
					sb.append(d.getNodePath()+"0000|");
				}else if(d.getNodePath().length()==4){
					sb.append(d.getNodePath()+"00|");
				}else{
					sb.append(d.getNodePath()+"|");
				}
				sb.append(d.getName());
				sb.append(",");
			}
			domainsStr = sb.toString();
			
			attachments = expertAttachmentService.findByUserId(id);
			
			//附件类型
			picType = Config.getString("PIC_TYPE");
			movieType = Config.getString("MOVIE_TYPE");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getExpert() {
		return expert;
	}

	public void setExpert(Map<String, Object> expert) {
		this.expert = expert;
	}

	public DomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ExpertService getExpertService() {
		return expertService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}
	
	public String getDomainsStr() {
		return domainsStr;
	}

	public void setDomainsStr(String domainsStr) {
		this.domainsStr = domainsStr;
	}

	public List<ExpertAttachment> getAttachments() {
		return attachments;
	}

	public void setExpertAttachmentService(ExpertAttachmentService expertAttachmentService) {
		this.expertAttachmentService = expertAttachmentService;
	}

	public String getPicType() {
		return picType;
	}

	public String getMovieType() {
		return movieType;
	}

	public List<Map<String, Object>> getSelectedDomains() {
		return selectedDomains;
	}

}
