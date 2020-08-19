package com.java.moudle.tripartdock.healthdoctor.service;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.tripartdock.healthdoctor.domain.SubscribeInfoHistory;

public interface SubscribeHistoryService  extends BaseService<SubscribeInfoHistory> {

	SubscribeInfoHistory getSubHisInfo(String subId);
}
