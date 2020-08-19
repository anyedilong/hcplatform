package com.java.moudle.tripartdock.healthdoctor.service;

import com.java.moudle.common.message.JsonResult;

public interface SyncCustomerService   {

	JsonResult syncCustomerInfo(String oldUsername, String newUsername, String pwd);
}
