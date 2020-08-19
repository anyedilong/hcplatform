package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.UpdatePhoneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 09:52
 **/
public interface SysCustormerRepository extends JpaRepository<SysCustormer, String> {

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "update sys_custormer set password = :password, pwd = :pwd where phone = :phone", nativeQuery = true)
    void updatePassword(@Param("phone") String phone, @Param("pwd") String pwd, @Param("password") String newPassword);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "update SysCustormer set phone = :#{#updatePhoneDto.newPhone} where id = :#{#updatePhoneDto.id}")
    void updatePhone(UpdatePhoneDto updatePhoneDto);
    
    @Transactional
    @Modifying
    @Query(value = "update sys_custormer set custormer_url =:custormerUrl where sfzh = :sfzh", nativeQuery = true)
    void updatePersonData(@Param("sfzh") String sfzh, @Param("custormerUrl") String custormerUrl);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysCustormer set status = :status where id = :id")
    void userstatus(String status, String id);
    
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update SysCustormer set custormer_url = :headImage where id = :id ")
    void updateUserHeadImage(@Param("id") String id, @Param("headImage") String headImage);
    
}
