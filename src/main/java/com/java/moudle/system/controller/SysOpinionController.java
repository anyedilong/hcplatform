package com.java.moudle.system.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysOpinion;
import com.java.moudle.system.service.SysOpinionService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 咨询投诉管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/opinion")
public class SysOpinionController extends BaseController {
    
	@Inject
    private SysOpinionService opinionService;

	@RequestMapping("save")
	public JsonResult save() {
		try {
			String param = getParam(request);
			SysOpinion opinion = JSONObject.parseObject(param, SysOpinion.class);
			if(StringUntil.isNull(opinion.getId())) {
				opinion.setId(UUIDUtil.getUUID());
				opinion.setCreateDate(new Date());
			}
			opinionService.save(opinion);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
