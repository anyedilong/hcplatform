package com.java.moudle.system.dao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.moudle.system.domain.SysNewsHealth;

public interface SysNewsHealthRepository extends JpaRepository<SysNewsHealth, String> {
	
	@Query(value = "from SysNewsHealth where (newsTitle like concat('%', :searchContent, '%') or newsContent like concat('%', :searchContent, '%'))")
	List<SysNewsHealth> getNewsHealthList(String searchContent);
	
	@Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "update news_health set status = :status where id = :id", nativeQuery = true)
    int updateStatusById(@Param("id") String id, @Param("status") String status);
	
	@Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "delete from news_health where id = :id", nativeQuery = true)
    int delNewsHealth(@Param("id") String id);
	
}
