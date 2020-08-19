package com.java.until.resthttp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.until.StringUntil;


public class RestTemplateUtils {
	
	/**
	 * 	调用公卫服务，解析返回数据
	 * @param
	 * @return
	 */
	public static JsonResult sendPost(RestTemplate restTemplate, String url, Object param) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		HttpEntity formEntity = new HttpEntity(JSONObject.parse(JSON.toJSONString(param)), headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, formEntity, String.class);
		String body = postForEntity.getBody();
		JSONObject result = JSONObject.parseObject(body);
		String data = result.getString("data");
		if(!StringUntil.isNull(data)) { 
			if ("{".equals(data.substring(0, 1))) {
				JSONObject resultData = JSONObject.parseObject(data);
				return new JsonResult(resultData, result.getInteger("retCode"), result.getString("retMsg"));
			} else if ("[".equals(data.substring(0, 1))) {
				JSONArray resultData = JSON.parseArray(data);
				return new JsonResult(resultData, result.getInteger("retCode"), result.getString("retMsg"));
			} else {
				return new JsonResult(data, result.getInteger("retCode"), result.getString("retMsg"));
			}
		}
		return new JsonResult(null, result.getInteger("retCode"), result.getString("retMsg"));
	}
	
	public static JsonResult sendPosts(RestTemplate restTemplate, String url, Object param) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		HttpEntity formEntity = new HttpEntity(JSONObject.parse(JSON.toJSONString(param)), headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, formEntity, String.class);
		String body = postForEntity.getBody();
		JSONObject result = JSONObject.parseObject(body);
		String data = result.getString("data");
		String results = result.getString("result");
		JSONObject result1 = JSONObject.parseObject(results);
		if(!StringUntil.isNull(data)) { 
			if ("{".equals(data.substring(0, 1))) {
				JSONObject resultData = JSONObject.parseObject(data);
				return new JsonResult(resultData, result1.getInteger("retCode"), result1.getString("retMsg"));
			} else if ("[".equals(data.substring(0, 1))) {
				JSONArray resultData = JSON.parseArray(data);
				return new JsonResult(resultData, result1.getInteger("retCode"), result1.getString("retMsg"));
			} else {
				return new JsonResult(data, result1.getInteger("retCode"), result1.getString("retMsg"));
			}
		}
		return new JsonResult(null, result1.getInteger("retCode"), result1.getString("retMsg"));
	}
}
