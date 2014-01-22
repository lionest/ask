package com.ah3nong.wd.action.question;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class EditQuestionImgAction extends GenericActionSupport<QuestionImg> {

	private static final long serialVersionUID = 2373740238689012088L;
	private QuestionService questionService;
	private QuestionImg questionImg;
	//上传图片
	private File questionImgs;	
	private String questionImgsContentType;	
	private String questionImgsFileName;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "change img action!";
		
		try {
			//questionService.findExpertByQuestionId(questionId)
			//先删除
			//questionService.delQuestionImgByPrimaryKey(questionImg.getId());
			//添加
			String URL = null;
			SimpleDateFormat sdFormat = new SimpleDateFormat(
					"yyyyMMddhhmmssSSS");
			String DIR = Config.getString("Img_dir");
			String type = questionImgsFileName.substring(
					questionImgsFileName.lastIndexOf(".")).toLowerCase();
			int order = questionImg.getImgOrder();
			InputStream is = new FileInputStream(questionImgs);
	
			String time = sdFormat.format(System.currentTimeMillis());
			URL = DIR + time +"_"+order+ type;
			OutputStream os = new FileOutputStream(URL);
			questionImg.setUrl("uploadIMG/"+time+"_"+order+type);
			byte buffer[] = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
			}
			
			os.close();
			is.close();
			
			questionService.updateQuestionImgs(questionImg, true);
			questionImg=questionService.findQuestionImgByPrimaryKey(questionImg.getId());
			request.setAttribute("questionId", questionImg.getQuestionId());
			request.setAttribute("tips", "修改图片成功！");
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public QuestionImg getQuestionImg() {
		return questionImg;
	}
	public void setQuestionImg(QuestionImg questionImg) {
		this.questionImg = questionImg;
	}

	
	public File getQuestionImgs() {
		return questionImgs;
	}


	public void setQuestionImgs(File questionImgs) {
		this.questionImgs = questionImgs;
	}


	public String getQuestionImgsContentType() {
		return questionImgsContentType;
	}


	public void setQuestionImgsContentType(String questionImgsContentType) {
		this.questionImgsContentType = questionImgsContentType;
	}


	public String getQuestionImgsFileName() {
		return questionImgsFileName;
	}


	public void setQuestionImgsFileName(String questionImgsFileName) {
		this.questionImgsFileName = questionImgsFileName;
	}


	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	
}
