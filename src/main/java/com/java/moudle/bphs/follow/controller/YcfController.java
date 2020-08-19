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
 * @Date: 2020-03-23 11:32
 **/
@RestController
@RequestMapping("ycf")
public class YcfController extends BaseController {

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Description 获取孕产妇详情(旧公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getYcfDetails")
    public JsonResult getYcfDetails () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getYcfDetails"), JSON.parseObject(param));
    }

    /**
     * @Description 获取孕产妇详情(新公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getYcfDetailsNew")
    public JsonResult getYcfDetailsNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getYcfDetailsNew"), param);
    }

    /**
     * 获取孕产妇列表（旧公卫）
     */
    @RequestMapping("getYcfSFList")
    public JsonResult getYcfSFList() {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getYcfSFList"), JSON.parseObject(param));
    }
    
    /**
     * 获取孕产妇列表（新公卫）
     */
    @RequestMapping("getYcfSFListNew")
    public JsonResult getYcfSFListNew() {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getYcfSFListNew"), param);
    }
}
