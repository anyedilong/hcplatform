package com.java.moudle.bphs.follow.controller;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.http.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangWei
 * @Description 接种史
 * @Date: 2020-03-10 17:15
 **/
@RestController
@RequestMapping("jzs")
public class JzsController extends BaseController {

    /**
     * @Author zw
     * @Description 获取已接种信息 (旧公卫)
     * @Date 16:52 2020-03-10
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getJzsList")
    public JsonResult getJzsList () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getJzsList"), JSON.parseObject(param));
    }

    /**
     * @Author zw
     * @Description 获取接种史详情 (旧公卫)
     * @Date 13:41 2020-03-16
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getJzsDetails")
    public JsonResult getJzsDetails () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getJzsDetails"), JSON.parseObject(param));
    }
}
