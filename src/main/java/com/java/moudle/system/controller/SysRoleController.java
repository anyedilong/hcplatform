package com.java.moudle.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysFunction;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.MeunAnniuDto;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.system.service.SysRoleService;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:21
 **/
@RestController
@RequestMapping("sys/role")
public class SysRoleController extends BaseController {

    @Inject
    private SysRoleService sysRoleService;

    /**
     * @Description 查询所有的角色
     * @Author zw
     * @Date 14:51 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getAllRole")
    public JsonResult getAllRole () {
        String param = getParam(request);
        logger.info("getAllRole查询该用户下所有的角色入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
//        if (!json.containsKey("userId") || StringUtils.isBlank(json.get("userId").toString())) {
//            return jsonResult(null, 9001, "用户id不能为空");
//        }
        LoginInfoDto user = SysUtil.sysUser(request, response);
		if (user == null) {
			return jsonResult(null, 9001, "请先登录");
		}
        List<SysRole> sysRoles = sysRoleService.getAllRole(user.getId());
        logger.info("getAllRole查询该用户下所有的角色出参:[{}]", JSON.toJSONString(sysRoles));
        return jsonResult(sysRoles);
    }

    /**
     * @Description 根据条件查询角色
     * @Author zw
     * @Date 14:51 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getRoleList")
    public JsonResult getRoleList () {
        String param = getParam(request);
        logger.info("getRoleList查询角色列表入参:[{}]", param);
        SysRoleDto dto = JSON.parseObject(param, SysRoleDto.class);
        PageModel pageModel = new PageModel();
//        if (StringUtils.isBlank(dto.getUserId())) {
//            return jsonResult(null, 9001, "用户id不能为空");
//        }
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
        sysRoleService.getRoleList(dto, pageModel);

        logger.info("getRoleList查询角色列表出参:[{}]", JSON.toJSONString(pageModel));
        return jsonResult(pageModel);
    }


    /**
     * @Description 授权角色权限
     * @Author zw
     * @Date 11:32 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("roleAuthorization")
    public JsonResult roleAuthorization () {
        String param = getParam(request);
        logger.info("roleAuthorization授权角色权限入参:[{}]", param);
        MeunAnniuDto meunAnniuDto = JSON.parseObject(param, MeunAnniuDto.class);
        if (StringUntil.isBlank(meunAnniuDto.getRoleId())) {
            return jsonResult(null, 9001, "角色id不能为空");
        }

        JsonResult jsonResult = sysRoleService.roleAuthorization(meunAnniuDto);

        logger.info("roleAuthorization授权角色权限出参:[{}]", JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description 查询角色的权限集合(包含没有权限的数据)
     * @Author zw
     * @Date 11:47 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("queryRoleFunction")
    public JsonResult queryRoleFunction () {
        String param = getParam(request);
        logger.info("queryRoleFunction查询角色权限集合入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("roleId") || StringUntil.isBlank(json.get("roleId").toString())) {
            return jsonResult(null, 9001, "角色id不能为空");
        }

        List<SysFunction> sysFunctionList = sysRoleService.queryRoleFunction(json.get("roleId").toString());
        logger.info("queryRoleFunction查询角色权限集合出参:[{}]", sysFunctionList);
        return jsonResult(sysFunctionList);
    }

    /**
     * @Description 删除角色
     * @Author zw
     * @Date 17:21 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("deleteRole")
    public JsonResult deleteRole() {
        String param = getParam(request);
        logger.info("deleteRole删除角色入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("roleId") || StringUntil.isBlank(json.get("roleId").toString())) {
            return jsonResult(null, 9001, "角色id不能为空");
        }

        JsonResult jsonResult = sysRoleService.deleteRole(json.get("roleId").toString());
        return jsonResult;
    }

    /**
     * @Description 新增角色
     * @Author zw
     * @Date 09:51 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("addRole")
    public JsonResult addRole () {
        String param = getParam(request);
        logger.info("addRole新增角色入参:[{}]", param);
        SysRole sysRole = JSON.parseObject(param, SysRole.class);
        if (StringUntil.isBlank(sysRole.getRoleCode())) {
            return jsonResult(null, 9001, "角色编码不能为空");
        }
        if (StringUntil.isBlank(sysRole.getRoleName())) {
            return jsonResult(null, 9001, "角色名称不能为空");
        }
        if (StringUtils.isBlank(sysRole.getRoleType())) {
            return jsonResult(null, 9001, "角色类型不能为空");
        }
        LoginInfoDto user = SysUtil.sysUser(request, response);
        if (user == null) {
            return jsonResult(null, 9001, "请先登陆");
        }
        sysRole.setUserId(user.getId());
        JsonResult jsonResult = sysRoleService.addRole(sysRole);
        logger.info("addRole新增角色出参:[{}]", jsonResult);
        return jsonResult;
    }

    /**
     * @Description 查询角色的权限集合(只查询含有的权限)
     * @Author zw
     * @Date 11:47 2020-03-17
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("queryRoleSomeFunctions")
    public JsonResult queryRoleSomeFunctions () {
        String param = getParam(request);
        logger.info("queryRoleSomeFunctions查询角色含有的权限集合入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("userId") || StringUntil.isBlank(json.get("userId").toString())) {
            return jsonResult(null, 9001, "用户id不能为空");
        }

        List<SysFunction> sysFunctionList = sysRoleService.queryRoleSomeFunctions(json.get("userId").toString());
        logger.info("queryRoleSomeFunctions查询角色含有的权限集合入参:[{}]查询角色权限集合出参:[{}]", sysFunctionList);
        return jsonResult(sysFunctionList);
    }

}
