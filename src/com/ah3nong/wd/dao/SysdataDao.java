package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Sysdata;

public interface SysdataDao {
	//查全部
	public List<Sysdata> findAll();
	
	//增加
	public void add(Sysdata sysdata);
	
	//修改
	public void updateByPrimarykey(Sysdata sysdata);
	
	//按页查找
	public List<Sysdata> findByPager(Map<String,Object> map);
	
	//查出总数
	public int countAll();
	
	//查1个
	public Sysdata findByPrimarykey(int id);
}
