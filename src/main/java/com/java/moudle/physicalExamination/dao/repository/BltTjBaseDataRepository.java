package com.java.moudle.physicalExamination.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.moudle.physicalExamination.domain.BltTjBaseData;

import feign.Param;

public interface BltTjBaseDataRepository extends JpaRepository<BltTjBaseData, String>{

	@Query(value = "from BltTjBaseData where grade = '1'")
	List<BltTjBaseData> queryGjFirstData();

	@Query(value = "from BltTjBaseData where parentId = :parentId")
	List<BltTjBaseData> getBaseDataInfoByParentId(@Param("parentId")String parentId);

}
