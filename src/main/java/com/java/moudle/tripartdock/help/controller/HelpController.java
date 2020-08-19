package com.java.moudle.tripartdock.help.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.help.service.HelpService;

@RequestMapping("/help")
@RestController
public class HelpController extends BaseController{
	
	@Autowired
	private HelpService helpService;
	
	/**
	 * 获取问题列表
	 */
	@RequestMapping("getCommonProblem")
	public JsonResult getCommonProblem() {
		try {
			return jsonResult(helpService.getCommonProblem());
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(e.toString(), -1, "异常错误");
		}
	}
}
