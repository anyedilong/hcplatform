package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysNewsRepository;
import com.java.moudle.system.domain.SysNews;
import com.java.moudle.system.dto.SysNewsDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysNewsDao extends BaseDao<SysNewsRepository, SysNews> {

	
	public void getNewsPage(SysNewsDto news, PageModel page) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.id, u.title, u.synopsis, u.content, u.create_time, u.status, ");
		sql.append(" u.pub_time, a.username as createUser, a.name, a.org_name ");
		sql.append(" from sys_news u ");
		sql.append(" join sys_user a on u.create_user = a.id ");
		sql.append(" where u.delete_flg = '0' ");
		if(!StringUntil.isNull(news.getStatus())) {
			sql.append(" and u.status = :status ");
		}
		if(!StringUntil.isNull(news.getTitle())) {
			sql.append(" and u.title like concat('%', concat(:title, '%')) ");
		}
		if(!StringUntil.isNull(news.getStartDate())) {
			sql.append(" and u.create_time >= to_date(:startDate, 'yyyy-mm-dd') ");
		}
		if(!StringUntil.isNull(news.getEndDate())) {
			sql.append(" and u.create_time <= to_date(:endDate, 'yyyy-mm-dd') ");
		}
		if(!StringUntil.isNull(news.getType()) && "1".equals(news.getType())) {
			sql.append(" and u.status in ('3', '4') ");
		}
		//权限roleType 1.超级管理员 2.管理员 3.普通用户
		if("1".equals(news.getRoleType())) {
			sql.append(" and u.status in ('3', '4') ");
		}else if("2".equals(news.getRoleType())) {
			sql.append(" and u.status in ('3', '4') ");
			sql.append(" and u.create_user in ( ");
			List<String> ids = news.getUserIds();
			for(int i = 0; i < ids.size(); i++) {
				if(i == 0) {
					sql.append(" '"+ids.get(i)+"' ");
				}else {
					sql.append(" ,'"+ids.get(i)+"' ");
				}
			}
			sql.append(" ) ");
		}else if("3".equals(news.getRoleType())) {
			String userId = news.getUserIds().get(0);
			sql.append(" and u.create_user = '"+userId+"' ");
		}
		
		sql.append(" order by u.status desc, u.pub_time desc, u.create_time desc ");
		queryPageList(sql.toString(), news, page, SysNewsDto.class);
	}
	
	public List<SysNews> getRollNewsList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_news u ");
		sql.append(" where u.status = '3' ");
		sql.append(" order by u.status desc, u.pub_time desc ");
		return queryList(sql.toString(), null, SysNews.class);
	}
	
	public SysNewsDto getNewsDetail(String id) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.id, u.title, u.synopsis, u.content, u.create_time, u.status, ");
		sql.append(" u.pub_time, a.username as createUser, a.org_name ");
		sql.append(" from sys_news u ");
		sql.append(" join sys_user a on u.create_user = a.id ");
		sql.append(" where u.delete_flg = '0' and u.id = :id ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return queryOne(sql.toString(), paramMap, SysNewsDto.class);
	}
	
	public int getNewsCount(SysNews news) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) ");
		sql.append(" from sys_news u ");
		sql.append(" where u.delete_flg = '0' ");
		if(!StringUntil.isNull(news.getId())) {
			sql.append(" and u.id <> :id ");
		}
		if(!StringUntil.isNull(news.getTitle())) {
			sql.append(" and u.title = :title ");
		}
		if(!StringUntil.isNull(news.getStatus())) {
			sql.append(" and u.status = :status ");
		}
		return queryOne(sql.toString(), news, Integer.class);
	}
	
	public void getFrontNewsPage(SysNews news, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.id, u.title, u.synopsis, u.pub_time ");
		sql.append(" from sys_news u ");
		sql.append(" where u.delete_flg = '0' ");
		if(!StringUntil.isNull(news.getStatus())) {
			String[] statusArr = news.getStatus().split(",");
			sql.append(" and u.status in ( ");
	        for(int i = 0; i< statusArr.length; i++) {
	        	if(i > 0) {
	        		sql.append(" , ");
	        	}
	        	sql.append("'"+statusArr[i]+"'");
	        }
	        sql.append(" ) ");
		}else {
			sql.append(" and 1 = 2 ");
		}
		sql.append(" order by u.status desc, u.pub_time desc ");
		queryPageList(sql.toString(), news, page, SysNews.class);
	}
}
