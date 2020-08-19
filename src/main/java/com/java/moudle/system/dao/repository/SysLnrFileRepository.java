package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysLnrFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:42
 **/
public interface SysLnrFileRepository extends JpaRepository<SysLnrFile, String> {

    @Query("from SysLnrFile where lnrId = :id")
    SysLnrFile getSysLnrFileByLnrId(String id);
}
