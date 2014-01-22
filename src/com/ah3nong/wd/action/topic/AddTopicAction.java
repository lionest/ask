package com.ah3nong.wd.action.topic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.TopicService;

public class AddTopicAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = 2660704173695232753L;
	private TopicService topicService;

	private String title;
	private File topicImg;
	private String topicImgFileName;
	private String[] questionIds;

	public String execute() {
		Topic topic = new Topic();
		//标题
		topic.setTitle(title);
		// 图片
		try {
			String fileName = topicImgFileName;
			String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			InputStream is;
			is = new FileInputStream(topicImg);
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String time = sdFormat.format(System.currentTimeMillis());
			String DIR = Config.getString("topic_img_dir");
			String url = DIR + time + type;
			OutputStream os = new FileOutputStream(url);
			byte buffer[] = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
			os.close();
			is.close();
			topic.setImgUrl("uploadIMG/"+time+type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//时间
		topic.setCreatedTime(new Date());
		//状态
		topic.setStatus(Config.getInt("TOPIC_NORMAL"));
		topicService.addTopic(topic);
		return SUCCESS;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTopicImg(File topicImg) {
		this.topicImg = topicImg;
	}

	public void setQuestionIds(String[] questionIds) {
		this.questionIds = questionIds;
	}

	public void setTopicImgFileName(String topicImgFileName) {
		this.topicImgFileName = topicImgFileName;
	}
	
}
