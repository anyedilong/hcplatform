package com.java.moudle.system.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysAppVersionDao;
import com.java.moudle.system.domain.SysAppVersion;
import com.java.moudle.system.service.SysAppVersionService;


@Named
@Transactional(readOnly=false)
public class SysAppVersionServiceImpl extends BaseServiceImpl<SysAppVersionDao, SysAppVersion> implements SysAppVersionService {

	@Override
	public SysAppVersion queryNewlyInfo() throws Exception {
		return dao.queryNewlyInfo();
	}

	
}
