package com.java.moudle.system.service;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysCallUs;

public interface SysCallUsService extends BaseService<SysCallUs> {
   
	
	SysCallUs queryInfo();
	
}
