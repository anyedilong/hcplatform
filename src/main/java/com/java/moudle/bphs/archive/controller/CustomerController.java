package com.java.moudle.bphs.archive.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.bphs.archive.dto.HmXqDto;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
@RestController
@RequestMapping("customer")
public class CustomerController  extends BaseController{
	@Inject
	private RestTemplate restTemplate;
	
	/**
	  *  健康信息
	 * @return
	 * @author lnz
	 * @date 2020-03-05 09:41:26
	 */
	@RequestMapping("getjkxx")
	public JsonResult getFirstFjhDetail() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh= paramJson.getString("sfzh");
			if (sfzh != null) {
				return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getjkxx"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	/**
	 * 档案详情
	 * 
	 * @return
	 * @author lnz
	 * @date 2020-03-05 02:31:13
	 */
	@RequestMapping("getDAXQBySfzh")
	public JsonResult getDAXQBySfzh() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh= paramJson.getString("sfzh");
			if (sfzh != null) {
				String resStr = HttpUtil.doPost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("xqcx"), param);
		        JSONObject result = JSONObject.parseObject(resStr);
		        String data = result.get("data").toString();
		        JSONObject resultJson = result.getJSONObject("result");
		        int code = resultJson.getInteger("ret_code");
		        String msg = resultJson.getString("ret_msg");
		        if(!StringUntil.isNull(data)) {
		            data = HttpUtil.lineToHump(data);
		            HmXqDto xq = JSON.parseObject(data, HmXqDto.class);
		            return jsonResult(xq, code, msg);
		        }
		        return jsonResult(null, code, msg);
				//return HttpUtil.providePost(
				//		PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("xqcx"), paramJson);
				//return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getDAXQBySfzh"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	/**
	 * 家庭情况查询
	 * 
	 * @return
	 * @author lnz
	 * @date 2020-03-13 09:05:55
	 */
	@RequestMapping("getjtqk")
	public JsonResult getjtqk() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String jtid= paramJson.getString("jtid");
			if (jtid != null) {
				return HttpUtil.providePost(
						PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getjtqk"), paramJson);
				//return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getjtqk"), param);
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	/**
	 * @Description: 通过身份证号获取人群类型
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("queryRqlxBySfzh")
	public JsonResult queryRqlxBySfzh() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh= paramJson.getString("sfzh");
			if (sfzh != null) {
				return HttpUtil.providePost(
						PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("queryRqlxBySfzh"), paramJson);
			} else {
				return jsonResult(null, 10001, "参数身份证号不能为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
}