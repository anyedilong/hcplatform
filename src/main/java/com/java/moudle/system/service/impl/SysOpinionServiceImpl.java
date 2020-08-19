package com.java.moudle.system.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysOpinionDao;
import com.java.moudle.system.domain.SysOpinion;
import com.java.moudle.system.service.SysOpinionService;


@Named
@Transactional(readOnly=false)
public class SysOpinionServiceImpl extends BaseServiceImpl<SysOpinionDao, SysOpinion> implements SysOpinionService {

	

	
	
}
