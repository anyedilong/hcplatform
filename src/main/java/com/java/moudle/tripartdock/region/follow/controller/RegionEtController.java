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
 * @ClassName: RegionEtController
 * @Description: 三方对接 查询儿童信息
 * @author Administrator
 * @date 2019年8月14日
 */
@RestController
@RequestMapping("/health/follow")
public class RegionEtController extends BaseController {

	private String HOST = PropertiesUtil.getRegion("regionUrl");
	
	/**
	 * 获取儿童列表
	 */
	@RequestMapping("/getEtList")
	public JsonResult getEtList() {
        try {
        	String param = getParam(request);
        	JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
            String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_ET_LIST"), param);
            return JSONObject.parseObject(resultStr, JsonResult.class);
        }catch(Exception e) {
        	e.printStackTrace();
        	return jsonResult(null, -1, "系统错误");
        }
	}

	/**
	 * 获取儿童基本
	 */
	@RequestMapping("/getEtJb")
	public JsonResult getInfo() {
		 try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ETJBINFO"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取新生儿
	 */
	@RequestMapping("/getXse")
	public JsonResult getXse() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("XSEINFO"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取新生儿列表
	 */
	@RequestMapping("/getXseList")
	public JsonResult getXseList() {
		try {	
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("XSELIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童1岁
	 */
	@RequestMapping("/getEt1")
	public JsonResult getEt1() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET1INFO"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童1岁列表
	 */
	@RequestMapping("/getEt1List")
	public JsonResult getEt1List() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET1LIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童3岁
	 */
	@RequestMapping("/getEt3")
	public JsonResult getEt3() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET3INFO"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童3岁列表
	 */
	@RequestMapping("/getEt3List")
	public JsonResult getEt3List() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}	 
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET3LIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童6岁
	 */
	@RequestMapping("/getEt6")
	public JsonResult getEt6() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET6INFO"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童6岁列表
	 */
	@RequestMapping("/getEt6List")
	public JsonResult getEt6List() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET6LIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * 获取儿童1岁中医药
	 */
	@RequestMapping("/getEt1Zyy")
	public JsonResult getEt1Zyy() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET1ZYY"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童1岁中医药列表
	 */
	@RequestMapping("/getEt1ZyyList")
	public JsonResult getEt1ZyyList() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET1ZYYLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童3岁中医药
	 */
	@RequestMapping("/getEt3Zyy")
	public JsonResult getEt3Zyy() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET3ZYY"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童3岁中医药列表
	 */
	@RequestMapping("/getEt3ZyyList")
	public JsonResult getEt3ZyyList() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET3ZYYLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童6岁中医药
	 */
	@RequestMapping("/getEt6Zyy")
	public JsonResult getEt6Zyy() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("id")) {
        		return jsonResult(null, 10000, "标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET6ZYY"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}

	/**
	 * 获取儿童6岁中医药列表
	 */
	@RequestMapping("/getEt6ZyyList")
	public JsonResult getEt6ZyyList() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
        	if(!jsonObj.containsKey("jmid")) {
        		return jsonResult(null, 10000, "居民标识不能为空");
        	}
			String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("ET6ZYYLIST"), param);
			return JSONObject.parseObject(resultStr, JsonResult.class);
		}catch(Exception e) {
	    	e.printStackTrace();
	    	return jsonResult(null, -1, "系统错误");
	    }
	}
}
