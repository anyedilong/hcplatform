package com.java.moudle.system.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.moudle.system.domain.SysApplicationList;

@Repository
public interface SysApplicationListRepository extends JpaRepository<SysApplicationList, String>{

	@Query(value="from SysApplicationList where applicationName like concat('%', :searchContent, '%') and status != 2")
	List<SysApplicationList> getApplicationListByName(@Param("searchContent")String searchContent);

}
