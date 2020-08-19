package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysNewsCategoryRepository;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysNewsCategoryDao extends BaseDao<SysNewsCategoryRepository, SysNewsCategory> {

	
	public List<SysNewsCategory> getNewsCategoryList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from news_category a ");
		sql.append(" where a.status = '0' ");
		sql.append(" order by a.order_num ");
		return queryList(sql.toString(), null, SysNewsCategory.class);
	}
	
	public void getNewsCategoryPage(SysNewsCategory newsCat, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id, a.name, a.order_num, ");
		sql.append(" (select count(1) from news_health_inspect s where s.news_cat_id = a.id) as newsHealthNum ");
		sql.append(" from news_category a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(newsCat.getName())) {
			sql.append(" and a.name like concat('%', concat(:name, '%')) ");
		}
		sql.append(" order by a.order_num ");
		queryPageList(sql.toString(), newsCat, page, SysNewsCategory.class);
	}
	
	public int getNewCatByCon(String id, String name, String orderNum) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from news_category a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(id)) {
			sql.append(" and a.id != :id ");
			paramMap.put("id", id);
		}
		if(!StringUntil.isNull(name)) {
			sql.append(" and a.name = :name ");
			paramMap.put("name", name);
		}
		if(!StringUntil.isNull(orderNum)) {
			sql.append(" and a.order_num = :orderNum ");
			paramMap.put("orderNum", orderNum);
		}
		return queryOne(sql.toString(), paramMap, Integer.class);
	}
	
	public SysNewsCategory getNewCatInfoByCon(String name, String orderNum) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from news_category a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(name)) {
			sql.append(" and a.name = :name ");
			paramMap.put("name", name);
		}
		if(!StringUntil.isNull(orderNum)) {
			sql.append(" and a.order_num = :orderNum ");
			paramMap.put("orderNum", orderNum);
		}
		return queryOne(sql.toString(), paramMap, SysNewsCategory.class);
	}
}
