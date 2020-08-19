package com.java.moudle.system.service.impl;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysFunctionDao;
import com.java.moudle.system.dao.SysRoleDao;
import com.java.moudle.system.dao.SysUserDao;
import com.java.moudle.system.dao.SysUserRoleDao;
import com.java.moudle.system.domain.SysFunction;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.domain.SysUserRole;
import com.java.moudle.system.dto.OrganizationUserDto;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import com.java.until.ras.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named
@Transactional(readOnly=false)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

	@Inject
	private SysUserRoleDao sysUserRoleDao;

	@Inject
	private SysRoleDao sysRoleDao;

	@Inject
	private SysUserDao sysUserDao;

	@Inject
	private SysFunctionDao sysFunctionDao;

	@Override
	public SysUser queryInfoByCon(String id, String username) {
		SysUser sysUser = dao.queryInfoByCon("", username);
		//sysUser.setPassword(null);
		// 根据用户id查询角色信息
		if(sysUser != null) {
			SysRole sysRole = sysRoleDao.getRoleInfoByUserId(sysUser.getId());
			if (sysRole != null) {
				sysUser.setRoleId(sysRole.getId());
			}
		}
		return sysUser;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void queryOrganizationUser(OrganizationUserDto dto, PageModel pageModel) {
		// 出参
		List<OrganizationUserDto> dtoList = new ArrayList<>();

		// 查询用户信息
		sysUserDao.queryUsers(dto, pageModel);
		List<SysUser> sysUsers = pageModel.getList();

		// 查询每个用户的所有角色
		if (sysUsers != null) {
			sysUsers.forEach(sysUser -> {
				// 封装返回值
				OrganizationUserDto organizationUserDto = new OrganizationUserDto();
				organizationUserDto.setId(sysUser.getId());
				organizationUserDto.setPhone(sysUser.getPhone());
				organizationUserDto.setName(sysUser.getName());
				organizationUserDto.setStatus(sysUser.getStatus());
				organizationUserDto.setUserName(sysUser.getUsername());
				organizationUserDto.setCreationTime(sysUser.getCreationTime());

				// 查询角色
				List<SysRole> sysRoles = sysRoleDao.getUserRole(sysUser);

				// 根据每个角色查询所有的权限
				StringBuffer sb = new StringBuffer();
				if (sysRoles != null) {
					organizationUserDto.setRoleName(sysRoles.get(0).getRoleName());
					sysRoles.forEach(sysRole -> {
						SysFunction sysFunction = sysFunctionDao.getRoleFunction(sysRole);
						sb.append(sysFunction.getName() + ",");
					});
				}
				// 去除最后一个逗号
				String role = sb.length() == 0 ? "" : sb.toString().substring(0, sb.toString().lastIndexOf(","));
				organizationUserDto.setRole(role);

				dtoList.add(organizationUserDto);
			});
		}

		pageModel.setList(dtoList);
	}

	@Override
	public JsonResult addOrganizationUser(SysUser sysUser, int addOrupdate) {

		// 新增
		if (addOrupdate == 0) {
			if (StringUntil.isBlank(sysUser.getUsername())) {
				return new JsonResult(null, 9001, "用户名不能为空");
			}
			if (StringUntil.isBlank(sysUser.getPassword())) {
				return new JsonResult(null, 9001, "密码不能为空");
			}
			if (StringUntil.isBlank(sysUser.getParentId())) {
				return new JsonResult(null, 9001, "当前用户id不能为空");
			}


			// 判断用户名是否重复
			SysUser sysUser1 = sysUserDao.queryInfoByCon("", sysUser.getUsername());
			if (sysUser1 != null) {
				return new JsonResult(null, 9001, "您输入的用户名重复，请重新输入");
			}

			// 添加机构用户
			String uuid = UUIDUtil.getUUID();
			sysUser.setId(uuid);

			// 密码加密
			sysUser.setPassword(BCrypt.hashpw(sysUser.getPassword(), BCrypt.gensalt()));

			// 状态默认为0 正常
			sysUser.setStatus("0");

			// 创建时间
			sysUser.setCreationTime(new Date());

			// 去除前导尾部空白
			sysUser.setOrgName(sysUser.getOrgName().trim());

			// 保存
			sysUserDao.save(sysUser);

			// 分配用户角色 根据角色id查询角色id
			SysRole sysRole = sysRoleDao.get(sysUser.getRoleId());
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setId(UUIDUtil.getUUID());
			sysUserRole.setUserId(sysUser.getId());
			sysUserRole.setRoleId(sysRole.getId());

			// 保存用户角色关联信息
			sysUserRoleDao.save(sysUserRole);
		} else {

			// 修改机构用户
			sysUserDao.updateSysUserById(sysUser);

			// 修改角色
			if (StringUtils.isNotBlank(sysUser.getRoleId())) {
				// 先删除用户之前的角色关联信息
				sysUserRoleDao.delUserRoleOfSysUserId(sysUser.getId());

				// 然后再添加新的角色
				// 分配用户角色 根据角色id查询角色id
				SysRole sysRole = sysRoleDao.get(sysUser.getRoleId());
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setId(UUIDUtil.getUUID());
				sysUserRole.setUserId(sysUser.getId());
				sysUserRole.setRoleId(sysRole.getId());

				// 保存用户角色关联信息
				sysUserRoleDao.save(sysUserRole);
			}
		}

		return new JsonResult();
	}

	@Override
	public JsonResult resetPassword(SysUser sysUser) {
		// 密码加密
		sysUser.setPassword(BCrypt.hashpw(sysUser.getPassword(), BCrypt.gensalt()));
		sysUserDao.resetPassword(sysUser);
		return new JsonResult();
	}

	@Override
	public JsonResult updateUserStatus(SysUser sysUser) {
		sysUserDao.updateUserStatus(sysUser);
		return new JsonResult();
	}

	@Override
	public SysUser getUserInfoByName(String username) {
		return dao.getUserInfoByName(username);
	}

	@Override
	public List<String> getUserChilds(String userId, String roleType) {
		List<String> list = new ArrayList<>();
		//超级管理员不参与条件判断；管理员查询出旗下的子用户；普通用户就是其本身
		if("2".equals(roleType)) {
			list = dao.getChildsByUserId(userId);
		}else if("3".equals(roleType)) {
			list.add(userId);
		}
		return list;
	}

}
