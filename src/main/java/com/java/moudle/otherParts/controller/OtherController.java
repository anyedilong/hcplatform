package com.java.moudle.otherParts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysApplicationList;
import com.java.moudle.system.domain.SysGuide;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.moudle.system.domain.SysPolicy;
import com.java.moudle.system.service.SysApplicationListService;
import com.java.moudle.system.service.SysGuideService;
import com.java.moudle.system.service.SysNewsHealthService;
import com.java.moudle.system.service.SysPolicyService;

import io.netty.util.internal.StringUtil;

@RequestMapping("other")
@RestController
public class OtherController extends BaseController{

	@Inject
	private SysApplicationListService applicationListService;
	
	@Inject
	private SysPolicyService sysPolicyService;
	
	@Inject
	private SysNewsHealthService newsHealthService;
	
	@Inject 
	private SysGuideService guideService;
	
	/**
	 * 搜索框
	 */
	@RequestMapping("search")
	public JsonResult search() {
		try{
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);

    		// 定义返回值内容
    		Map<String, List> result = new HashMap<>();
    		
    		// 搜索内容
    		String searchClass = paramObj.getString("searchClass"); // 0全部 1相关应用 2政策法规 3健康资讯 4办事指南

    		// 搜索内容
    		String searchContent = paramObj.getString("searchContent"); // 搜索框的内容
    		if (StringUtil.isNullOrEmpty(searchContent)) {
    			return jsonResult(result);
    		}
    		
    		switch(searchClass) {
    			case "0":
    				// 0查询全部
    			case "1":
    	    		// 搜索相关应用内容
    	    		List<SysApplicationList> applicationList = applicationListService.getApplicationListByName(searchContent);
    	    		result.put("applicationList", applicationList);
    				if (!"0".equals(searchClass)) {
        				break;
    				}
    			case "2":
    	    		// 搜索政策法规
    				List<SysPolicy> policyList = sysPolicyService.getPolicyList(searchContent);
    	    		result.put("policyList", policyList);
    				if (!"0".equals(searchClass)) {
        				break;
    				}
    			case "3":
    	    		// 搜索健康资讯
    				List<SysNewsHealth> newsHealthList = newsHealthService.getNewsHealthList(searchContent);
    	    		result.put("newsHealthList", newsHealthList);
    				if (!"0".equals(searchClass)) {
        				break;
    				}
    			case "4":
    	    		// 搜索办事指南
    				List<SysGuide> guideList = guideService.getGuideList(searchContent);
    	    		result.put("guideList", guideList);
    				if (!"0".equals(searchClass)) {
        				break;
    				}
    		}
    		return jsonResult(result);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
}
