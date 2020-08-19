package com.java.moudle.system.dao;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysCallUsRepository;
import com.java.moudle.system.domain.SysCallUs;
import com.java.until.dba.BaseDao;


@Named
public class SysCallUsDao extends BaseDao<SysCallUsRepository, SysCallUs> {

	
	public SysCallUs queryInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_call_us u ");
		return queryOne(sql.toString(), null, SysCallUs.class);
	}
	
}
