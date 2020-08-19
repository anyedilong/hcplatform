package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.java.moudle.system.dao.repository.SysUserRepository;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.OrganizationUserDto;
import com.java.moudle.system.dto.SysUserDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysUserDao extends BaseDao<SysUserRepository, SysUser> {

	public SysUser getUserInfoByName(String username) {
		Map<String, Object> map = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_user u ");
		sql.append(" where u.username = :username ");
		map.put("username", username);
		return queryOne(sql.toString(), map, SysUser.class);
	}
	
	public SysUser queryInfoByCon(String id, String username) {
		Map<String, Object> map = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_user u ");
		sql.append(" where u.status = '0' ");
		if(!StringUntil.isNull(id)) {
			sql.append(" and u.id = :id ");
			map.put("id", id);
		}
		if(!StringUntil.isNull(username)) {
			sql.append(" and u.username = :username ");
			map.put("username", username);
		}
		return queryOne(sql.toString(), map, SysUser.class);
	}

	public SysUserDto getUserInfo(SysUserDto dto) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select u.*,r.role_type ");
		sql.append(" from sys_user u ");
		sql.append(" join sys_user_role ur on ur.user_id = u.id ");
		sql.append(" join sys_role r on r.id = ur.role_id ");
		sql.append(" where r.status = '0' ");
		if(StringUtils.isNotBlank(dto.getId())) {
			sql.append(" and u.id = :id ");
		}
		return queryOne(sql.toString(), dto, SysUserDto.class);
	}

	public void queryUsers(OrganizationUserDto dto, PageModel pageModel) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_user where parent_Id = :userId ");
		if (StringUntil.isNotBlank(dto.getUserName())) {
			sql.append(" and USERNAME like CONCAT('%',CONCAT(:userName,'%')) ");
		}
		if (dto.getStartCreationTime() != null) {
			sql.append(" and CREATION_TIME >= :startCreationTime ");
		}
		if (dto.getEndCreationTime() != null) {
			sql.append(" and CREATION_TIME <= :endCreationTime ");
		}
		if (StringUntil.isNotBlank(dto.getStatus())) {
			sql.append(" and status = :status ");
		}
		sql.append(" order by CREATION_TIME desc ");
		queryPageList(sql.toString(), dto, pageModel, SysUser.class);
	}

	public void resetPassword(SysUser sysUser) {
		repository.resetPassword(sysUser);
	}

	public void updateUserStatus(SysUser sysUser) {
		repository.updateUserStatus(sysUser);
	}

	public void updateSysUserById(SysUser sysUser) {
		repository.updateSysUserById(sysUser);
	}
	
	public List<String> getChildsByUserId(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.id ");
		sql.append(" from sys_user r ");
		sql.append(" where (r.parent_id = :userId  or r.id = :userId) ");
		sql.append(" and r.status = '0' ");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		return queryList(sql.toString(), paramMap, String.class);
	}
}
