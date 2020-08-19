package com.java.moudle.system.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.moudle.system.domain.SysLinks;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SysLinksRepository extends JpaRepository<SysLinks, String> {

    /**
     * @Description 删除友情链接
     * @Author zw
     * @Date 15:35 2020-03-23
     * @Param
     * @return void
     **/
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysLinks set status = :status where id = :id ")
    void updateStatus(@Param("id") String id, @Param("status") String status);

    /**
     * @Description 根据链接名称查询详情
     * @Author zw
     * @Date 17:39 2020-03-30
     * @Param
     * @return com.java.moudle.system.domain.SysLinks
     **/
    @Query("from SysLinks where name = :name and status = '0'")
    SysLinks getSysLinksByName(String name);
}
