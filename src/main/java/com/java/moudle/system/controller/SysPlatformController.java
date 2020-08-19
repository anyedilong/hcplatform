package com.java.moudle.system.controller;

import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.java.moudle.system.dto.SaveWzszDto;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysPlatform;
import com.java.moudle.system.service.SysPlatformService;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 平台信息管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/web")
public class SysPlatformController  extends BaseController {
    
	@Inject
    private SysPlatformService platformService;

	
	@RequestMapping("xq")
	public JsonResult show() {
		try {
			SysPlatform plat = platformService.getPlatDetail();
			return jsonResult(plat);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

	/**
	 * @Author zw
	 * @Description 机构管理端网站设置
	 * @Date 08:47 2020-03-11
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("save")
	public JsonResult save (SaveWzszDto dto) {
		logger.info("save机构管理端网站设置入参:[{}]", "id: " + dto.getId() + " 欢迎词: " + dto.getWelcomeContent() + "logo是否上传: " + (dto.getLogo() == null ? "未上传" : "已上传"));
		// id是否上传 0 未上传 1已上传
		int isId = 0;
		if (StringUntil.isNull(dto.getId())) {
			dto.setId(UUIDUtil.getUUID());
		} else {
			isId = 1;
		}
		if (StringUntil.isBlank(dto.getWelcomeContent())) {
			return jsonResult(null, 9001, "welcomeContent首页欢迎词不能为空");
		}
		JsonResult jsonResult = platformService.siteSettings(dto, isId);
		logger.info("save机构管理端网站设置出参:[{}]", JSON.toJSONString(jsonResult));
		return jsonResult;
	}
	

}
