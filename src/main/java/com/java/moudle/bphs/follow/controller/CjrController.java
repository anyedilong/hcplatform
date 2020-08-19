package com.java.moudle.bphs.follow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-23 11:01
 **/
@RestController
@RequestMapping("cjr")
public class CjrController extends BaseController {

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Description 获取残疾人详情(旧公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getCjrFollowDetails")
    public JsonResult getCjrFollowDetails () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCjrFollowDetails"), JSON.parseObject(param));
    }

    /**
     * @Description 获取残疾人详情(新公卫)
     * @Author zw
     * @Date 09:37 2020-03-20
     * @Param
     * @return org.sdblt.common.message.JsonResult
     **/
    @RequestMapping("getCjrFollowDetailsNew")
    public JsonResult getCjrFollowDetailsNew () {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getCjrFollowDetailsNew"), param);
    }

    /**
     * 获取残疾人列表（旧公卫）
     * @return
     */
   @RequestMapping("getCjrList")
    public JsonResult getCjrList() {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCjrSFList"), JSON.parseObject(param));
    }
    
    /**
     * 获取残疾人列表（新公卫）
     */
   @RequestMapping("getCjrListNew")
    public JsonResult getCjrListNew() {
        String param = getParam(request);
        return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getCjrSFListNew"), param);
    }
}
