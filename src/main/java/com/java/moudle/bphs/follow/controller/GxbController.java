package com.java.moudle.bphs.follow.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.http.HttpUtil;
/**
 * <p>Title: GxyController.java</p>
 * <p>Description : 高血压随访</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : 皮雪平
 * @date : 2020/3/12 9:07
 * @version : V1.0.0
 */
@RestController
@RequestMapping("follow/gxb")
public class GxbController extends BaseController {

    private final String bltsysUrl = PropertiesUtil.getFollow("prefix");

    /**
     * @Description: 获取冠心病列表
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getGxbList")
    public JsonResult getGxbList() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String sfzh = paramObj.getString("sfzh");
            if (!StringUtils.isNull(sfzh)) {
            	String year = paramObj.getString("year");
                String pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? "10" : paramObj.getString("pageSize");
                String pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? "1" : paramObj.getString("pageNo");
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("sfzh", sfzh);
                paramMap.put("pageSize", pageSize+"");
                paramMap.put("pageNo", pageNo+"");
                paramMap.put("year", year);
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getGxbList");
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
     * @Description: 获取冠心病的详情
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getGxbDetail")
    public JsonResult getGxbDetail() {
        try {
            String param = getParam(request);
            JSONObject paramObj = JSONObject.parseObject(param);
            String id = paramObj.getString("id");
            if (!StringUtils.isNull(id)) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", id);
                String url =  bltsysUrl +  PropertiesUtil.getFollow("getGxbDetail");
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
}
