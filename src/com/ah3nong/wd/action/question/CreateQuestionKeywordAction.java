package com.ah3nong.wd.action.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.QuestionKeyword;

public class CreateQuestionKeywordAction extends GenericActionSupport<QuestionKeyword> {
	private static final long serialVersionUID = -31441911098386889L;
	
	private String questionSubject;
	private String questionKeywords;
	public String execute() throws IOException{
		System.out.println(questionSubject);
		// 创建分词对象 true为智能切分，false为最细粒切分
		Analyzer anal = new IKAnalyzer(true);
		StringReader reader = new StringReader(questionSubject);
		// 分词
		TokenStream ts = anal.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		// 遍历分词数据
		StringBuilder sb = new StringBuilder();
		while (ts.incrementToken()) {
			String tmp = term.toString();
			sb.append(tmp);
			sb.append(",");
		}
		String i = sb.toString();
		if(i.length()>0){
			questionKeywords = i.substring(0,i.length()-1);
		}
		reader.close();
		return SUCCESS;
	}

	public void setQuestionSubject(String questionSubject) {
		this.questionSubject = questionSubject;
	}

	public String getQuestionKeywords() {
		return questionKeywords;
	}
	
}
