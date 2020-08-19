package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.OrganizationUserDto;
import com.java.until.dba.PageModel;

public interface SysUserService extends BaseService<SysUser> {
   
	SysUser getUserInfoByName(String username);
	
	SysUser queryInfoByCon(String id, String username);

    void queryOrganizationUser(OrganizationUserDto dto, PageModel pageModel);

    JsonResult addOrganizationUser(SysUser sysUser, int addOrupdate);

    JsonResult resetPassword(SysUser sysUser);

    JsonResult updateUserStatus(SysUser sysUser);

    //通过用户标识获取用户下管理的子用户集合
    List<String> getUserChilds(String userId, String roleType);
}
