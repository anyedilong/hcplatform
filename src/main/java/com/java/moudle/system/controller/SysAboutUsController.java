package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysAboutUs;
import com.java.moudle.system.service.SysAboutUsService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 关于我们管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/aboutus")
public class SysAboutUsController extends BaseController {
    
	@Inject
    private SysAboutUsService aboutUsService;

	@RequestMapping("queryInfo")
	public JsonResult queryInfo() {
		try {
			SysAboutUs info = aboutUsService.queryInfo();
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
			SysAboutUs aboutUs = JSONObject.parseObject(param, SysAboutUs.class);
			System.out.println(param);
			if(StringUntil.isNull(aboutUs.getId())) {
				aboutUs.setId(UUIDUtil.getUUID());
			}
			aboutUsService.save(aboutUs);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
