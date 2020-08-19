package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.service.SysAppVersionService;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 关于我们管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/appversion")
public class SysAppVersionController extends BaseController {
    
	@Inject
    private SysAppVersionService appVersionService;

	@RequestMapping("queryInfo")
	public JsonResult queryInfo() {
		try {
			return jsonResult(appVersionService.queryNewlyInfo());
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	

}
