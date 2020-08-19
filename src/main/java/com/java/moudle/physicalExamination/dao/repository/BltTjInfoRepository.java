package com.java.moudle.physicalExamination.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.moudle.physicalExamination.domain.BltTjInfo;

import feign.Param;

public interface BltTjInfoRepository extends JpaRepository<BltTjInfo, String>{

	@Query(value = "from BltTjInfo where rqlxCode = :rqlx and orgName = :org")
	BltTjInfo queryGjInfo(@Param("org")String org, @Param("rqlx")String rqlx);

}
