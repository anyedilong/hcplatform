package com.java.moudle.system.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.moudle.system.domain.SysPolicy;

public interface SysPolicyRepository extends JpaRepository<SysPolicy, String> {

	@Query(value = "from SysPolicy where (policyTitle like concat('%', :searchContent, '%') or policyContent like concat('%', :searchContent, '%'))")
	List<SysPolicy> getPolicyList(@Param("searchContent")String searchContent);

}
