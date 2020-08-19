package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysGuideInspectRepository;
import com.java.moudle.system.domain.SysGuideInspect;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;

@Named
public class SysGuideInspectDao extends BaseDao<SysGuideInspectRepository, SysGuideInspect>{

	public void getGuideInpectPage(SysGuideInspect guide, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*, c.name guideTypeName, c.image_url ");
		sql.append(" from sys_guide_inspect a left join sys_guide_category c on a.guide_type = c.id ");
		sql.append(" where a.delete_flg = '0' ");
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
		
		//权限roleType 1.超级管理员 2.管理员 3.普通用户
		if("1".equals(guide.getRoleType())) {
			sql.append(" and a.status in ('2', '3') ");
		}else if("2".equals(guide.getRoleType())) {
			sql.append(" and a.status in ('1', '2', '3') ");
			sql.append(" and a.create_user in ( ");
			List<String> ids = guide.getUserIds();
			for(int i = 0; i < ids.size(); i++) {
				if(i == 0) {
					sql.append(" '"+ids.get(i)+"' ");
				}else {
					sql.append(" ,'"+ids.get(i)+"' ");
				}
			}
			sql.append(" ) ");
		}else if("3".equals(guide.getRoleType())) {
			String userId = guide.getUserIds().get(0);
			sql.append(" and a.create_user = '"+userId+"' ");
		}
		queryPageList(sql.toString(), guide, page, SysGuideInspect.class);
	}

	public SysGuideInspect getGuideInfo(String id) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<>();
		sql.append(" select a.* ");
		sql.append(" from sys_guide_inspect a ");
		sql.append(" where a.delete_flg = '0' ");
		sql.append(" and a.id = :id ");
		paramMap.put("id", id);
		return queryOne(sql.toString(), paramMap, SysGuideInspect.class);
	}
	
	public int getCountByCatId(String catId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from sys_guide_inspect a ");
		sql.append(" where a.delete_flg = '0' ");
		sql.append(" and a.guide_type = :catId ");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("catId", catId);
		return queryOne(sql.toString(), paramMap, Integer.class);
	}

	public SysGuideInspect getCuideInfoByTitle(String title, String id) {
		System.out.println(title + " - " + id);
		StringBuffer sql = new StringBuffer();
		sql.append(" select * ");
		sql.append(" from sys_guide_inspect a ");
		sql.append(" where a.delete_flg = '0' ");
		sql.append(" and a.title = :title ");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", title);
		if (!StringUntil.isNull(id)) {
			paramMap.put("id", id);
			sql.append(" and a.id <> :id");
		}
		return queryOne(sql.toString(), paramMap, SysGuideInspect.class);
	}
}
