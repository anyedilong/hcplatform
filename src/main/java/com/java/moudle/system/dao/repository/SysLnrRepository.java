package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:33
 **/
public interface SysLnrRepository extends JpaRepository<SysLnr, String> {

    /**
     * 根据id删除联系人
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "delete from SysLnr where id = :id")
    void delLxr(@Param("id") String id);
}
