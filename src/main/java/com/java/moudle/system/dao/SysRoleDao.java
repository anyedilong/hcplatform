package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysRoleRepository;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;

import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:06
 **/
@Named
public class SysRoleDao extends BaseDao<SysRoleRepository, SysRole> {

    public SysRole getSysRoleByCodeAndUserId(SysRole sysRole) {
        String sql = "select * from sys_role where ROLE_CODE = :roleCode and user_id = :userId and status = '0'";
        return queryOne(sql, sysRole, SysRole.class);
    }

    public List<SysRole> getUserRole(SysUser sysUser) {
        StringBuffer sql = new StringBuffer();
        sql.append("select r.id, r.role_code, r.role_name, r.status, r.role_type from sys_user u ");
        sql.append(" join sys_user_role ur on ur.user_id = u.id ");
        sql.append(" join sys_role r on r.id = ur.role_id ");
        sql.append(" where r.status = '0' and u.id = :id ");

        return queryList(sql.toString(), sysUser, SysRole.class);
    }

    public void getRoleList(SysRoleDto dto, PageModel pageModel) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from sys_role where status = '0' and role_type != '1' and user_id = :userId ");
        if (StringUntil.isNotBlank(dto.getRoleCode())) {
            sql.append(" and role_code like concat('%', concat(:roleCode, '%'))");
        }
        if (StringUntil.isNotBlank(dto.getRoleName())) {
            sql.append(" and role_name like concat('%', concat(:roleName, '%'))");
        }

        queryPageList(sql.toString(), dto, pageModel, SysRole.class);
    }

    public SysRole getRoleInfoByUserId(String userId) {
    	StringBuffer sql = new StringBuffer();
        sql.append(" select r.id, r.role_code, r.role_name, r.status, r.role_type ");
        sql.append(" from sys_user_role ur ");
        sql.append(" join sys_role r on r.id = ur.role_id ");
        sql.append(" where r.status = '0' and ur.user_id = :userId ");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        return queryOne(sql.toString(), paramMap, SysRole.class);
    }

    public List<SysRole> getSysRoleByUserId(String userId) {
        Map<String, String> map = new HashMap<>(16);
        map.put("userId", userId);
        StringBuffer sql = new StringBuffer();
        sql.append("select r.id, r.role_code, r.role_name, r.status, r.role_type from sys_user_role ur  ");
        sql.append(" join sys_role r on r.id = ur.role_id ");
        sql.append(" where ur.user_id = :userId");
        return queryList(sql.toString(), map, SysRole.class);
    }

    public void updateStatus(String id, String status) {
        repository.updateStatus(id, status);
    }

    public List<SysRole> getAllRole() {
        return repository.getAllRole("0");
    }

    public SysRole getSysRoleByNameAndUserId(SysRole sysRole) {
        return repository.getSysRoleByNameAndUserId(sysRole);
    }

    public SysRole getSysRoleOfUserId(String userId) {
        Map<String, String> map = new HashMap<>(16);
        map.put("userId", userId);
        StringBuffer sql = new StringBuffer();
        sql.append("select r.* from sys_user u ");
        sql.append(" join sys_user_role ur on ur.user_id = u.id ");
        sql.append(" join sys_role r on r.id = ur.role_id ");
        sql.append(" where u.id = :userId ");
        return queryOne(sql.toString(), map, SysRole.class);
    }

    public List<SysRole> getSysRoleByRoleType(String roleType) {
        return repository.getSysRoleByRoleType(roleType);
    }

    public List<SysRole> getSysRolesByUserId(String userId) {
        return repository.getSysRolesByUserId(userId);
    }
}
