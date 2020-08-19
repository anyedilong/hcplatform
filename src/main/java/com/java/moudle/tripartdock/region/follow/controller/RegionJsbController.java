package com.java.moudle.tripartdock.region.follow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;

/**
 * @ClassName: RegionJsbController
 * @Description: 三方对接  查询精神病信息
 * @author Administrator
 * @date 2019年8月1日
 */
@RestController
@RequestMapping("/health/follow")
public class RegionJsbController extends BaseController {

	private String HOST = PropertiesUtil.getRegion("regionUrl");

	
	/**
	 * 精神病基本信息
	 */
	@RequestMapping("/getJsbJb")
	public JsonResult getJsbJb() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_JSBJB"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 精神病基本信息列表
	 */
	@RequestMapping("/getJsbJbList")
	public JsonResult getJsbJbList() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_JSBJBLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 精神病信息
	 */
	@RequestMapping("/getJsb")
	public JsonResult getJsb() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_JSB"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 精神病信息列表
	 */
	@RequestMapping("/getJsbList")
	public JsonResult getJsbList() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_JSBLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}
}
