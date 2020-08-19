package com.java.moudle.system.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysApplicationListRepository;
import com.java.moudle.system.domain.SysApplicationList;
import com.java.until.dba.BaseDao;

@Named
public class SysApplicationListDao extends BaseDao<SysApplicationListRepository, SysApplicationList>{

	public List<SysApplicationList> getApplicationListByName(String searchContent) {
		return repository.getApplicationListByName(searchContent);
	}
	
}