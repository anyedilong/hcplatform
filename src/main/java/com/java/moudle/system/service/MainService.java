package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.SysCustomerDto;

public interface MainService {
	JsonResult login(SysCustomerDto dto);
	
	JsonResult getCode(SysCustomerDto dto);
	
	JsonResult yzmlogin(SysCustomerDto dto);
	
	JsonResult singleLogin(String clientIp);

	SysCustomerDto getResidentInfo(SysCustomerDto customerDto);
	
}
