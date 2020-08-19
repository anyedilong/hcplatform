package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysNewsCategoryService;
import com.java.moudle.system.service.SysNewsHealthService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 健康咨讯管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/lb")
public class SysNewsHealthController  extends BaseController {
    
	@Inject
    private SysNewsHealthService newsHealthService;
	@Inject
    private SysNewsCategoryService newsCategoryService;
	@Inject
    private SysUserService userService;

	/**
	 * @Description: 查询健康资讯列表（分页）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getNewsHealthPage")
    public JsonResult getNewsHealthPage() {
    	try {
    		String param = getParam(request);
    		SysNewsHealth health = JSONObject.parseObject(param, SysNewsHealth.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		newsHealthService.getNewsHealthPage(health, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
	/**
	 * @Description: 查询健康资讯详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("show")
    public JsonResult show() {
    	try {
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
    		if(StringUntil.isNull(id)) {
    			return jsonResult(null, 10000, "id不能为空");
    		}
    		SysNewsHealth info = newsHealthService.get(id);
    		SysNewsCategory catInfo = newsCategoryService.get(info.getNewsCatId());
    		info.setNewsCatName(catInfo.getName());
    		SysUser user = userService.get(info.getCreateUser());
    		info.setCreateUser(user.getName());
    		return jsonResult(info);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
}
