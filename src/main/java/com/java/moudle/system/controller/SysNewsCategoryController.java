package com.java.moudle.system.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.moudle.system.service.SysNewsCategoryService;
import com.java.moudle.system.service.SysNewsHealthInspectService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 健康咨询类别管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/lb")
public class SysNewsCategoryController extends BaseController {
    
	@Inject
    private SysNewsCategoryService newsCategoryService;
	@Inject
    private SysNewsHealthInspectService newsHealthInspectService;

	/**
	 * @Description: 查询资讯类别的list(网站端)
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getNewsCategoryList")
	public JsonResult getNewsCategoryList() {
		try {
			List<SysNewsCategory> list = newsCategoryService.getNewsCategoryList();
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查询资讯类别的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("list")
	public JsonResult list() {
		try {
			String param = getParam(request);
			SysNewsCategory newsCat = JSONObject.parseObject(param, SysNewsCategory.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
			newsCategoryService.getNewsCategoryPage(newsCat, page);
			return jsonResult(page);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查询资讯类别的详情
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
			SysNewsCategory info = newsCategoryService.get(id);
			return jsonResult(info);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 校验资讯类别参数
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("valiNewsCat")
	public JsonResult valiNewsCat() {
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			String name = paramObj.getString("name");
			String orderNum = paramObj.getString("orderNum");
			if(StringUntil.isNull(name)) {
				return jsonResult(null, 10000, "类别名称不能为空");
			}
			if(StringUntil.isNull(orderNum)) {
				return jsonResult(null, 10000, "排序不能为空");
			}
			//资讯类别个数不能超过10
			if(StringUntil.isNull(id)) {
				int count = newsCategoryService.getNewCatByCon("", "", "");
				if(count >= 10) {
					return jsonResult(null, 10000, "资讯类别个数不能超过10个!");
				}
			}
			//查询资讯类别名称是否被占用
			int count = newsCategoryService.getNewCatByCon(id, name, "");
			if(count > 0) {
				return jsonResult(null, 10000, "您输入的类别信息和已有类别信息重复，请重新输入！");
			}
			//查询资讯类别排序是否被占用
			int count1 = newsCategoryService.getNewCatByCon(id, "", orderNum);
			if(count1 > 0) {
				return jsonResult(null, 0, "该排序位置已被占用，是否要继续使用该排序位置？");
			}
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 保存资讯类别
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("category")
	public JsonResult saveNewsCat() {
		try {
			String param = getParam(request);
			SysNewsCategory newsCat = JSONObject.parseObject(param, SysNewsCategory.class);
			if(StringUntil.isNull(newsCat.getName())) {
				return jsonResult(null, 10000, "类别名称不能为空");
			}
			if(StringUntil.isNull(newsCat.getOrderNum())) {
				return jsonResult(null, 10000, "排序不能为空");
			}
			newsCategoryService.saveNewsCat(newsCat);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 删除资讯类别
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("delcategory")
	public JsonResult delNewsCat() {
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "标识不能为空");
			}
			int count = newsHealthInspectService.getCountByCatId(id);
			if(count == 0) {
				SysNewsCategory info = newsCategoryService.get(id);
				info.setStatus("1");
				newsCategoryService.save(info);
				return jsonResult();
			}else {
				return jsonResult(null, 10000, "该资讯类别下存在资讯，不能删除！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
