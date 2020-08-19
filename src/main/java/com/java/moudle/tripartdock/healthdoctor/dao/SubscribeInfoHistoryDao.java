package com.java.moudle.tripartdock.healthdoctor.dao;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.tripartdock.healthdoctor.dao.repository.SubscribeInfoHistoryRepository;
import com.java.moudle.tripartdock.healthdoctor.domain.SubscribeInfoHistory;
import com.java.until.dba.BaseDao;

@Named
public class SubscribeInfoHistoryDao extends BaseDao<SubscribeInfoHistoryRepository, SubscribeInfoHistory> {

	public SubscribeInfoHistory getSubHisInfo(String subId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from subscribe_info_history a ");
		sql.append(" where a.subscribe_id = :subId ");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subId", subId);
		return queryOne(sql.toString(), paramMap, SubscribeInfoHistory.class);
	}

}
