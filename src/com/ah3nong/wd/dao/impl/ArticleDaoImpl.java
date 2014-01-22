package com.ah3nong.wd.dao.impl;

import java.util.List;

import com.ah3nong.wd.bean.Article;
import com.ah3nong.wd.dao.ArticleDao;
import com.ah3nong.wd.dao.BaseDao;

public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao {

	@Override
	public List<Article> findAll() {
		return getSqlMapClientTemplate().queryForList("article.selectAll");
	}
}
