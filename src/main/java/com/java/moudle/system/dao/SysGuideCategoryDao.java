package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysGuideCategoryRepository;
import com.java.moudle.system.domain.SysGuideCategory;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysGuideCategoryDao extends BaseDao<SysGuideCategoryRepository, SysGuideCategory> {

	
	public List<SysGuideCategory> getGuideCategoryList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from sys_guide_category a ");
		sql.append(" where a.status = '0' ");
		sql.append(" order by a.order_num ");
		return queryList(sql.toString(), null, SysGuideCategory.class);
	}
	
	public void getGuideCategoryPage(SysGuideCategory info, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id, a.name, a.image_url, a.order_num, ");
		sql.append(" (select count(1) from sys_guide_inspect s where s.guide_type = a.id and s.delete_flg = '0') as guideNum ");
		sql.append(" from sys_guide_category a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(info.getName())) {
			sql.append(" and a.name like concat('%', concat(:name, '%')) ");
		}
		sql.append(" order by a.order_num ");
		queryPageList(sql.toString(), info, page, SysGuideCategory.class);
	}
	
	public List<Integer> getCategoryNum() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.order_num ");
		sql.append(" from sys_guide_category a ");
		sql.append(" where a.status = '0' ");
		return queryList(sql.toString(), null, Integer.class);
	}
	
	public SysGuideCategory getCatByOrderNum(String orderNum, String name, String id) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from sys_guide_category a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(orderNum)) {
			sql.append(" and a.order_num = :orderNum ");
			paramMap.put("orderNum", orderNum);
		}
		if(!StringUntil.isNull(name)) {
			sql.append(" and a.name = :name ");
			paramMap.put("name", name);
		}
		if(!StringUntil.isNull(id)) {
			sql.append(" and a.id <> :id ");
			paramMap.put("id", id);
		}
		return queryOne(sql.toString(), paramMap, SysGuideCategory.class);
	}
	
}
