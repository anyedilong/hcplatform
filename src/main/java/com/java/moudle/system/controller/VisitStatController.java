package com.java.moudle.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.VisitStatDto;
import com.java.moudle.system.service.VisitStatService;
import com.java.until.StringUntil;

@RestController
@RequestMapping("visit")
public class VisitStatController extends BaseController {

    @Autowired
    private VisitStatService visitStatService;

    //查询访问量
    @RequestMapping("visitStat")
    public JsonResult visitStat() {
        try {
            String param = getParam(request);
            logger.info("查询访问量请求参数：" + param);
            VisitStatDto dto = JSONObject.parseObject(param, VisitStatDto.class);
            return jsonResult(visitStatService.visitStat(dto));
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }
    
    //活跃用户的统计
    @RequestMapping("activeUserCharts")
    public JsonResult activeUserStat() {
        try {
            String param = getParam(request);
            logger.info("查询访问量请求参数：" + param);
            VisitStatDto dto = JSONObject.parseObject(param, VisitStatDto.class);
            dto.setType("2");
            if(StringUntil.isNull(dto.getStatType())) {
            	dto.setStatType("1");
            }
            Map<String, Object> resp = visitStatService.visitCharts(dto);
            return jsonResult(resp);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }
    
    //访问量统计
    @RequestMapping("visitCharts")
    public JsonResult visitCharts() {
        try {
            String param = getParam(request);
            logger.info("查询访问量请求参数：" + param);
            VisitStatDto dto = JSONObject.parseObject(param, VisitStatDto.class);
            if(StringUntil.isNull(dto.getStatType())) {
            	dto.setStatType("1");
            }
            Map<String, Object> resp = visitStatService.visitCharts(dto);
            return jsonResult(resp);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }
    
}
