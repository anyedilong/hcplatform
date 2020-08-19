package com.java.moudle.bphs.follow.controller;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * @author ZhangWei
 * @Date: 2020-03-06 09:44
 **/
@RestController
@RequestMapping("follow/jsb")
public class MxJsbController extends BaseController {

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Author zw
     * @Description 根据身份证号获取家庭精神病列表
     * @Date 10:47 2020-03-06
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getFamIlyJsbfList")
    public JsonResult getFamIlyJsbfList () {
        String param = getParam(request);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getFamIlyJsbfList"), param);
        return jsonResult;
    }
    
    /**
     * 根据身份证号获取精神病列表
     */
    @RequestMapping("getJsbSfList")
    public JsonResult getJsbSfList() {
    	String param = getParam(request);
        JsonResult jsonResult = HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getJsbSfList"), JSON.parseObject(param));
        return jsonResult;
    }
    
    /**
     * 根据id获取精神病详情
     */
    @RequestMapping("getJsbSfInfo")
    public JsonResult getJsbSfInfo() {
    	String param = getParam(request);
        JsonResult jsonResult = HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getJsbSfInfo"), JSON.parseObject(param));
        return jsonResult;
    }
    
    /**
     * 根据身份证号获取重度精神病列表
     */
    @RequestMapping("getYzJsbSfList")
    public JsonResult getYzJsbSfList() {
    	String param = getParam(request);
        JsonResult jsonResult = HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getYzJsbSfList"), JSON.parseObject(param));
        return jsonResult;
    }
    
    /**
     * 根据身份证号获取中度精神病详情
     */
    @RequestMapping("getYzJsbSfInfo")
    public JsonResult getYzJsbSfInfo() {
    	String param = getParam(request);
        JsonResult jsonResult = HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getYzJsbSfInfo"), JSON.parseObject(param));
        return jsonResult;
    }
    
}
