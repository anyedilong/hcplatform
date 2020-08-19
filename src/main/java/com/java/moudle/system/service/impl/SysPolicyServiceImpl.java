package com.java.moudle.system.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysPolicyDao;
import com.java.moudle.system.domain.SysPolicy;
import com.java.moudle.system.service.SysPolicyService;
import com.java.until.dba.PageModel;


@Named
@Transactional(readOnly=false)
public class SysPolicyServiceImpl extends BaseServiceImpl<SysPolicyDao, SysPolicy> implements SysPolicyService {

	@Override
	public void getPolicyPage(SysPolicy policy, PageModel page) {
		 dao.getPolicyPage(policy, page);
	}

	@Override
	public List<SysPolicy> getPolicyList(String searchContent) {
		List<SysPolicy> list = dao.getPolicyList(searchContent);
		if(list != null && list.size() > 0) {
			for(SysPolicy info : list) {
				info.setPolicyContent(info.getPolicyContent());
			}
		}
		return list;
	}
	
}
