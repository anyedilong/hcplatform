package com.java.moudle.tripartdock.healthdoctor.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.tripartdock.healthdoctor.dao.SubscribeInfoHistoryDao;
import com.java.moudle.tripartdock.healthdoctor.domain.SubscribeInfoHistory;
import com.java.moudle.tripartdock.healthdoctor.service.SubscribeHistoryService;

@Named
@Transactional(readOnly = false)
public class SubscribeHistoryServiceImpl  extends BaseServiceImpl<SubscribeInfoHistoryDao, SubscribeInfoHistory> implements SubscribeHistoryService{

	@Override
	public SubscribeInfoHistory getSubHisInfo(String subId) {
		return dao.getSubHisInfo(subId);
	}

	
}
