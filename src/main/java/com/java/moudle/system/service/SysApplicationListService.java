package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.system.domain.SysApplicationList;

public interface SysApplicationListService {

	List<SysApplicationList> getApplicationListByName(String searchContent);
	
}