package com.java.moudle.system.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.moudle.system.domain.SysGuide;

@Repository
public interface SysGuideRepository extends JpaRepository<SysGuide, String>{

	@Query(value = "from SysGuide where (title like concat('%', :searchContent, '%') or content like concat('%', :searchContent, '%'))")
	List<SysGuide> getGuideList(String searchContent);

}
