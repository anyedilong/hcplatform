package com.java.moudle.bphs.follow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.bphs.follow.service.TnbService;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
/**
 * <p>Title: TnbController.java</p>
 * <p>Description : 糖尿病随访</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : 皮雪平
 * @date : 2020/3/12 9:33
 * @version : V1.0.0
 */
@RestController
@RequestMapping("follow/tnb")
public class TnbController extends BaseController {

    private final String bltsysUrl = PropertiesUtil.getFollow("prefix");
    @Inject
    private TnbService tnbService;

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Title : 旧公卫获取糖尿病随访列表
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/12 9:34 
     * @param : 
     * @return : 
     * @throws
     */
    @RequestMapping("getTnbList")
    public JsonResult getTnbList() {
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
                paramMap.put("pageSize", pageSize+"");
                paramMap.put("pageNo", pageNo+"");
                paramMap.put("year", year);
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getTnbList");
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
     * @Title : 旧公卫获取糖尿病随访详情
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/12 9:34 
     * @param :
     * @return : 
     * @throws
     */
    @RequestMapping("getTnbDetail")
    public JsonResult getTnbDetail() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String id = paramObj.getString("id");
            if (!StringUtils.isNull(id)) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", id);
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getTnbDetail");
                JsonResult result = tnbService.getTnbDetail(url, paramMap);

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
     * 根据jmid获取糖尿病随访记录(新公卫)
     * @return
     */
    @RequestMapping("getMxTnbFollowRecords")
    public JsonResult getMxTnbFollowRecords () {
        String param = getParam(request);
        logger.info("getMxTnbFollowRecords获取糖尿病随访记录入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getMxTnbFollowRecords"), param);
        logger.info("getMxTnbFollowRecords获取糖尿病随访记录出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description: 获取糖尿病详情
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getMxTnbDetail")
    public JsonResult getMxTnbDetail() {
        try {
            String param = getParam(request);
            JSONObject paramJson = JSON.parseObject(param);
            String id = paramJson.getString("id");
            if (id != null) {
                return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getMxTnbDetail"), param);
            } else {
                return jsonResult(null, 10001, "参数为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return jsonResult(null, 10001, e.getMessage());
        }
    }

    /**
     * @Author zw
     * @Description 惠民获取糖尿病详情(新公卫)
     * @Date 13:18 2020-03-16
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getTnbXq")
    public JsonResult getTnbXq() {
        String param = getParam(request);
        logger.info("getTnbXq获取糖尿病随访详情入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getTnbXq"), param);
        logger.info("getTnbXq获取糖尿病随访详情出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }
}
