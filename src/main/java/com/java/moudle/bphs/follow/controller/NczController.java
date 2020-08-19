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
 * @Date: 2020-03-23 11:14
 **/
@RestController
@RequestMapping("ncz")
public class NczController extends BaseController {

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Description 获取脑卒中详情(老公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getNczFollowDetails")
    public JsonResult getNczFollowDetails () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getNczFollowDetails"), JSON.parseObject(param));
    }

    /**
     * @Description 获取脑卒中详情(新公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getNczFollowDetailsNew")
    public JsonResult getNczFollowDetailsNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getNczFollowDetailsNew"), param);
    }

    /**
     * 获取脑卒中列表（老公卫）
     */
    @RequestMapping("getNczSFList")
    public JsonResult getNczList () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getNczSFList"), JSON.parseObject(param));
    }
    
    /**
     * 获取脑卒中列表（新公卫）
     */
    @RequestMapping("getNczSFListNew")
    public JsonResult getNczSFListNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getNczSFListNew"), param);
    }
    
}
