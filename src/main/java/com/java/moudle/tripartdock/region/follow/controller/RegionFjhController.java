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
 * @ClassName: RegionFjhController
 * @Description: 三方对接 查询肺结核信息
 * @author Administrator
 * @date 2019年8月1日
 */
@RestController
@RequestMapping("/health/follow")
public class RegionFjhController extends BaseController {
	
	private String HOST = PropertiesUtil.getRegion("regionUrl");

	
	/**
	 * 获取肺结核入户随访
	 */
	@RequestMapping("/getFjhRh")
	public JsonResult getFjhRh() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("FJHRH"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取肺结核入户随访列表
	 */
	@RequestMapping("/getFjhRhList")
	public JsonResult getFjhRhList() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("FJHRHLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取肺结核随访
	 */
	@RequestMapping("/getFjh")
	public JsonResult getFjh() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("FJH"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取肺结核随访列表
	 */
	@RequestMapping("/getFjhList")
	public JsonResult getFjhList() {
		try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("FJHLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}
}
