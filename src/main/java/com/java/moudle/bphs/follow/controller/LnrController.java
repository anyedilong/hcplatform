package com.java.moudle.bphs.follow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dao.SysPhysicalHealthcareDao;
import com.java.moudle.system.domain.SysPhysicalHealthcare;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author ZhangWei
 * @Date: 2020-03-06 16:06
 **/
@RestController
@RequestMapping("lnr")
public class LnrController extends BaseController {


    @Inject
    private RestTemplate restTemplate;

    @Inject
    private SysPhysicalHealthcareDao sysPhysicalHealthcareDao;

    /**
     * @Author zw
     * @Description 惠民获取中医药健康管理列表(老公卫)
     * @Date 13:39 2020-03-06
     * @Param sfzh
     * @Param pageNo
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getLnrFollowtzList")
    public JsonResult getLnrFollowtzList () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getLnrFollowtzList"), JSON.parseObject(param));
    }

    /**
     * @Author zw
     * @Description 获取中医药健康管理列表详情(老公卫)
     * @Date 14:40 2020-03-06
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getLnrFollowTzXq")
    public JsonResult getLnrFollowTzXq () {
        String param = getParam(request);
        JsonResult jsonResult = HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getLnrFollowTzXq"), JSON.parseObject(param));
        JSONObject json = JSON.parseObject(jsonResult.getData().toString());
        // 保健指导
        Map<String, JSONArray> map = CacheUntil.get(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, Map.class);

        JSONArray array = map.get("PhysicalType");
        // 查找体质类型code
        String tzCode = null;
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            if (json.get("tz").toString().equals(object.get("name").toString())) {
                tzCode = object.get("code").toString();
            }
        }
        // 根据code去查找保健指导详情
        SysPhysicalHealthcare sysPhysicalHealthcare = sysPhysicalHealthcareDao.getGuidanceByType(tzCode);
        json.put("sysPhysicalHealthcare", sysPhysicalHealthcare);
        jsonResult.setData(json);
        return jsonResult;
    }

    /**
     * @Author zw
     * @Description 惠民平台获取老年人自理能力评估列表(老公卫)
     * @Date 09:19 2020-03-09
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getLnrFollowPgList")
    public JsonResult getLnrFollowPgList () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getLnrFollowPgList"), JSON.parseObject(param));
    }


    /**
     * @Author zw
     * @Description 惠民平台获取老年人自理能力评估详情
     * @Date 09:19 2020-03-09
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getLnrFollowPgXq")
    public JsonResult getLnrFollowPgXq () {
        String param = getParam(request);
        return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getLnrFollowPgXq"), JSON.parseObject(param));
    }

    /**
     * @Author zw
     * @Description 惠民获取中医药健康管理列表(新)
     * @Date 13:39 2020-03-06
     * @Param sfzh
     * @Param pageNo
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getHmLnrzlnlpgList")
    public JsonResult getHmLnrzlnlpgList () {
        String param = getParam(request);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getHmLnrzlnlpgList"), param);
        return jsonResult;
    }

    /**
     * @Author zw
     * @Description 获取中医药健康管理列表详情(新)
     * @Date 14:40 2020-03-06
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getHmLnrzlnlpgjgDetail")
    public JsonResult getHmLnrzlnlpgjgDetail () {
        String param = getParam(request);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getHmLnrzlnlpgjgDetail"), param);
        return jsonResult;
    }

    /**
     * @Author zw
     * @Description 惠民平台获取老年人自理能力评估列表(新)
     * @Date 09:19 2020-03-09
     * @Param
     * @return com.java.moudle.common.domain.JsonResult
     **/
    @RequestMapping("getHmLnrzlnljkpgbList")
    public JsonResult getHmLnrzlnljkpgbList () {
        String param = getParam(request);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getHmLnrzlnljkpgbList"), param);
        return jsonResult;
    }


}
