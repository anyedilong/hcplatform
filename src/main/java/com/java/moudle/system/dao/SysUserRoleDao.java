package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysUserRoleRepository;
import com.java.moudle.system.domain.SysUserRole;
import com.java.until.dba.BaseDao;

import javax.inject.Named;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:03
 **/
@Named
public class SysUserRoleDao extends BaseDao<SysUserRoleRepository, SysUserRole> {
    public void delUserRoleOfSysUserId(String userId) {
        repository.delUserRoleOfSysUserId(userId);
    }

    public List<SysUserRole> getSysUserRole(String roleId) {

        return repository.getSysUserRole(roleId);
    }
}
