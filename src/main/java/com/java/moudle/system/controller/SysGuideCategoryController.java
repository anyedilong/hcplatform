package com.java.moudle.system.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysGuideCategory;
import com.java.moudle.system.service.SysGuideCategoryService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysGuideCategoryController.java</p>
 * <p>Description : 办事指南类别管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/guidecat")
public class SysGuideCategoryController extends BaseController {
    
	@Inject
	private SysGuideCategoryService guideCategoryService;

	/**
	 * @Description: 查询办事指南类别的list(网站端)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getGuideCategoryList")
	public JsonResult getGuideCategoryList() {
		try {
			List<SysGuideCategory> list = guideCategoryService.getGuideCategoryList();
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查询办事指南类别的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("list")
	public JsonResult list() {
		try {
			String param = getParam(request);
			SysGuideCategory guideCat = JSONObject.parseObject(param, SysGuideCategory.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		guideCategoryService.getGuideCategoryPage(guideCat, page);
			return jsonResult(page);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查询办事指南类别的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("xq")
	public JsonResult getNewsCatDetail() {
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "标识不能为空");
			}
			SysGuideCategory info = guideCategoryService.get(id);
			return jsonResult(info);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 保存办事指南类别
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("save")
	public JsonResult saveNewsCat(SysGuideCategory guideCat) {
		try {
			//String param = getParam(request);
			//SysGuideCategory guideCat = JSONObject.parseObject(param, SysGuideCategory.class);
			if(StringUntil.isNull(guideCat.getName())) {
				return jsonResult(null, 10000, "类别名称不能为空");
			}
			if(StringUntil.isNull(guideCat.getOrderNum())) {
				return jsonResult(null, 10000, "排序不能为空");
			}
			Map<String, String> resultMap = guideCategoryService.saveGuideCat(guideCat);
			int code = Integer.parseInt(resultMap.get("code"));
			String msg = resultMap.get("msg");
			return jsonResult(null, code, msg);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 删除办事指南类别
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("delcategory")
	public JsonResult delGuideCat() {
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "标识不能为空");
			}
			int count = guideCategoryService.getCountByCatId(id);
			if(count == 0) {
				SysGuideCategory info = guideCategoryService.get(id);
				info.setStatus("1");
				guideCategoryService.save(info);
				return jsonResult();
			}else {
				return jsonResult(null, 10000, "该类别下存在办事指南，不能删除！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 获取类别的排序
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCategoryNum")
	public JsonResult getCategoryNum() {
		try {
			return jsonResult(guideCategoryService.getCategoryNum());
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
