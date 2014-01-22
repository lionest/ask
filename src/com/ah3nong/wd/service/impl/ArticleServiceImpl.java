package com.ah3nong.wd.service.impl;

import java.util.List;

import com.ah3nong.wd.bean.Article;
import com.ah3nong.wd.dao.ArticleDao;
import com.ah3nong.wd.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDao articleDao;
	@Override
	public List<Article> findAll() {
		return articleDao.findAll();
	}
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
}
