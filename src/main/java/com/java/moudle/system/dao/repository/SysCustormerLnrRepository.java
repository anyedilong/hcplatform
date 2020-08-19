package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysCustormerLnr;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.dto.LxrDto;
import com.java.moudle.system.dto.UpdateLxrDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:44
 **/
public interface SysCustormerLnrRepository extends JpaRepository<SysCustormerLnr, String> {

    /**
     * @Description 根据本人身份证号和联系人身份证号修改关系
     * @Author zw
     * @Date 15:13 2020-03-31
     * @Param
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update SysCustormerLnr set gx = :gx where lnrId = :id and custormerId = :custormerId")
    void updateLxrGx(@Param("gx") String gx, @Param("id")String id, @Param("custormerId")String custormerId);

    /**
     * @Description 删除客户联系人关联信息
     * @Author zw
     * @Date 08:44 2020-04-26
     * @Param lnrId
     * @return custormerId
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("delete from SysCustormerLnr where lnrId = :id and custormerId = :custormerId")
    void deleteSysCustormerLnr(@Param("id") String id, @Param("custormerId") String custormerId);

    /**
     * @Description 修改联系人关系其他
     * @Author zw
     * @Date 08:37 2020-05-06
     * @Param
     * @return
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update SysCustormerLnr set gx = :#{# updateLxrDto.gx}, gxOther = :#{#updateLxrDto.gxOther} where lnrId = :#{#updateLxrDto.id} and custormerId = :#{#updateLxrDto.custormerId}")
    void updateLxrGxOther(@Param("updateLxrDto") UpdateLxrDto updateLxrDto);

}
