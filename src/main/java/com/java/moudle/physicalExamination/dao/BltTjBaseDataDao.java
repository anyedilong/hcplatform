package com.java.moudle.physicalExamination.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.physicalExamination.dao.repository.BltTjBaseDataRepository;
import com.java.moudle.physicalExamination.domain.BltTjBaseData;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;

@Named
public class BltTjBaseDataDao extends BaseDao<BltTjBaseDataRepository, BltTjBaseData>{

	public List<BltTjBaseData> queryGjFirstData() {
		return repository.queryGjFirstData();
	}

	public List<BltTjBaseData> getBaseDataInfoByParentId(String parentId) {
		return repository.getBaseDataInfoByParentId(parentId);
	}

	public List<BltTjBaseData> getSelectedTjData(String rqlx) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.name, a.attribute ");
		sql.append(" from blt_tj_base_data a ");
		sql.append(" join blt_tj_data b on a.id = b.basedata_id ");
		sql.append(" join blt_tj_info c on c.id = b.tj_info_id ");
		sql.append(" where (1 = 2 or ");
		if(!StringUntil.isNull(rqlx)) {
			sql.append(" c.rqlx_code in (");
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
		return queryList(sql.toString(), null, BltTjBaseData.class);
	}
	
}
