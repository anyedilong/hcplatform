package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysGuideRepository;
import com.java.moudle.system.domain.SysGuide;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;

@Named
public class SysGuideDao extends BaseDao<SysGuideRepository, SysGuide>{

	public List<SysGuide> getGuideList(String searchContent) {
		return repository.getGuideList(searchContent);
	}

	public void getGuidePage(SysGuide guide, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from sys_guide a ");
		sql.append(" where 1 = 1 ");
		if(!StringUntil.isNull(guide.getGuideType())) {
			sql.append(" and a.guide_type = :guideType ");
		}
		if (!StringUntil.isNull(guide.getTitle())) {
			sql.append(" and a.title like '%' || :title || '%' ");
		}
		if (!StringUntil.isNull(guide.getStartTime())) {
			sql.append(" and a.CREATE_TIME >= to_date(:startTime, 'yyyy-mm-dd') ");
		}
		if (!StringUntil.isNull(guide.getEndTime())) {
			sql.append(" and a.CREATE_TIME <= to_date(:endTime, 'yyyy-mm-dd') ");
		}
		queryPageList(sql.toString(), guide, page, SysGuide.class);
	}
	
	public SysGuide getGuideInfo(String id) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<>();
		sql.append(" select a.* ");
		sql.append(" from sys_guide a ");
		sql.append(" where 1 = 1  ");
		sql.append(" and a.id = :id ");
		paramMap.put("id", id);
		return queryOne(sql.toString(), paramMap, SysGuide.class);
	}
}
