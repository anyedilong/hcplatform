package com.java.moudle.system.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysNewsHealthDao;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.moudle.system.service.SysNewsHealthService;
import com.java.until.dba.PageModel;


@Named
@Transactional(readOnly=false)
public class SysNewsHealthServiceImpl extends BaseServiceImpl<SysNewsHealthDao, SysNewsHealth> implements SysNewsHealthService {
	
	@Override
	public List<SysNewsHealth> getNewsHealthList(String searchContent) {
		List<SysNewsHealth> list = dao.getNewsHealthList(searchContent);
		if(list != null && list.size() > 0) {
			for(SysNewsHealth info : list) {
				info.setNewsContent(info.getNewsContent());
			}
		}
		return list;
	}

	@Override
	public void getNewsHealthPage(SysNewsHealth health, PageModel page) {
		dao.getNewsHealthPage(health, page);
	}
}
