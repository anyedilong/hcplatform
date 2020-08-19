package com.java.moudle.system.dao;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysAboutUsRepository;
import com.java.moudle.system.domain.SysAboutUs;
import com.java.until.dba.BaseDao;


@Named
public class SysAboutUsDao extends BaseDao<SysAboutUsRepository, SysAboutUs> {

	
	public SysAboutUs queryInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_about_us u ");
		return queryOne(sql.toString(), null, SysAboutUs.class);
	}
	
}
