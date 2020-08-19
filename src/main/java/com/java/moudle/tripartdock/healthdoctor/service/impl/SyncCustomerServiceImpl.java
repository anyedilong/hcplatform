package com.java.moudle.tripartdock.healthdoctor.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.healthdoctor.service.SyncCustomerService;

@Named
@Transactional(readOnly = false)
public class SyncCustomerServiceImpl implements SyncCustomerService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	private RestTemplate restTemplate;
	
	@Override
	public JsonResult syncCustomerInfo(String oldUsername, String newUsername, String pwd) {
		String url = PropertiesUtil.getRegister("syncHcCustomer");
		url = url + "?oldUsername="+oldUsername + "&newUsername=" + newUsername + "&pwd=" + pwd;
		logger.info("同步账号至预约挂号请求：" + url);
		ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
		String body = postForEntity.getBody();
		logger.info("同步账号至预约挂号返回：" + body);
		JsonResult result = JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
		return result;
	}
	
	/**
	 * @Description: 去掉jsonp格式多余的字符
	 * @param @param jsonpDataStr
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String coverJsonpData(String jsonpDataStr) {
		String str = jsonpDataStr.substring(5, jsonpDataStr.length()-1);
		return str;
	}
}
