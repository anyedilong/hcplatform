package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysPolicy;
import com.java.until.dba.PageModel;

public interface SysPolicyService extends BaseService<SysPolicy> {
   
	void getPolicyPage(SysPolicy policy, PageModel page);

	List<SysPolicy> getPolicyList(String searchContent);
}
