package com.java.moudle.system.dao;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysPlatformRepository;
import com.java.moudle.system.domain.SysPlatform;
import com.java.moudle.system.dto.SaveWzszDto;
import com.java.until.dba.BaseDao;


@Named
public class SysPlatformDao extends BaseDao<SysPlatformRepository, SysPlatform> {

	
	public SysPlatform getPlatDetail() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from sys_platform ");
		return queryOne(sql.toString(), null, SysPlatform.class);
	}

	public void siteSettings(SysPlatform sysPlatform) {
		repository.siteSettings(sysPlatform);
	}

	public void siteSettings2(SysPlatform sysPlatform) {
		repository.siteSettings2(sysPlatform);
	}
}
