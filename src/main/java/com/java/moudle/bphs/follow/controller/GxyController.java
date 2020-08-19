package com.java.moudle.bphs.follow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.bphs.follow.service.GxyService;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;
/**
 * <p>Title: GxyController.java</p>
 * <p>Description : 高血压随访</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : 皮雪平
 * @date : 2020/3/12 9:07
 * @version : V1.0.0
 */
@RestController
@RequestMapping("follow/gxy")
public class GxyController extends BaseController {

    private final String bltsysUrl = PropertiesUtil.getFollow("prefix");
    @Inject
    private GxyService gxyService;

    @Inject
    private RestTemplate restTemplate;

    /**
     * @Title : 旧公卫根据身份证号获取高血压随访列表
     * @Description :
     * @author : 皮雪平
     * @date : 2020/3/12 9:07
     * @param :
     * @return :
     * @throws
     */
    @RequestMapping("getGxyList")
    public JsonResult getGxyList() {
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
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getGxyList");
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
     * @Title : 旧公卫获取高血压随访详情
     * @Description :
     * @author : 皮雪平
     * @date : 2020/3/12 9:07
     * @param :
     * @return :
     * @throws
     */
    @RequestMapping("getGxyDetail")
    public JsonResult getGxyDetail() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String id = paramObj.getString("id");
            if (!StringUtils.isNull(id)) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", id);
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getGxyDetail");
                JsonResult result = gxyService.getGxyDetail(url,paramMap);
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
     * @Author zw
     * @Description 根据身份证号获取高血压随访记录(新公卫)
     * @Date 13:18 2020-03-16
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getMxGxyFollowRecords")
    public JsonResult getMxGxyFollowRecords () {
        String param = getParam(request);
        logger.info("getMxGxyFollowRecords获取高血压随访记录入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getMxGxyFollowRecords"), param);
        logger.info("getMxGxyFollowRecords获取高血压随访记录出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Author zw
     * @Description 惠民获取高血压详情(新公卫)
     * @Date 13:18 2020-03-16
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getGxyXq")
    public JsonResult getGxyXq() {
        String param = getParam(request);
        logger.info("getGxyXq获取高血压随访详情入参: [{}]",  param);
        JsonResult jsonResult = RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("getGxyXq"), param);
        logger.info("getGxyXq获取高血压随访详情出参: [{}]",  JSON.toJSONString(jsonResult));
        return jsonResult;
    }
}
