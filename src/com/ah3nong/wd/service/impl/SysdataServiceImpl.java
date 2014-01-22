package com.ah3nong.wd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.SysdataDao;
import com.ah3nong.wd.service.SysdataService;

public class SysdataServiceImpl implements SysdataService {
	private SysdataDao sysdataDao;

	@Override
	public List<Sysdata> findAll() {
		return sysdataDao.findAll();
	}
	
	@Override
	public Sysdata findByName(String name) {
		List<Sysdata> sysdatas = sysdataDao.findAll();
		if(sysdatas != null && sysdatas.size()>0){
			for(int i=0 ; i<sysdatas.size() ; i++ ){
				if(name.equals(sysdatas.get(i).getName())){
					return sysdatas.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public void add(Sysdata sysdata) {
		sysdataDao.add(sysdata);
	}

	@Override
	public void updateByPrimarykey(Sysdata sysdata) {
		sysdataDao.updateByPrimarykey(sysdata);
	}

	@Override
	public Pager<Sysdata> findSystemsByPageNum(int pageNum, int numPerPage) {
		Pager<Sysdata> pager = new Pager<Sysdata>(pageNum,numPerPage);
		int start = (pageNum-1)*numPerPage;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("size", numPerPage);
		pager.setPageRecords(sysdataDao.findByPager(map));
		pager.setTotalRecords(sysdataDao.countAll());
		return pager;
	}

	public void setSysdataDao(SysdataDao sysdataDao) {
		this.sysdataDao = sysdataDao;
	}

	@Override
	public Sysdata findByPrimarykey(int id) {
		return sysdataDao.findByPrimarykey(id);
	}

}