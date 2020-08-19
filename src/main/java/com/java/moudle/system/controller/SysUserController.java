package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.OrganizationUserDto;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;
import com.java.until.ras.BCrypt;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 用户管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/1/2 9:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/user")
public class SysUserController  extends BaseController {
    
	@Inject
    private SysUserService userService;


    /**
     * @Description:查询用户的详情 
     * @param @return
     * @return String
     * @throws
     */
    @RequestMapping("show")
    public JsonResult show(){
    	try{
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		
    		String username = paramObj.getString("username");
    		
    		if(StringUntil.isNull(username)) {
    			return jsonResult(null, 10000, "请选择查询的数据");
    		}
    		SysUser info = userService.queryInfoByCon("", username);
    		return jsonResult(info);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}

    /**
     * @Description: 获取后台管理用户
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getLoginInfo")
    public JsonResult getLoginInfo() {
    	try {
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user != null) {
				user.setPassword("");
				user.setAuthorities("");
			}
    		return jsonResult(user);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }

    /**
     * @Description 查询机构用户列表
     * @Author zw
     * @Date 09:16 2020-03-17
     * @Paramr
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("queryOrganizationUser")
    public JsonResult queryOrganizationUser () {
		String param = getParam(request);
		logger.info("queryOrganizationUser查询机构用户入参:[{}]", param);
		OrganizationUserDto dto = JSON.parseObject(param, OrganizationUserDto.class);
		PageModel pageModel = new PageModel();
//		if (StringUtils.isBlank(dto.getUserId())) {
//			return jsonResult(null, 9001, "当前用户id不能为空");
//		}
		LoginInfoDto user = SysUtil.sysUser(request, response);
		if (user == null) {
			return jsonResult(null, 9001, "请先登录");
		}
		dto.setUserId(user.getId());
		
		if (dto.getPageNo() != 0) {
			pageModel.setPageNo(dto.getPageNo());
		}
		if (dto.getPageSize() != 0) {
			pageModel.setPageSize(dto.getPageSize());
		}
		userService.queryOrganizationUser(dto, pageModel);
		logger.info("queryOrganizationUser查询机构用户出参:[{}]", JSON.toJSONStringWithDateFormat(pageModel, "yyyy-MM-dd"));
    	return jsonResult(pageModel);
	}

	/**
	 * @Description 新增机构用户
	 * @Author zw
	 * @Date 10:48 2020-03-17
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("addOrganizationUser")
	public JsonResult addOrganizationUser () {
		String param = getParam(request);
		logger.info("addOrganizationUser新增机构用户入参:[{}]", param);
		SysUser sysUser = JSON.parseObject(param, SysUser.class);
		if (StringUntil.isBlank(sysUser.getOrgName())) {
			return jsonResult(null, 9001, "所在机构不能为空");
		}
		if (StringUntil.isBlank(sysUser.getRoleId())) {
			return jsonResult(null, 9001, "角色id不能为空");
		}
		if (StringUntil.isBlank(sysUser.getName())) {
			return jsonResult(null, 9001, "姓名不能为空");
		}

		// 0:新增 1:修改
		int addOrupdate = 0;
		if (StringUtils.isNotBlank(sysUser.getId())) {
			addOrupdate = 1;
		}
		JsonResult jsonResult = userService.addOrganizationUser(sysUser, addOrupdate);
		logger.info("addOrganizationUser新增机构用户出参:[{}]", JSON.toJSONString(jsonResult));
		return jsonResult;
	}

	/**
	 * 用户更改自己密码
	 * 李家峰
	 */
	@RequestMapping("changePassword")
	public JsonResult changePassword() {
		try {
			String param = getParam(request);
			logger.info("changePassword用户更改自己密码入参:[{}]", param);
			JSONObject input = JSON.parseObject(param);
			LoginInfoDto sysUser = SysUtil.sysUser(request, response);
			if (!input.containsKey("newPsd") && !"".equals(input.get("newPsd").toString())) {
				return jsonResult(null, 9001, "newPsd不能为空");
			}
			if (!input.containsKey("oldPsd") && !"".equals(input.get("oldPsd").toString())) {
				return jsonResult(null, 9001, "oldPsd不能为空");
			}
			String oldPsd = input.get("oldPsd").toString();
			String newPsd = input.get("newPsd").toString();
			if (BCrypt.checkpw(oldPsd, sysUser.getPassword())) {
				SysUser user = new SysUser();
				user.setId(sysUser.getId());
				user.setPassword(newPsd);
				userService.resetPassword(user);
				return jsonResult();
			} else {
				return jsonResult(null, 9001, "原密码输入错误");
			}
		} catch (Exception e) {
			return jsonResult(null, 9001, "异常错误");
		}
	}
	
	/**
	 * @Description 机构用户重置密码
	 * @Author zw
	 * @Date 14:03 2020-03-17
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("resetPassword")
	public JsonResult resetPassword () {
		String param = getParam(request);
		logger.info("resetPassword机构用户重置密码入参:[{}]", param);
		SysUser sysUser = JSON.parseObject(param, SysUser.class);
		if (StringUntil.isBlank(sysUser.getPassword())) {
			return jsonResult(null, 9001, "密码不能为空");
		}
		if (StringUntil.isBlank(sysUser.getId())) {
			return jsonResult(null, 9001, "id不能为空");
		}
		JsonResult jsonResult = userService.resetPassword(sysUser);
		logger.info("resetPassword机构用户重置密码出参:[{}]", JSON.toJSONString(jsonResult));
		return jsonResult;
	}

	/**
	 * @Description 启用停用用户
	 * @Author zw
	 * @Date 17:49 2020-03-17
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("updateUserStatus")
	public JsonResult updateUserStatus () {
		String param = getParam(request);
		logger.info("updateUserStatus启用停用用户入参:[{}]", param);
		SysUser sysUser = JSON.parseObject(param, SysUser.class);
		if (StringUntil.isBlank(sysUser.getStatus())) {
			return jsonResult(null, 9001, "状态不能为空");
		}
		if (StringUntil.isBlank(sysUser.getId())) {
			return jsonResult(null, 9001, "id不能为空");
		}
		JsonResult jsonResult = userService.updateUserStatus(sysUser);
		logger.info("updateUserStatus启用停用用户出参:[{}]", jsonResult);
		return jsonResult;
	}

}
