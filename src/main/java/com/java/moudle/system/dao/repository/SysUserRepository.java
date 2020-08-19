package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SysUserRepository extends JpaRepository<SysUser, String> {

    /**
     * @Author zw
     * @Description 重置密码
     * @Date 14:18 2020-03-17
     * @Param
     * @return void
     **/
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysUser set password = :#{#sysUser.password} where id = :#{#sysUser.id}")
    void resetPassword(SysUser sysUser);

    /**
     * @Author zw
     * @Description 根据id修改账户状态
     * @Date 17:53 2020-03-17
     * @Param
     * @return void
     **/
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysUser set status = :#{#sysUser.status} where id = :#{#sysUser.id}")
    void updateUserStatus(SysUser sysUser);

    /**
     * @Description 根据id修改机构名称和客户姓名
     * @Author zw
     * @Date 12:51 2020-03-27
     * @Param
     * @return void
     **/
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysUser set orgName = :#{#sysUser.orgName}, name = :#{#sysUser.name} where id = :#{#sysUser.id}")
    void updateSysUserById(SysUser sysUser);
}
