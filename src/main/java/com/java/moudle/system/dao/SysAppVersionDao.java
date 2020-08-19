package com.java.moudle.system.dao;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysAppVersionRepository;
import com.java.moudle.system.domain.SysAppVersion;
import com.java.until.dba.BaseDao;


@Named
public class SysAppVersionDao extends BaseDao<SysAppVersionRepository, SysAppVersion> {

	
	public SysAppVersion queryNewlyInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.* from sys_app_version r order by r.version desc ");
		return queryOne(sql.toString(), null, SysAppVersion.class);
	}
	
}
