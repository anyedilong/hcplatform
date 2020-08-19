package com.java.moudle.physicalExamination.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.physicalExamination.domain.BltTjBaseData;
import com.java.moudle.physicalExamination.domain.BltTjInfo;
import com.java.moudle.physicalExamination.service.PhysicalCruxService;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.until.StringUntil;
import com.java.until.SysUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.DictDto;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@RequestMapping("tjgjsj")
@RestController
public class PhysicalCruxController extends BaseController{

	@Inject
	private PhysicalCruxService service;
	
	/**
	 * 添加或者更新关键数据
	 */
	@RequestMapping("save")
	public JsonResult addOrUpdateGjData() {
		LoginInfoDto sysUser = SysUtil.sysUser(request, response);
		String param = getParam(request);
		JSONObject input = JSON.parseObject(param);
		System.out.println(param);
		service.addOrUpdateGjData(sysUser, input);
		return jsonResult();
	}
	
	/**
	 * 查询二级关键数据列表
	 * @throws CloneNotSupportedException 
	 */
	@RequestMapping("queryBaseGjData")
	public JsonResult queryBaseGjData() throws CloneNotSupportedException {
		String param = getParam(request);
		JSONObject input = JSON.parseObject(param);
		List<BltTjBaseData> data = service.queryBaseGjData(input);
		for (BltTjBaseData baseData: data) {
			List<BltTjBaseData> newData = service.getBaseDataInfoByParentId(baseData.getId());
			baseData.setNextData(newData);
		}
		return jsonResult(data);
	}
	
	/**
	 * 查询已选中的关键数据
	 */
	@RequestMapping("queryGjInfo")
	public JsonResult queryGjInfo() throws CloneNotSupportedException {
		LoginInfoDto sysUser = SysUtil.sysUser(request, response);
		String param = getParam(request);
		JSONObject input = JSON.parseObject(param);
		if (!input.containsKey("rqlx") || StringUntil.isNull(input.get("rqlx").toString())) {
			return jsonResult(null, 9001,"rqlx不能为空");
		}
		String rqlx = input.get("rqlx").toString();
		BltTjInfo data = service.queryGjInfo(sysUser.getOrgName(), rqlx);
		return jsonResult(data);
	}
	
	/**
	 * 查询人群类型数据
	 */
	@RequestMapping("getRqlx")
	public JsonResult getRqlx() {
		List<DictDto> array = service.getRqlx();
		return jsonResult(array);
	}
}
