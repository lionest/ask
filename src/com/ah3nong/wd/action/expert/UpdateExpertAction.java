package com.ah3nong.wd.action.expert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class UpdateExpertAction extends GenericActionSupport<Expert> {
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
	private User user = new User();
	private String username;
	private String info;
	private String recommended;
	private List<Domain> domains;
	private String[] domainIds = null;

	private File expertImgs;
	private String expertImgsContentType;
	private String expertImgsFileName;
	private int id;
	private String[] selectedDomains;
	// 附件
	private List<File> picAttachments;
	private List<String> picAttachmentsFileName;

	private List<File> movieAttachments;
	private List<String> movieAttachmentsFileName;

	public String execute() {
		String method = "NewExpertAction";
		log.info(method, "is start!");
		// html过滤
		expert.setSummary(StringHelper.encodeHTML(expert.getSummary()));
		expert.setOrganization(StringHelper.encodeHTML(expert.getOrganization()));
		expert.setResume(StringHelper.encodeHTML(expert.getResume()));

		if (expert == null) {
			log.error(method + ", expert is null");
		}
		try {
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
			expert.setId(id);
			if (recommended.equals("T")) {
				expert.setRecommended(1);
			} else {
				expert.setRecommended(0);
			}
			user.setExpert(1);
			user.setUsername(username);

			List<String> list = new ArrayList<String>();
			//如果选择了专家领域则对专家领域的NodePath处理后找到对应的domainId进行添加
			if(selectedDomains!=null){
			domainIds = new String[selectedDomains.length];
				//一级领域传来的nodePath值为 010000 ;二级 010100 ;三级010101.此处进行处理
				for(int j=0;j<selectedDomains.length;j++){
					if("0000".equals(selectedDomains[j].substring(2, 6))){
						String nodePath = selectedDomains[j].substring(0,2);
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					}else if("00".equals(selectedDomains[j].substring(4, 6))){
						String nodePath = selectedDomains[j].substring(0,4);
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					}else{
						String nodePath = selectedDomains[j];
						Domain tmp = domainService.findDomainByNodePath(nodePath);
						domainIds[j] = tmp.getId().toString();
					};
				}
				for(int i=0;i<domainIds.length;i++){
					if(!domainIds[i].equals("")&&!list.contains(domainIds[i])){
						list.add(domainIds[i]);
					}
				}
			}
			String[] newDomainIds = list.toArray(new String[1]);
			expertService.updateExpert(expert, user, newDomainIds, expert.getId());
			// user.getUserid()+ "*****************************]");
			// System.out.println(expert.toString());
			//增加附件
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
					String eURL = eDIR + time + "_" + id +"_" + i + "." + type;
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
					expertAttachment.setPath(time + "_" + id +"_" + i + "." + type);
					expertAttachment.setType(Config.getInt("PIC_TYPE_ID"));
					expertAttachment.setUserId(id);
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
					String eURL = eDIR + time + "_" + id +"_" + i + "." + type;

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
					expertAttachment.setPath(time + "_" + id +"_" + i + "." + type);
					expertAttachment.setType(Config.getInt("MOVIE_TYPE_ID"));
					expertAttachment.setUserId(id);
					expertAttachmentService.addExpertAttachment(expertAttachment);
				}
			}
			
			info = "修改专家成功！";
			return "success";
		} catch (ServiceException e) {
			log.error(method, e);
			e.getStackTrace();
			info = "修改专家失败！";
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("checkeImg failed!+e");
			e.printStackTrace();
			info = "修改专家失败！";
			return "error";
		}
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public ExpertService getExpertService() {
		return expertService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public DomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public String[] getDomainIds() {
		return domainIds;
	}

	public void setDomainIds(String[] domainIds) {
		this.domainIds = domainIds;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getSelectedDomains() {
		return selectedDomains;
	}

	public void setSelectedDomains(String[] selectedDomains) {
		this.selectedDomains = selectedDomains;
	}

	public void setExpertAttachmentService(ExpertAttachmentService expertAttachmentService) {
		this.expertAttachmentService = expertAttachmentService;
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
	
}
