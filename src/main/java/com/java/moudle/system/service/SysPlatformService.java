package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysPlatform;
import com.java.moudle.system.dto.SaveWzszDto;

public interface SysPlatformService extends BaseService<SysPlatform> {
   
	SysPlatform getPlatDetail();

    JsonResult siteSettings(SaveWzszDto dto, int isId);
}
