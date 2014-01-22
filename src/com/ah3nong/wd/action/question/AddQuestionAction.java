package com.ah3nong.wd.action.question;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.service.impl.QuestionServiceImpl;
import com.ah3nong.wd.util.StringHelper;

public class AddQuestionAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 2373740238689012088L;
	private Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	private QuestionService questionService;
	private KeywordService keywordService;
	private SysdataService sysdataService;
	
	private Question question;
	private QuestionExpert questionExpert;
	// 上传图片
	private List<File> questionImgs;
	private File questionImg;
	private List<String> questionImgsContentType;
	private List<String> questionImgsFileName;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		String method = "addQuestionAction";
		log.info(method + ", the method is begin!");

		User user = SecurityContext.getUser();
		if(user==null){
			request.setAttribute("msg", "NULLUSER");
			return SUCCESS;
		}
		
		try {
			question.setSubject(StringHelper.encodeHTML(question.getSubject()));
			question.setContent(StringHelper.encodeHTML(question.getContent()));

			// 判断是否为重复提问
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("start", 0);
			params.put("size", 1);
			List<Question> questions = questionService.findQuestionsByUserId(params);
			if (questions != null && questions.size() > 0) {
				Date d1 = new Date();
				Date d2 = (Date) questions.get(0).getCreatedTime();
				long timespace = d1.getTime() - d2.getTime();
				int askTmeInterval = Integer.parseInt(sysdataService.findByName("askTmeInterval").getContent());
				if (timespace < askTmeInterval*60000) {
					request.setAttribute("msg", "ERROR");
					return SUCCESS;
				}
			}
			
			//根据用户配置的AUDIT_QUESTION判断是否需要审核问题
			int audit = Integer.parseInt(sysdataService.findByName("auditQuestion").getContent());
			
			// 1.如果用户选择的为关键字审核
			if(audit==2){
				//如果包含关键字，设置问题状态为审核中
				if(keywordService.findKeywordInString(question.getSubject())||keywordService.findKeywordInString(question.getContent())){
					question.setStatus(Config.getInt("QUESTION_AUDITING"));
				//否则设置为正常状态
				}else{
					question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
				}
				
			// 2.如果用户选择的为全部审核
			}else if(audit==1){
				question.setStatus(Config.getInt("QUESTION_AUDITING"));
				
			// 3.如果用户选择的为全部不审核
			}else{
				question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
			}
			
			question.setCreatedTime(new Date());
			question.setRecommended(Config.getInt("NOT_RECOMMEND"));
			question.setReplyNum(0);
			question.setViewCount(0);
			question.setUserId(user.getId());

			if (question.getDomainId() != null) {
				question.setDomainId(question.getDomainId());
			}

			if (questionExpert != null && questionExpert.getExpertId() != 0) {
				log.info("get param with expertId =" + questionExpert.getExpertId());
			}
			// 上传图片控制
			List<QuestionImg> questionImgList = new ArrayList<QuestionImg>();
			String DIR = Config.getString("Img_dir");
			QuestionImg questionImg = null;
			String URL = null;
			int order = 1;
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			if (questionImgs != null) {
				for (int i = 0; i < questionImgs.size(); i++) {
					String fileName = questionImgsFileName.get(i);
					log.info("get param with ImgName =" + fileName);
					String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					// 验证上传图片
					if (!(".jpg".equals(type) || ".png".equals(type) || ".gif".equals(type) || ".bmp".equals(type))) {
						request.setAttribute("msg", "IMGFAIL");
						return INPUT;
					}
					long fileSize = questionImgs.get(i).length();
					if (fileSize > 1024 * 1024) {
						request.setAttribute("msg", "IMGFAIL");
						return INPUT;
					}
					InputStream is = new FileInputStream(questionImgs.get(i));
					String time = sdFormat.format(System.currentTimeMillis());
					URL = DIR + time + "_" + order + type;

					OutputStream os = new FileOutputStream(URL);
					questionImg = new QuestionImg();
					questionImg.setUrl("uploadIMG/" + time + "_" + order + type);
					questionImg.setImgOrder(order);
					questionImg.setStatus(1);
					order++;
					questionImgList.add(questionImg);
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = is.read(buffer)) > 0) {
						os.write(buffer, 0, count);
					}
					os.close();
					is.close();
				}
			};
			if (questionExpert.getExpertId() == 0) {
				questionExpert = null;
			}
			int qid = questionService.addQuestion(question, questionExpert, questionImgList);
			logger.info("addQuestion,add QuestionExpert,add QuestionImg success !");
			
			//根据用户问题状态判断页面提示
			if(question.getStatus()==Config.getInt("QUESTION_AUDITING")){
				request.setAttribute("msg", "AUDITTING");
			}else{
				request.setAttribute("msg", "SUCCESS");
			}
			
			request.setAttribute("qid", qid);
			request.setAttribute("questionId", question.getId());
		} catch (ServiceException e) {
			log.error("addQuestion failed.", e);
			e.printStackTrace();
			request.setAttribute("msg", "FAIL");
		} catch (IOException e) {
			log.error("IO exception with addQuestion.", e);
			e.printStackTrace();
			request.setAttribute("msg", "FAIL");
		}

		return SUCCESS;
	}

	public File getQuestionImg() {
		return questionImg;
	}

	public void setQuestionImg(File questionImg) {
		this.questionImg = questionImg;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<File> getQuestionImgs() {
		return questionImgs;
	}

	public void setQuestionImgs(List<File> questionImgs) {
		this.questionImgs = questionImgs;
	}

	public List<String> getQuestionImgsContentType() {
		return questionImgsContentType;
	}

	public void setQuestionImgsContentType(List<String> questionImgsContentType) {
		this.questionImgsContentType = questionImgsContentType;
	}

	public List<String> getQuestionImgsFileName() {
		return questionImgsFileName;
	}

	public void setQuestionImgsFileName(List<String> questionImgsFileName) {
		this.questionImgsFileName = questionImgsFileName;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuestionExpert getQuestionExpert() {
		return questionExpert;
	}

	public void setQuestionExpert(QuestionExpert questionExpert) {
		this.questionExpert = questionExpert;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}
	
}