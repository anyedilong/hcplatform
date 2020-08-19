package com.java.moudle.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysPhysicalHealthcare;
import com.java.moudle.system.service.SysPhysicalHealthcareService;
import com.java.until.cache.DictDto;

@RequestMapping("guidance")
@RestController
public class SysPhysicalHealthcareController extends BaseController {

    @Inject
    private SysPhysicalHealthcareService service;

    /**
     * 获取指导类型
     */
    @RequestMapping("getPhysicalType")
    public JsonResult getPhysicalType() {
        try {
            // 查询详情
            List<DictDto> list = service.getPhysicalType();
            return jsonResult(list);
        } catch (Exception e) {
            return jsonResult(null, -1, "系统错误");
        }
    }

    /**
     * 根据指导类型获取详情
     *
     * @return
     */
    @RequestMapping("xq")
    public JsonResult guidanceInfo() {
        try {
            String param = getParam(request);
            JSONObject json = JSON.parseObject(param);
            if (!json.containsKey("physicalType")) {
                return jsonResult(null, 9001, "缺少参数physicalType");
            }
            // 查询详情
            String physicalType = json.get("physicalType").toString();
            SysPhysicalHealthcare healthcare = service.getGuidanceByType(physicalType);

            return jsonResult(healthcare);
        } catch (Exception e) {
            return jsonResult(null, -1, "系统错误");
        }
    }

    /**
     * 添加或者更新
     */
    @RequestMapping("save")
    public JsonResult addOrUpdateGuidanceInfo(SysPhysicalHealthcare healthcase) {
        try {
            System.out.println("请求参数：" + JSON.toJSONString(healthcase));
            // 添加或者更新
            service.addOrUpdateGuidanceInfo(healthcase);

            return jsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    /**
     * 根据体质类型和建议获取详情
     *
     * @return 聂亚威
     */
    @RequestMapping("getPhysicalByType")
    public List getPhysicalByType() {

        String param = getParam(request);
        logger.info("根据体质类型和建议获取详情请求参数：" + param);
        JSONObject json = JSON.parseObject(param);
        List list = json.getJSONArray("list");
        if (list == null || list.size() == 0)
            return new ArrayList();
        List resp = service.getPhysicalByType(list);
        logger.info("根据体质类型和建议获取详情返回：" + JSON.toJSONString(resp));
        return resp;

    }
}
