package com.java.moudle.tripartdock.region.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;

/**
 * @ClassName: RegionSignController
 * @Description: 三方对接 查询签约的信息
 * @author Administrator
 * @date 2019年7月25日
 */

@RestController
@RequestMapping("/health/sign")
public class RegionSignController extends BaseController {

	private String HOST = PropertiesUtil.getRegion("regionUrl");

	/**
	 * 	获取签约详情
	 */
	@RequestMapping("/getSignInfo")
	public JsonResult getSignInfo() throws Exception {
		String param = getParam(request);
		JSONObject paramJson = JSONObject.parseObject(param);
		String jmId = paramJson.getString("jmId");
		if (StringUtils.isBlank(jmId)) {
			return jsonResult(null, 90001, "居民编号不能为空！");
		}
		String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_SIGN"), param);
		return JSONObject.parseObject(resultStr, JsonResult.class);
	}
	
	/**
	 * 	获取履约服务分页
	 */
	@RequestMapping("/getSignServiceList")
	public JsonResult getSignServiceList() throws Exception {
		String param = getParam(request);
		logger.info("查询签约列表请求参数：" + param);
		JSONObject paramJson = JSONObject.parseObject(param);
		String jmId = paramJson.getString("jmId");
		if (StringUtils.isBlank(jmId)) {
			return jsonResult(null, 90001, "居民编号不能为空！");
		}
		String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_RECORD_LIST"), param);
		return JSONObject.parseObject(resultStr, JsonResult.class);
	}
	
	/**
	 * 	获取履约服务详情
	 */
	@RequestMapping("/getSignServiceInfo")
	public JsonResult getSignServiceInfo() throws Exception {
		String param = getParam(request);
		logger.info("查询签约详情请求参数：" + param);
		JSONObject paramJson = JSONObject.parseObject(param);
		String signId = paramJson.getString("signId");
		String packeageId = paramJson.getString("packeageId");
		String projectId = paramJson.getString("projectId");
		if (StringUtils.isBlank(signId) || StringUtils.isBlank(packeageId) || StringUtils.isBlank(projectId)) {
			return jsonResult(null, 90001, "参数不能为空！");
		}
		String resultStr = HttpUtil.doPost(HOST + PropertiesUtil.getRegion("REGION_RECORD_INFO"), param);
		return JSONObject.parseObject(resultStr, JsonResult.class);
	}


}
