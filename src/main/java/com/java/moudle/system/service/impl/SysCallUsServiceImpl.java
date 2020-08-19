package com.java.moudle.system.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysCallUsDao;
import com.java.moudle.system.domain.SysCallUs;
import com.java.moudle.system.service.SysCallUsService;


@Named
@Transactional(readOnly=false)
public class SysCallUsServiceImpl extends BaseServiceImpl<SysCallUsDao, SysCallUs> implements SysCallUsService {

	@Override
	public SysCallUs queryInfo() {
		return dao.queryInfo();
	}
	
}
