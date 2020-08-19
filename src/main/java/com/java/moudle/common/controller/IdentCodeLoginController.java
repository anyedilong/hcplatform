package com.java.moudle.common.controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.java.moudle.tripartdock.healthdoctor.service.SyncCustomerService;
import com.java.until.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.service.SysCustormerService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.ras.BCrypt;

/**
 * <br>
 * <b>功能：</b>CustomerController<br>
 * <b>作者：</b>blt<br>
 * <b>版权所有：<b>版权所有(C) 2016，blt<br>
 */
@RestController
@RequestMapping("identCodelogin")
public class IdentCodeLoginController extends BaseController {

	@Inject
	private RestTemplate restTemplate;
	@Inject
	private SysCustormerService custormerService;
	@Inject
	private SyncCustomerService syncCustomerService;
	/**
	 * @Description: 验证码登录（pc网站端）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("index")
	public JsonResult index() {
		try {
			String param = getParam(request);
			JSONObject json = JSON.parseObject(param);
			String phone = null , code = null;
			if (json.containsKey("phone")) {
				phone = json.get("phone").toString();
			}
			if (json.containsKey("code")) {
				code = json.get("code").toString();
			}

	        if(StringUntil.isNull(phone)) {
	        	return jsonResult(null, 10000, "手机号不能为空！");
	        }
	        if(StringUntil.isNull(code)) {
	        	return jsonResult(null, 10000, "验证码不能为空！");
	        }
	        SysCustormer custormer = custormerService.getCUstormerInfoByPhone(phone);
        	if(custormer == null) {
        		return jsonResult(null, 10000, "该手机号未注册，请先注册");
        	}else if("1".equals(custormer.getStatus())) {
        		return jsonResult(null, 10000, "您的账户已被禁用，请联系管理员");
        	}
        	String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, phone, String.class);
	        if(code.equals(cacheCode)) {
	        	String url = "http://oa/oa/oauth/token?grant_type=password&username="
	        				+custormer.getUsername()+"&password="+custormer.getPwd()+"&client_id=benefit&client_secret=123456";
				ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
				String body = postForEntity.getBody();
				String token = JSONObject.parseObject(body).getString("access_token");
				CacheUntil.delete(RedisCacheEmun.USER_CACHE, phone);
				
				Map<String, Object> map = new HashMap<>();
				map.put("token", token);
				map.put("sfzh", custormer.getSfzh());
				map.put("name", custormer.getName());
				map.put("custormerUrl", custormer.getCustormerUrl());
				return jsonResult(map);
			}else {
				return jsonResult(null, 10000, "验证码不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, e.getMessage());
		}
	}
	
	/**
	 * @Description: 验证码登录（公众号）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("indexApp")
	public JsonResult indexApp() {
		try {
			String param = getParam(request);
			JSONObject json = JSON.parseObject(param);
			String phone = null , code = null, openId = null;
			if (json.containsKey("phone")) {
				phone = json.get("phone").toString();
			}
			if (json.containsKey("code")) {
				code = json.get("code").toString();
			}
			if (json.containsKey("openId")) {
				openId = json.get("openId").toString();
			}

	        if(StringUntil.isNull(phone)) {
	        	return jsonResult(null, 10000, "手机号不能为空！");
	        }
	        if(StringUntil.isNull(code)) {
	        	return jsonResult(null, 10000, "验证码不能为空！");
	        }
	        SysCustormer custormer = custormerService.getCUstormerInfoByPhone(phone);
        	if(custormer == null) {
        		//应需求要求，未注册的时候自动创建临时用户
        		custormer = new SysCustormer();
        		custormer.setId(UUIDUtil.getUUID());
        		custormer.setSfzh("110110110110");//临时
        		custormer.setPhone(phone);
        		custormer.setUsername(phone);
				custormer.setOpenId(openId);
        		custormer.setStatus("0");
        		custormer.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));//临时
        		custormer.setPwd("123456");//临时
        		custormer.setIsRealName(0);
        		custormerService.save(custormer);
				//根据业务需求；暂时向预约挂号的用户同步 TODO
				syncCustomerService.syncCustomerInfo(custormer.getPhone(), custormer.getPhone(), custormer.getPwd());
        	}else if("1".equals(custormer.getStatus())) {
        		return jsonResult(null, 10000, "账户已被禁用，请联系运营商");
        	}
        	String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, phone, String.class);
	        if(code.equals(cacheCode)) {
	        	String url = "http://oa/oa/oauth/token?grant_type=password&username="
	        				+custormer.getUsername()+"&password="+custormer.getPwd()+"&client_id=benefit&client_secret=123456";
				ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
				String body = postForEntity.getBody();
				String token = JSONObject.parseObject(body).getString("access_token");
				CacheUntil.delete(RedisCacheEmun.USER_CACHE, phone);
				
				Map<String, Object> map = new HashMap<>();
				map.put("token", token);
				map.put("sfzh", custormer.getSfzh());
				map.put("name", custormer.getName());
				map.put("custormerUrl", custormer.getCustormerUrl());
				if (StringUtils.isNotBlank(openId)) {
					custormer.setOpenId(openId);
					custormerService.save(custormer);
				}
				return jsonResult(map);
			}else {
				return jsonResult(null, 10000, "验证码不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, e.getMessage());
		}
	}
	
}
