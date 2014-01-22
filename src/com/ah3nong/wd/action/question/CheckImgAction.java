package com.ah3nong.wd.action.question;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.util.JsonUtil;

public class CheckImgAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 2373740238689012088L;
	
	//上传图片
	private File questionImgs;
	
	private String questionImgsContentType;
	
	private String questionImgsFileName;
	
	

	@Transactional(rollbackFor = Exception.class)
	public void checkImg(){
		String method = "checkImg";
		logger.info(method+",the method begin!");
		try {
			String type = questionImgsFileName.substring(questionImgsFileName.lastIndexOf(".")).toLowerCase();	
			response.setCharacterEncoding("UTF-8");
			//response.setContentType("text/html");
			PrintWriter writer;
			writer = response.getWriter();
			JsonUtil json = new JsonUtil();
			String tips=null;
			//Map<String,String> map = new HashMap<String,String>();
			if(questionImgs.length()>1024*1024){
				logger.info("questionImg out of max size!");
				//request.setAttribute("TIPS","文件"+questionImgsFileName+"大小超过1M,请重新上传！");
//				map.put("tips", "文件"+questionImgsFileName+"大小超过1M,请重新上传！");
//				map.put("id", id);
				tips = "文件"+questionImgsFileName+"大小超过1M,请重新上传！";
//				String s = json.map2json(map);
				writer.write(json.string2json(tips));						
			}else if(!type.equals(".jpg")){
				logger.info("questionImg with wrong type!");
				request.setAttribute("TIPS","文件"+questionImgsFileName+"格式错误，请重新上传！");	
				//writer.write("您上传的文件"+questionImgsFileName+"格式错误,请重新上传！");	
				tips = "您上传的文件"+questionImgsFileName+"格式错误,请重新上传！";
				//map.put("tips", "您上传的文件"+questionImgsFileName+"格式错误,请重新上传！");
				//map.put("id", id);
				writer.write(json.string2json(tips));
			}
			writer.flush();
			writer.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("checkeImg failed!+e");
			e.printStackTrace();
		}		

	
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

	
}
