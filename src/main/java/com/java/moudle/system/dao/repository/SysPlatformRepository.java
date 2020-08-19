package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysPlatformRepository extends JpaRepository<SysPlatform, String> {

    /**
     * @Description 修改网站logo和欢迎词
     * @Author zw
     * @Date 09:26 2020-03-23
     * @Param
     * @return void
     **/
    @Modifying
    @Query("update SysPlatform set logo = :#{#sysPlatform.logo}, welcomeContent = :#{#sysPlatform.welcomeContent} where id = :#{#sysPlatform.id}")
    void siteSettings(SysPlatform sysPlatform);

    /**
     * @Description 修改网站欢迎词
     * @Author zw
     * @Date 09:03 2020-03-24
     * @Param 
     * @return void
     **/
    @Modifying
    @Query("update SysPlatform set welcomeContent = :#{#sysPlatform.welcomeContent} where id = :#{#sysPlatform.id}")
    void siteSettings2(SysPlatform sysPlatform);
}
