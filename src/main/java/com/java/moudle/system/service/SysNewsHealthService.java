package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.until.dba.PageModel;

public interface SysNewsHealthService extends BaseService<SysNewsHealth> {
   
	List<SysNewsHealth> getNewsHealthList(String searchContent);
	//查询健康资讯列表（分页）
	void getNewsHealthPage(SysNewsHealth health, PageModel page);
}
