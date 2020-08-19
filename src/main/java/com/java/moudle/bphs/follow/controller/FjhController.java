package com.java.moudle.bphs.follow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.bphs.follow.service.FjhService;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
/**
 * <p>Title: FjhController.java</p>  
 * <p>Description : 肺结核随访</p> 
 * <p>Copyright: Copyright (c) 2020</p> 
 * @author : 皮雪平
 * @date : 2020/3/20 14:54 
 * @version : V1.0.0
 */
@RestController
@RequestMapping("follow/fjh")
public class FjhController extends BaseController {
    private final String fdssUrl = PropertiesUtil.getFollow("fdssPrefix");
    @Inject
    private FjhService fjhService;

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Title : 旧公卫获取肺结核入户列表
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/20 14:55 
     * @param : 
     * @return : 
     * @throws
     */
    @RequestMapping("getFjhrhList")
    public JsonResult getFjhrhList() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String sfzh = paramObj.getString("sfzh");
            if (!StringUtils.isNull(sfzh)) {
                String pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? "10" : paramObj.getString("pageSize");
                String pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? "1" : paramObj.getString("pageNo");
                String year = paramObj.getString("year");
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("sfzh", sfzh);
                paramMap.put("year", year);
                paramMap.put("pageSize", pageSize+"");
                paramMap.put("pageNo", pageNo+"");
                String url =  fdssUrl +  PropertiesUtil.getFollow("getFjhrhList");
                JsonResult result = HttpUtil.providePost(url, paramMap);
                return result;
            }else{
                return jsonResult(null, 10001, "参数为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }

    /**
     * @Title : 旧公卫获取肺结核入户详情
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/20 14:55 
     * @param : 
     * @return : 
     * @throws
     */
    @RequestMapping("getFjhrhDetail")
    public JsonResult getFjhrhDetail() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String id = paramObj.getString("id");
            if (!StringUtils.isNull(id)) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", id);
                String url =  fdssUrl +  PropertiesUtil.getFollow("getFjhrhDetail");
                JsonResult result = fjhService.getFjhrhDetail(url,paramMap);
                return result;
            }else{
                return jsonResult(null, 10001, "参数为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }

    /**
     * @Title : 旧公卫获取肺结核随访列表
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/20 14:56 
     * @param : 
     * @return : 
     * @throws
     */
    @RequestMapping("getFjhFollowList")
    public JsonResult getFjhFollowList() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String sfzh = paramObj.getString("sfzh");
            if (!StringUtils.isNull(sfzh)) {
                String pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? "10" : paramObj.getString("pageSize");
                String pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? "1" : paramObj.getString("pageNo");
                String year = paramObj.getString("year");
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("sfzh", sfzh);
                paramMap.put("year", year);
                paramMap.put("pageSize", pageSize+"");
                paramMap.put("pageNo", pageNo+"");
                String url =  fdssUrl +  PropertiesUtil.getFollow("getFjhFollowList");
                JsonResult result = HttpUtil.providePost(url, paramMap);
                return result;
            }else{
                return jsonResult(null, 10001, "参数为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }

    /**
     * @Title : 旧公卫获取肺结核随访详情
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/20 14:56 
     * @param : 
     * @return : 
     * @throws
     */
    @RequestMapping("getFjhFollowDetail")
    public JsonResult getFjhFollowDetail() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String id = paramObj.getString("id");
            if (!StringUtils.isNull(id)) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", id);
                String url =  fdssUrl +  PropertiesUtil.getFollow("getFjhFollowDetail");
                JsonResult result = fjhService.getFjhFollowDetail(url,paramMap);
                return result;
            }else{
                return jsonResult(null, 10001, "参数为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }

    /**
     * 根据身份证号获取肺结核随访记录(新公卫)
     * @return
     */
    @RequestMapping("getFjhlist")
    public JsonResult getFjhlist () {
        String param = getParam(request);
        logger.info("getFjhlist获取肺结核随访记录入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getFjhlist"), param);
        logger.info("getFjhlist获取肺结核随访记录出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 根据身份证号获取肺结核随访记录(新公卫)
     * @return
     */
    @RequestMapping("getNFjhrhlist")
    public JsonResult getNFjhrhlist () {
        String param = getParam(request);
        logger.info("getNFjhrhlist获取肺结核入户随访记录入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getNFjhrhList"), param);
        logger.info("getNFjhrhlist获取肺结核入户随访记录出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 获取肺结核随访详情(新公卫)
     * @return
     */
    @RequestMapping("getFjhXq")
    public JsonResult getFjhXq () {
        String param = getParam(request);
        logger.info("getFjhXq获取肺结核随访详情入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getFjhXq"), param);
        logger.info("getFjhXq获取肺结核随访详情出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 获取肺结核入户随访详情(新公卫)
     * @return
     */
    @RequestMapping("getFjhrhXq")
    public JsonResult getFjhrhXq () {
        String param = getParam(request);
        logger.info("getFjhrhXq获取肺结核入户随访详情入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getFjhrhXq"), param);
        logger.info("getFjhrhXq获取肺结核入户随访详情出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }


}
