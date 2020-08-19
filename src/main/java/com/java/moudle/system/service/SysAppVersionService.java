package com.java.moudle.system.service;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysAppVersion;

public interface SysAppVersionService extends BaseService<SysAppVersion> {
   
	SysAppVersion queryNewlyInfo() throws Exception;
	
}
