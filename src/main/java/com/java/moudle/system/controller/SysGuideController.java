package com.java.moudle.system.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysGuide;
import com.java.moudle.system.domain.SysGuideInspect;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.SysGuideDto;
import com.java.moudle.system.service.SysGuideService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.SysUtil;
import com.java.until.ToJavaUtils;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 办事指南管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/1/2 9:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/guide")
public class SysGuideController extends BaseController {
    
	@Inject
    private SysGuideService guideService;
	@Inject
    private SysUserService userService;

	/**
	 * @Description: 查询办事指南列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getGuidePage")
    public JsonResult getGuidePage() {
    	try {
    		String param = getParam(request);
    		SysGuide guide = JSONObject.parseObject(param, SysGuide.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		
    		guideService.getGuidePage(guide, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
	/**
	 * 查询办事指南审核表
	 */
	@RequestMapping("getGuideInpectPage")
    public JsonResult getGuideInpectPage() {
    	try {
    		String param = getParam(request);
    		SysGuideInspect guide = JSONObject.parseObject(param, SysGuideInspect.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		
    		//权限判断
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		if(user != null) {
    			List<String> childs = userService.getUserChilds(user.getId(), user.getRole().getRoleType());
    			guide.setUserIds(childs);
    			guide.setRoleType(user.getRole().getRoleType());
    		}else {
    			return jsonResult(null, 10000, "请先进行登录操作！");
    		}
    		
    		guideService.getGuideInpectPage(guide, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
	
    /**
     * @Description:查询办事指南的详情 
     * @param @return
     * @return String
     * @throws
     */
    @RequestMapping("show")
    public JsonResult show(){
    	try{
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
    		if(StringUntil.isNull(id)) {
    			return jsonResult(null, 10000, "参数id不能为空");
    		}
    		SysGuide info = guideService.getGuideInfo(id);
    		return jsonResult(info);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * @Description:查询办事指南的详情-管理端 
     * @param @return
     * @return String
     * @throws
     */
    @RequestMapping("showAdmin")
    public JsonResult showAdmin(){
    	try{
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
    		if(StringUntil.isNull(id)) {
    			return jsonResult(null, 10000, "参数id不能为空");
    		}
    		SysGuideInspect info = guideService.getGuideInfoAdmin(id);
    		return jsonResult(info);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * 添加/更新 办事指南
     */
    @RequestMapping("save")
    public JsonResult addGuildeInfo(SysGuideDto guideInfo) {
    	try{
    		LoginInfoDto sysUser = SysUtil.sysUser(request, response);
    		if (StringUntil.isNull(guideInfo.getGuideType())) {
    			return jsonResult("参数guideType不能为空", 10000, "请选择办事指南类型！");
    		}
    		if (StringUntil.isNull(guideInfo.getTitle())) {
    			return jsonResult("参数title不能为空", 10000, "标题不能为空！");
    		}
    		if (StringUntil.isNull(guideInfo.getContent())) {
    			return jsonResult("参数content不能为空", 10000, "办事指南内容不能为空！");
    		}
    		guideInfo.setCreateUser(sysUser.getId());
    		int result = guideService.addGuildeInspectInfo(guideInfo);
    		if (result == 0) {
        		return jsonResult();
    		} else {
        		return jsonResult(null, 9001, "办事指南标题重复，请重新输入！");
    		}
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
    }
    
    /**
     * 办事指南状态 2审核通过 3发表 5退回 6撤回
     */
    @RequestMapping("status")
    public JsonResult status() {
    	try{
    		String param = getParam(request);
    		JSONObject input = JSON.parseObject(param);
    		System.out.println(input);
    		String id = input.get("id").toString();
    		String status = input.get("status").toString();

			// 查询审核表信息
			SysGuideInspect guideInspect = guideService.getGuideInspectInfo(id);
			// 1 提交
    		if ("1".equals(status)) {
    			
    			// 先更改这个审核表的状态
    			guideInspect.setStatus("1");
    			guideService.updateInspectInfo(guideInspect);

        	// 2.审核通过
    		} else if ("2".equals(status)) {
    			
    			// 先更改这个审核表的状态
    			guideInspect.setStatus("2");
    			guideService.updateInspectInfo(guideInspect);
    		
    		// 3.发表
    		} else if ("3".equals(status)){

    			guideInspect.setStatus("3");
    			guideService.updateInspectInfo(guideInspect);
    			
    			// 再往发布表添加一条数据
    			SysGuide guideInfo = new SysGuide();
    			ToJavaUtils.copyFields(guideInspect, guideInfo);
    			guideInfo.setPubTime(new Date()); // 发布时间
    			guideService.addGuildeInfo(guideInfo);
    			
    		// 5.退回
    		}else if ("5".equals(status)) {
    			
        		String remarks = "";
        		if (input.containsKey("remarks")) {
        			remarks = input.get("remarks").toString();
        		}
    			guideInspect.setStatus("5");
    			guideInspect.setRemarks(remarks);
    			guideService.updateInspectInfo(guideInspect);
    			
    		// 6.撤回
    		} else if ("6".equals(status)) {
    			
    			// 先修改审核表信息
    			guideInspect.setStatus("6");
    			guideService.updateInspectInfo(guideInspect);
    			// 再删除发布表的信息
    			guideService.delete(id);
    		} else {
        		return jsonResult(null, -1, "status错误");
    		}
    		return jsonResult();
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
    }
    
    /**
     * 删除办事指南
     */
    @RequestMapping("delete")
    public JsonResult delete() {
    	try{
    		String param = getParam(request);
    		JSONObject input = JSON.parseObject(param);
    		String id = input.get("id").toString();

			// 查询审核表信息
			SysGuideInspect guideInspect = guideService.getGuideInspectInfo(id);
			
    		// 先修改审核表删除状态
			guideInspect.setDeleteFlg("1");
			guideService.updateInspectInfo(guideInspect);
			
			// 再删除发布表的信息
			SysGuide guide = guideService.get(id);
			if (guide != null) {
				guideService.delete(guide);
			}
			
    		return jsonResult();
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
    }
}
