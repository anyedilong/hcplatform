package com.java.moudle.system.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysNewsHealthRepository;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysNewsHealthDao extends BaseDao<SysNewsHealthRepository, SysNewsHealth> {

	public List<SysNewsHealth> getNewsHealthList(String searchContent) {
		return repository.getNewsHealthList(searchContent);
	}
	
	public void getNewsHealthPage(SysNewsHealth health, PageModel page) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from news_health a ");
		sql.append(" where 1 = 1 ");
		if(!StringUntil.isNull(health.getStatus())) {
			sql.append(" and a.status = :status ");
		}
		if(!StringUntil.isNull(health.getNewsCatId())) {
			sql.append(" and a.news_cat_id = :newsCatId ");
		}
		queryPageList(sql.toString(), health, page, SysNewsHealth.class);
	}
	
	public int updateStatusById(String id, String status) {
		return repository.updateStatusById(id, status);
	}
	
	public int delNewsHealth(String id) {
		return repository.delNewsHealth(id);
	}
	
}
