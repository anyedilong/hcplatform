package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysFunction;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.dto.MeunAnniuDto;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.until.dba.PageModel;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:23
 **/
public interface SysRoleService {
    List<SysFunction> queryRoleFunction(String roleId);

    List<SysRole> getAllRole(String userId);

    JsonResult roleAuthorization(MeunAnniuDto meunAnniuDto);

    JsonResult deleteRole(String roleId);

    JsonResult addRole(SysRole sysRole);

    List<SysFunction> queryRoleSomeFunctions(String userId);

    void getRoleList(SysRoleDto dto, PageModel pageModel);
    
    //查询用户的角色信息
    SysRole getRoleInfoByUserId(String userId);
}
