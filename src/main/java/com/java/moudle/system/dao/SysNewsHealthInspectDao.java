package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysNewsHealthInspectRepository;
import com.java.moudle.system.domain.SysNewsHealthInspect;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysNewsHealthInspectDao extends BaseDao<SysNewsHealthInspectRepository, SysNewsHealthInspect> {

	
	public int getCountByCatId(String catId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1)  ");
		sql.append(" from news_health_inspect r ");
		sql.append(" where r.delete_flg = '0' and r.news_cat_id = :catId ");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("catId", catId);
		return queryOne(sql.toString(), paramMap, Integer.class);
	}
	
	public void getNewsHealthPage(SysNewsHealthInspect newsHealth, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id, a.create_time, b.name as catName, a.news_title, a.status, a.news_content ");
		sql.append(" from news_health_inspect a ");
		sql.append(" join news_category b on a.news_cat_id = b.id ");
		sql.append(" where a.delete_flg = '0' and b.status = '0' ");
		if(!StringUntil.isNull(newsHealth.getNewsTitle())) {
			sql.append(" and a.news_title like concat('%', concat(:newsTitle, '%')) ");
		}
		if(!StringUntil.isNull(newsHealth.getStatus())) {
			sql.append(" and a.status = :status ");		
		}
		if(!StringUntil.isNull(newsHealth.getStartTime())) {
			sql.append(" and a.create_time >= to_date(:startTime, 'yyyy-mm-dd') ");
		}
		if(!StringUntil.isNull(newsHealth.getEndTime())) {
			sql.append(" and a.create_time <= to_date(:endTime, 'yyyy-mm-dd') ");
		}
		//权限roleType 1.超级管理员 2.管理员 3.普通用户
		if("1".equals(newsHealth.getRoleType())) {
			sql.append(" and a.status in ('4', '5', '6') ");
		}else if("2".equals(newsHealth.getRoleType())) {
			sql.append(" and a.status in ('3', '4', '5', '6') ");
			sql.append(" and a.create_user in ( ");
			List<String> ids = newsHealth.getUserIds();
			for(int i = 0; i < ids.size(); i++) {
				if(i == 0) {
					sql.append(" '"+ids.get(i)+"' ");
				}else {
					sql.append(" ,'"+ids.get(i)+"' ");
				}
			}
			sql.append(" ) ");
		}else if("3".equals(newsHealth.getRoleType())) {
			String userId = newsHealth.getUserIds().get(0);
			sql.append(" and a.create_user = '"+userId+"' ");
		}
		sql.append(" order by a.status desc, a.pub_time desc ");
		queryPageList(sql.toString(), newsHealth, page, SysNewsHealthInspect.class);
	}
	
	public int getNewsHealthCount(SysNewsHealthInspect newsHealth) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from news_health_inspect u ");
		sql.append(" where u.delete_flg = '0' ");
		if(!StringUntil.isNull(newsHealth.getId())) {
			sql.append(" and u.id <> :id ");
		}
		if(!StringUntil.isNull(newsHealth.getNewsTitle())) {
			sql.append(" and u.news_title = :newsTitle ");
		}
		if(!StringUntil.isNull(newsHealth.getStatus())) {
			sql.append(" and u.status = :status ");
		}
		return queryOne(sql.toString(), newsHealth, Integer.class);
	}
	
}
