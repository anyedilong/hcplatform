package com.java.moudle.physicalExamination.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.physicalExamination.dao.repository.BltTjInfoRepository;
import com.java.moudle.physicalExamination.domain.BltTjInfo;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;

@Named
public class BltTjInfoDao extends BaseDao<BltTjInfoRepository, BltTjInfo>{

	public BltTjInfo queryGjInfo(String org, String rqlx) {
		return repository.queryGjInfo(org, rqlx);
	}

	public List<String> getTipsByRqlx(String rqlx) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select to_char(a.guide_info) ");
		sql.append(" from blt_tj_info a ");
		sql.append(" where a.guide_info is not null and (1 = 2 or ");
		if(!StringUntil.isNull(rqlx)) {
			sql.append(" a.rqlx_code in (");
			String[] rqlxArr = rqlx.split(",");
			for(int i = 0; i < rqlxArr.length; i++) {
				if(i == 0) {
					sql.append(" '"+rqlxArr[i]+"' ");
				}else {
					sql.append(" ,'"+rqlxArr[i]+"' ");
				}
			}
			sql.append(" ) ");
		}
		sql.append(" ) ");
		return queryList(sql.toString(), null, String.class);
	}
	
}
