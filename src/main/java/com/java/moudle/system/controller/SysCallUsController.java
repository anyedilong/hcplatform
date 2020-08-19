package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysCallUs;
import com.java.moudle.system.service.SysCallUsService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 联系人信息管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/callus")
public class SysCallUsController extends BaseController {
    
	@Inject
    private SysCallUsService callUsService;

	
	@RequestMapping("queryInfo")
	public JsonResult queryInfo() {
		try {
			SysCallUs info = callUsService.queryInfo();
			return jsonResult(info);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	@RequestMapping("save")
	public JsonResult save() {
		try {
			String param = getParam(request);
			SysCallUs callUs = JSONObject.parseObject(param, SysCallUs.class);
			if(StringUntil.isNull(callUs.getId())) {
				callUs.setId(UUIDUtil.getUUID());
			}
			callUsService.save(callUs);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
