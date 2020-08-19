package com.java.moudle.bphs.sign;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
@RestController
@RequestMapping("sign")
public class Controller extends BaseController{
	@Inject
	private RestTemplate restTemplate;
	
	// 1 老公卫 2 新公卫
	private int flag = 1;
	
	@RequestMapping("hmSignxyList")
	public JsonResult hmSignxyList() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh= paramJson.getString("sfzh");
			if (sfzh != null) {
//				return HttpUtil.providePost(
//						PropertiesUtil.getFollow("fdssurl") + PropertiesUtil.getFollow("querySignList"), paramJson);
				return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("hmSignxyList"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	@RequestMapping("getPackageInfo")
	public JsonResult getPackageInfo() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String packageId= paramJson.getString("packageId");
			if (packageId != null) {
				return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("getPackageInfo"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	@RequestMapping("getSignInfo")
	public JsonResult getSignInfo() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh= paramJson.getString("id");
			if (sfzh != null) {
//				return HttpUtil.providePost(
//				PropertiesUtil.getFollow("fdssurl") + PropertiesUtil.getFollow("querySignXq"), paramJson);

				return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("getSignInfo"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}

	/**
	 * 获取家庭签约信息
	 */
	@RequestMapping("querySignFamily")
	public JsonResult querySignFamily() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			if (!paramJson.containsKey("sfzh")) {
				return jsonResult(null, 10001, "参数身份证号不能为空");
			}
			if (!paramJson.containsKey("signType")) {
				return jsonResult(null, 10001, "参数签约类型不能为空");
			}
			
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("fdssPrefix") + PropertiesUtil.getFollow("queryOldSignFamily"), paramJson);
			} else {
				//return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("jktjDetails"), param);
				return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("querySignFamily"), param);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
}
