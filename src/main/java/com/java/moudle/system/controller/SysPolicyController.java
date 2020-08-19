package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysPolicy;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysPolicyService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 政策法规管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/1/2 9:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/policy")
public class SysPolicyController  extends BaseController {
    
	@Inject
    private SysPolicyService policyService;
	@Inject
	private SysUserService userService;
	
	@RequestMapping("getPolicyPage")
    public JsonResult getPolicyPage() {
    	try {
    		String param = getParam(request);
    		SysPolicy policy = JSONObject.parseObject(param, SysPolicy.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		policyService.getPolicyPage(policy, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
	@RequestMapping("show")
    public JsonResult show() {
    	try {
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
    		if(StringUntil.isNull(id)) {
    			return jsonResult(null, 10000, "id不能为空"); 
    		}
    		SysPolicy info = policyService.get(id);
    		if(info != null) {
    			SysUser user = userService.get(info.getCreateUser());
    			info.setCreateUser(user.getName());
    		}
    		return jsonResult(info);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }

}
