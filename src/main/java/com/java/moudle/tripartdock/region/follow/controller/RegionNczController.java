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
 * @ClassName: RegionNczController
 * @Description: 三方对接 查询脑卒中随访信息
 * @author Administrator
 * @date 2019年8月2日
 */
@RestController
@RequestMapping("/health/follow")
public class RegionNczController extends BaseController {

	private String HOST = PropertiesUtil.getRegion("regionUrl");

	/**
	 * 脑卒中
	 */
	@RequestMapping("/getNcz")
	public JsonResult getNcz() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("NCZ"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 脑卒中列表
	 */
	@RequestMapping("/getNczList")
	public JsonResult getNczList() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("NCZLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}
}
