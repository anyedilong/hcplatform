package com.java.moudle.system.dao.repository;

import com.java.moudle.system.domain.SysFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:59
 **/
public interface SysFunctionRepository extends JpaRepository<SysFunction, String> {

    @Query("from SysFunction where parentId = :functionId")
    List<SysFunction> getSysFunctionListByFunctionId(String functionId);
}
