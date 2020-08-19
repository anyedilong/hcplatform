package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysPolicyInspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SysPolicyInspectRepository extends JpaRepository<SysPolicyInspect, String> {
    //删除
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update sys_policy_inspect set delete_flg = :deleteFlg where id = :id", nativeQuery = true)
    void delPolicy(@Param("id") String id, @Param("deleteFlg") String deleteFlg);

    //修改状态
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update sys_policy_inspect set status = :status where id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id, @Param("status") String status);
}
