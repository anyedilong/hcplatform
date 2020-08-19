package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysRoleFunctionRepository;
import com.java.moudle.system.domain.SysRoleFunction;
import com.java.until.dba.BaseDao;

import javax.inject.Named;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 16:48
 **/
@Named
public class SysRoleFunctionDao extends BaseDao<SysRoleFunctionRepository, SysRoleFunction> {
    public void delByRoleId(String roleId) {
        repository.delByRoleId(roleId);
    }

    public List<SysRoleFunction> getSysRoleFunctionOfRoleId(String roleId) {
        return repository.getSysRoleFunctionOfRoleId(roleId);
    }
}
