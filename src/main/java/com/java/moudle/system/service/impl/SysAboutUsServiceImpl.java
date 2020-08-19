package com.java.moudle.system.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysAboutUsDao;
import com.java.moudle.system.domain.SysAboutUs;
import com.java.moudle.system.service.SysAboutUsService;


@Named
@Transactional(readOnly=false)
public class SysAboutUsServiceImpl extends BaseServiceImpl<SysAboutUsDao, SysAboutUs> implements SysAboutUsService {

	@Override
	public SysAboutUs queryInfo() {
		return dao.queryInfo();
	}

	
	
}
