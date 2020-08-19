package com.java.moudle.system.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.java.moudle.system.dao.SysApplicationListDao;
import com.java.moudle.system.domain.SysApplicationList;
import com.java.moudle.system.service.SysApplicationListService;

@Named
public class SysApplicationListServiceImpl implements SysApplicationListService{

	@Inject
	private SysApplicationListDao applicationListDao;
	
	@Override
	public List<SysApplicationList> getApplicationListByName(String searchContent) {
		return applicationListDao.getApplicationListByName(searchContent);
	}
	
}