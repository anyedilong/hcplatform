package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysFunctionRepository;
import com.java.moudle.system.domain.SysFunction;
import com.java.moudle.system.domain.SysRole;
import com.java.until.dba.BaseDao;

import javax.inject.Named;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:58
 **/
@Named
public class SysFunctionDao extends BaseDao<SysFunctionRepository, SysFunction> {

    public SysFunction getRoleFunction(SysRole sysRole) {
        StringBuffer sql = new StringBuffer();
        sql.append("select wm_concat(to_char(f.name)) name from sys_function f ");
        sql.append(" join sys_role_function rf on rf.function_id = f.id ");
        sql.append(" join sys_role r on r.id = rf.role_id ");
        sql.append(" where r.id = :id ");
        return queryOne(sql.toString(), sysRole, SysFunction.class);
    }

    public List<SysFunction> queryRoleFunction(String roleId) {
        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(roleId);
        StringBuffer sql = new StringBuffer();
        sql.append("select f.id id, f.name name, f.parent_id parent_Id, f.status status, f.type type, f.url, f.icon from sys_role r ");
        sql.append(" join sys_role_function rf on rf.role_id = r.id ");
        sql.append(" join sys_function f on f.id = rf.function_id ");
        sql.append(" where (f.status = '0' or f.status is null) ");
        sql.append(" and r.id = :id ");

        return queryList(sql.toString(), sysFunction, SysFunction.class);
    }

    public List<SysFunction> getSysFunctionListByFunctionId(String functionId) {

        return repository.getSysFunctionListByFunctionId(functionId);
    }
}
