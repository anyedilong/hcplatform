package com.java.moudle.physicalExamination.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.java.moudle.physicalExamination.domain.BltTjData;

import feign.Param;

public interface BltTjDataRepository extends JpaRepository<BltTjData, String>{

	@Transactional
    @Modifying
	@Query(" delete from BltTjData where tjInfoId = :infoId ")
	void deleteByInfoId(@Param("infoId")String infoId);

}
