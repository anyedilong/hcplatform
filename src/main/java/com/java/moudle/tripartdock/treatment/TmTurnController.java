package com.java.moudle.tripartdock.treatment;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;

@RestController
@RequestMapping("/treat/turn")
public class TmTurnController extends BaseController {
   
	@Inject
	private RestTemplate restTemplate;
	
	/**
	 * @Description: 查询双向转诊列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getTurnPage")
	public JsonResult getTurnPage() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh = paramJson.getString("sfzh");
			String pageNo = paramJson.getString("pageNo");
			String pageSize = paramJson.getString("pageSize");
			if (StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10000, "参数身份证号不能为空");
			} 
			if (StringUntil.isNull(pageNo)) {
				return jsonResult(null, 10000, "参数页码不能为空");
			} 
			if (StringUntil.isNull(pageSize)) {
				return jsonResult(null, 10000, "参数页面大小不能为空");
			} 
			String url = PropertiesUtil.getFollow("getTurnPage");
			url = url+"?sfzh="+sfzh+"&pageNo="+pageNo+"&pageSize="+pageSize;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return jsonResult(JSONObject.parseObject(body));
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}

	/**
	 * @Description: 双向转诊详情查看
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getTurnDetail")
	public JsonResult getTurnDetail() {
		try {
			String param = getParam(request);
			JSONObject jsonObj = JSON.parseObject(param);
			String id = jsonObj.getString("id");
			String type = jsonObj.getString("type");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "标识不能为空");
			} 
			if (StringUntil.isNull(type)) {
				return jsonResult(null, 10000, "类型不能为空");
			} 
			String url = PropertiesUtil.getFollow("getTurnDetail");
			url = url+"?id="+id+"&type="+type;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return jsonResult(JSONObject.parseObject(body));
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
}
