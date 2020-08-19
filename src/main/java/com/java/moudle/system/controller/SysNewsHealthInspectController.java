package com.java.moudle.system.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.moudle.system.domain.SysNewsHealthInspect;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.service.SysNewsCategoryService;
import com.java.moudle.system.service.SysNewsHealthInspectService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 健康咨讯审核管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/information")
public class SysNewsHealthInspectController  extends BaseController {
    
	@Inject
    private SysNewsHealthInspectService newsHealthInspectService;
	@Inject
    private SysNewsCategoryService newsCategoryService;
	@Inject
    private SysUserService userService;

	/**
	 * @Description: 查询资讯列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("list")
	public JsonResult getNewsHealthpage() {
		try {
			String param = getParam(request);
			SysNewsHealthInspect newsHealth = JSONObject.parseObject(param, SysNewsHealthInspect.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		
    		//权限判断
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		if(user != null) {
    			List<String> childs = userService.getUserChilds(user.getId(), user.getRole().getRoleType());
    			newsHealth.setUserIds(childs);
    			newsHealth.setRoleType(user.getRole().getRoleType());
    		}else {
    			return jsonResult(null, 10000, "请先进行登录操作！");
    		}
    		
			newsHealthInspectService.getNewsHealthPage(newsHealth, page);
			return jsonResult(page);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 保存资讯信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("save")
	public JsonResult saveNewsHealth(SysNewsHealthInspect newsHealth) {
		try {
			//String param = getParam(request);
			//SysNewsHealthInspect newsHealth = JSONObject.parseObject(param, SysNewsHealthInspect.class);
    		if(StringUtils.isNull(newsHealth.getNewsTitle())) {
				return jsonResult(null, 10000, "请输入咨讯标题！");
			}
    		if(StringUtils.isNull(newsHealth.getNewsLy())) {
				return jsonResult(null, 10000, "请输入资讯来源！");
			}
    		if(StringUtils.isNull(newsHealth.getNewsContent())) {
				return jsonResult(null, 10000, "请输入资讯内容！");
			}
    		if(StringUtils.isNull(newsHealth.getNewsCatId())) {
				return jsonResult(null, 10000, "请输入资讯类别！");
			}
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		newsHealth.setCreateUser(user.getId());
    		Map<String, String> resultMap = newsHealthInspectService.saveNewsHealthDetail(newsHealth);
    		int code = Integer.parseInt(resultMap.get("code"));
			String msg = resultMap.get("msg");
			return jsonResult(null, code, msg);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 查询资讯的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("xq")
	public JsonResult getNewsHealthDetail() {
		try {
			String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
	   		if(StringUtils.isNull(id)) {
				return jsonResult(null, 10000, "缺少参数：标识");
			}
			SysNewsHealthInspect info = newsHealthInspectService.get(id);
			if (info == null) {
				return jsonResult(null, 9001, "查询内容不存在");
			}
			SysNewsCategory cat = newsCategoryService.get(info.getNewsCatId());
			info.setCatName(cat.getName());
			return jsonResult(info);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 更新资讯的状态
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("status")
	public JsonResult updateNewsHealthStatus() {
		try {
			String param = getParam(request);
			SysNewsHealthInspect newsHealth = JSONObject.parseObject(param, SysNewsHealthInspect.class);
	   		if(StringUtils.isNull(newsHealth.getId())) {
				return jsonResult(null, 10000, "缺少参数：标识");
			}
    		if(StringUtils.isNull(newsHealth.getStatus())) {
				return jsonResult(null, 10000, "缺少参数：状态");
			}
	   		Map<String, String> resultMap = newsHealthInspectService.updateNewsHealthStatus(newsHealth);
	   		int code = Integer.parseInt(resultMap.get("code"));
	   		String msg = resultMap.get("msg");
      		return jsonResult(null, code, msg);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * @Description: 删除资讯信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("delete")
	public JsonResult delete() {
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "标识不能为空");
			}
			SysNewsHealthInspect info = newsHealthInspectService.get(id);
			info.setDeleteFlg("1");
			newsHealthInspectService.save(info);
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

}
