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
 * @Description
 * @Date: 2020-03-23 11:29
 **/
@RestController
@RequestMapping("et")
public class EtController extends BaseController {

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Description 获取儿童详情(旧公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getErDetails")
    public JsonResult getErDetails () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getErDetails"), JSON.parseObject(param));
    }

    /**
     * @Description 获取儿童详情(新公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getErDetailsNew")
    public JsonResult getErDetailsNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getErDetailsNew"), param);
    }

    /**
     * 获取儿童列表（旧公卫）
     */
    @RequestMapping("getEtSFList")
    public JsonResult getEtSFList () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getEtSFList"), JSON.parseObject(param));
    }
    
    /**
     * 获取儿童列表（新公卫）
     */
    @RequestMapping("getEtSFListNew")
    public JsonResult getEtSFListNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getEtSFListNew"), param);
    }
}
