package com.ah3nong.wd.action.expert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertAttachmentService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;
import com.ah3nong.wd.util.StringHelper;

public class NewExpertAction extends GenericActionSupport<Expert> {
	/**
	 * 添加专家表
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private Expert expert;
	private ExpertService expertService;
	private DomainService domainService;
	private ExpertAttachmentService expertAttachmentService;
	private UserService userService;
	private User user;
	private String username;
	private String info;
	private String recommended;
	private List<Domain> domains;
	private String domainsStr;
	private String[] domainIds = null;
	private int id;

	private File expertImgs;
	private String expertImgsContentType;
	private String expertImgsFileName;
	private String newStr;
	private String[] selectedDomains;
	// 附件
	private List<File> picAttachments;
	private List<String> picAttachmentsFileName;

	private List<File> movieAttachments;
	private List<String> movieAttachmentsFileName;
	
	private String picType;
	private String movieType;

	@Transactional(rollbackFor = Exception.class)
	public String addExpert() {
		try {
			// 1.保存信息到用户表
			int userId = user.getId();
			if(userId == 0){
				User tmpUser = userService.findUserByUsername(user.getUsername());
				if(tmpUser != null ){
					userId = tmpUser.getId();
				}
			}
			
			if(userId == 0){
				request.setAttribute("info", "该用户不存在...");
				return ERROR;
			}
			// 2.保存专家信息
			String method = "NewExpertAction";
			log.info(method, "is start!");
			// html过滤
			expert.setSummary(StringHelper.encodeHTML(expert.getSummary()));
			expert.setOrganization(StringHelper.encodeHTML(expert.getOrganization()));
			expert.setResume(StringHelper.encodeHTML(expert.getResume()));

			if (expert == null) {
				log.error(method + ", expert is null");
			}

			// 上传图片控制
			String URL = null;
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String DIR = Config.getString("Img_dir");
			if (expertImgsFileName != null) {

				String type = expertImgsFileName.substring(expertImgsFileName.lastIndexOf(".")).toLowerCase();
				InputStream is = new FileInputStream(expertImgs);
				String time = sdFormat.format(System.currentTimeMillis());
				URL = DIR + time + type;
				OutputStream os = new FileOutputStream(URL);
				expert.setPhotoUrl("uploadIMG/" + time + type);
				byte buffer[] = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
				}
				os.close();
				is.close();
			}
			expert.setId(userId);
			if (recommended.equals("T")) {
				expert.setRecommended(1);
			} else {
				expert.setRecommended(0);
			}
			
			List<String> list = new ArrayList<String>();
			if (selectedDomains != null) {
				domainIds = new String[selectedDomains.length];
				for (int j = 0; j < selectedDomains.length; j++) {
					if ("0000".equals(selectedDomains[j].substring(2, 6))) {
						String nodePath = selectedDomains[j].substring(0, 2);
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					} else if ("00".equals(selectedDomains[j].substring(4, 6))) {
						String nodePath = selectedDomains[j].substring(0, 4);
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					} else {
						String nodePath = selectedDomains[j];
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					}
					;
				}
				for (int i = 0; i < domainIds.length; i++) {
					if (!domainIds[i].equals("") && !list.contains(domainIds[i])) {
						list.add(domainIds[i]);
					}
				}
			}

			String[] newDomainIds = list.toArray(new String[1]);
			user.setExpert(1);
			expertService.addExpert(expert, user, newDomainIds, expert.getId());

			// 3.保存专家附件
			// 图片
			String eDIR = Config.getString("expert_attachment_dir");
			if (picAttachments != null) {
				for (int i = 0; i < picAttachments.size(); i++) {
					String fileName = picAttachmentsFileName.get(i);
					String type = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					// 验证上传附件
					String attachmentType = Config.getString("PIC_TYPE");
					if (attachmentType.indexOf(type) < 0) {
						request.setAttribute("msg", "IMGFAIL");
						return INPUT;
					}

					InputStream picIs;
					picIs = new FileInputStream(picAttachments.get(i));
					String time = sdFormat.format(System.currentTimeMillis());
					String eURL = eDIR + time + "_" + userId +"_" + i + "." + type;
					OutputStream picOs = new FileOutputStream(eURL);
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = picIs.read(buffer)) > 0) {
						picOs.write(buffer, 0, count);
					}
					picOs.close();
					picIs.close();
					
					ExpertAttachment expertAttachment = new ExpertAttachment();
					expertAttachment.setName(fileName);
					expertAttachment.setPath(time + "_" + userId +"_" + i + "." + type);
					expertAttachment.setType(Config.getInt("PIC_TYPE_ID"));
					expertAttachment.setUserId(userId);
					expertAttachmentService.addExpertAttachment(expertAttachment);
				}
			}

			// 视频
			if (movieAttachments != null) {
				for (int i = 0; i < movieAttachments.size(); i++) {
					String fileName = movieAttachmentsFileName.get(i);
					String type = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					// 验证上传附件
					String attachmentType = Config.getString("MOVIE_TYPE");
					if (attachmentType.indexOf(type) < 0) {
						request.setAttribute("msg", "IMGFAIL");
						return INPUT;
					}

					InputStream movieIs;
					movieIs = new FileInputStream(movieAttachments.get(i));
					String time = sdFormat.format(System.currentTimeMillis());
					String eURL = eDIR + time + "_" + userId +"_" + i + "." + type;

					OutputStream movieOs = new FileOutputStream(eURL);
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = movieIs.read(buffer)) > 0) {
						movieOs.write(buffer, 0, count);
					}
					movieOs.close();
					movieIs.close();
					ExpertAttachment expertAttachment = new ExpertAttachment();
					expertAttachment.setName(fileName);
					expertAttachment.setPath(time + "_" + userId +"_" + i + "." + type);
					expertAttachment.setType(Config.getInt("MOVIE_TYPE_ID"));
					expertAttachment.setUserId(userId);
					expertAttachmentService.addExpertAttachment(expertAttachment);
				}
			}
			request.setAttribute("info", "添加专家成功，请刷新页面查看！");
			return "success";
		} catch (Exception e) {
			e.getStackTrace();
			return "error";
		}
	}

	public String NewExpert() {
		try {
			newStr = new String(username.getBytes("iso-8859-1"),"utf-8");
			username = newStr;
			domains = domainService.findAllDomain();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < domains.size(); i++) {
				Domain d = domains.get(i);
				if (d.getNodePath().length() == 2) {
					sb.append(d.getNodePath() + "0000|");
				} else if (d.getNodePath().length() == 4) {
					sb.append(d.getNodePath() + "00|");
				} else {
					sb.append(d.getNodePath() + "|");
				}
				sb.append(d.getName());
				sb.append(",");
			}
			domainsStr = sb.toString();
			
			//附件类型
			picType = Config.getString("PIC_TYPE");
			movieType = Config.getString("MOVIE_TYPE");
			return SUCCESS;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public File getExpertImgs() {
		return expertImgs;
	}

	public void setExpertImgs(File expertImgs) {
		this.expertImgs = expertImgs;
	}

	public String getExpertImgsContentType() {
		return expertImgsContentType;
	}

	public void setExpertImgsContentType(String expertImgsContentType) {
		this.expertImgsContentType = expertImgsContentType;
	}

	public String getExpertImgsFileName() {
		return expertImgsFileName;
	}

	public void setExpertImgsFileName(String expertImgsFileName) {
		this.expertImgsFileName = expertImgsFileName;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public DomainService getDomainService() {
		return domainService;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public String getDomainsStr() {
		return domainsStr;
	}

	public void setDomainsStr(String domainsStr) {
		this.domainsStr = domainsStr;
	}

	public String[] getDomainIds() {
		return domainIds;
	}

	public void setDomainIds(String[] domainIds) {
		this.domainIds = domainIds;
	}

	public String[] getSelectedDomains() {
		return selectedDomains;
	}

	public void setSelectedDomains(String[] selectedDomains) {
		this.selectedDomains = selectedDomains;
	}

	public String getNewStr() {
		return newStr;
	}

	public void setNewStr(String newStr) {
		this.newStr = newStr;
	}

	public ExpertService getExpertService() {
		return expertService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public void setPicAttachments(List<File> picAttachments) {
		this.picAttachments = picAttachments;
	}

	public void setPicAttachmentsFileName(List<String> picAttachmentsFileName) {
		this.picAttachmentsFileName = picAttachmentsFileName;
	}

	public void setMovieAttachments(List<File> movieAttachments) {
		this.movieAttachments = movieAttachments;
	}

	public void setMovieAttachmentsFileName(List<String> movieAttachmentsFileName) {
		this.movieAttachmentsFileName = movieAttachmentsFileName;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
