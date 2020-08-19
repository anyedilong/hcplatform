package com.java.moudle.bphs.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("record")
public class RecordController extends BaseController {
	@Inject
	private RestTemplate restTemplate;
    //查询居民履约列表
    @RequestMapping("getCustomerRecordList")
    public JsonResult getCustomerRecordList() {
        try {
            String param = getParam(request);
            logger.info("查询居民履约列表请求参数：" + param);
            JSONObject paramJson = JSON.parseObject(param);
            String sfzh = paramJson.getString("sfzh");
            if (sfzh != null) {
                return HttpUtil.providePost(
                        PropertiesUtil.getFollow("fdssPrefix") + PropertiesUtil.getFollow("getCustomerRecordList"), paramJson);
            } else {
                return jsonResult(null, 10001, "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }
  //惠民履约详情
    @RequestMapping("getCustomerRecordDetails")
    public JsonResult getCustomerRecordDetails() {
        try {
            String param = getParam(request);
            logger.info("查询居民履约详情请求参数：" + param);
            JSONObject paramJson = JSON.parseObject(param);
            String recordId = paramJson.getString("recordId");
            if (recordId != null) {
            	return HttpUtil.providePost(
                        PropertiesUtil.getFollow("fdssPrefix") + PropertiesUtil.getFollow("getCustomerRecordDetails"), paramJson);
            } else {
                return jsonResult(null, 10001, "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }
    //惠民履约列表
    @RequestMapping("getResidentRecordList")
    public JsonResult getResidentRecordList() {
        try {
            String param = getParam(request);
            logger.info("查询居民履约列表请求参数：" + param);
            JSONObject paramJson = JSON.parseObject(param);
            String sfzh = paramJson.getString("sfzh");
            if (sfzh != null) {
            	return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("getResidentRecordList"), param);
            } else {
                return jsonResult(null, 10001, "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }
    //惠民履约详情
    @RequestMapping("personageRecordDetails")
    public JsonResult personageRecordDetails() {
        try {
            String param = getParam(request);
            logger.info("查询居民履约列表请求参数：" + param);
            JSONObject paramJson = JSON.parseObject(param);
            String sfzh = paramJson.getString("id");
            if (sfzh != null) {
            	return RestTemplateUtils.sendPosts(restTemplate, PropertiesUtil.getFollow("personageRecordDetails"), param);
            } else {
                return jsonResult(null, 10001, "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }
}
