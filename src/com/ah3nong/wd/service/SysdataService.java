package com.ah3nong.wd.service;

import java.util.List;

import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.common.Pager;

public interface SysdataService {
	// 查全部
	public List<Sysdata> findAll();
	
	//查1个
	public Sysdata findByPrimarykey(int id);
	
	// 增加
	public void add(Sysdata sysdata);

	// 修改
	public void updateByPrimarykey(Sysdata sysdata);
	
	//按页数查找
	public Pager<Sysdata> findSystemsByPageNum(int pageNum, int numPerPage);
	
	//按名字查
	public Sysdata findByName(String name) ;
}
