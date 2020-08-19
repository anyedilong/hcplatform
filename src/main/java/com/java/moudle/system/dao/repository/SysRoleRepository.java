package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:06
 **/
public interface SysRoleRepository extends JpaRepository<SysRole, String> {


    /**
     * @Description 删除角色
     * @Author zw
     * @Date 17:52 2020-03-23
     * @Param 
     * @return void
     **/
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysRole set status = :status where id = :id")
    void updateStatus(String id, String status);

    @Query("from SysRole where status = :status and role_type != '1'")
    List<SysRole> getAllRole(String status);

    @Query("from SysRole where roleName = :#{#sysRole.roleName} and userId = :#{#sysRole.userId} and status = '0' and role_type != '1'")
    SysRole getSysRoleByNameAndUserId(SysRole sysRole);

    @Query("from SysRole where roleType = :roleType and status = '0' and role_type != '1'")
    List<SysRole> getSysRoleByRoleType(String roleType);

    @Query("from SysRole where userId = :userId and status = '0' and role_type != '1' ")
    List<SysRole> getSysRolesByUserId(String userId);
}
