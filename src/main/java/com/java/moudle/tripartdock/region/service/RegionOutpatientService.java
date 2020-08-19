package com.java.moudle.tripartdock.region.service;



import java.util.List;
import java.util.Map;

import com.java.moudle.tripartdock.region.dto.MedicalrecordDto;
import com.java.moudle.tripartdock.region.dto.OutpatientImageDto;
import com.java.moudle.tripartdock.region.dto.OutpatientJcDto;
import com.java.moudle.tripartdock.region.dto.OutpatientRecipeDto;
import com.java.moudle.tripartdock.region.dto.OutpatientZdDto;
import com.java.until.dba.PageModel;

public interface RegionOutpatientService {

    //查询门诊列表
	PageModel getOutpatientPage(PageModel page, Map<String, String> paramMap) throws Exception;
	//
	MedicalrecordDto getOutpatientDetail(Map<String, String> paramMap) throws Exception;
    //查询门诊处方的详情
	List<OutpatientRecipeDto> getOutpatientCfList(Map<String, String> paramMap) throws Exception;
	//查询门诊诊断的详情
	List<OutpatientZdDto> getOutpatientZDList(Map<String, String> paramMap) throws Exception;
	//查询门诊影像的详情
	List<OutpatientImageDto> getOutpatientImageList(Map<String, String> paramMap) throws Exception;
	//门诊影像的转换
	String getCoverImageFormat(Map<String, String> paramMap) throws Exception;
	//查询门诊检查的详情
	List<OutpatientJcDto> getOutpatientJcList(Map<String, String> paramMap) throws Exception;
	
	//查询门诊检查的详情
	String getJyList(Map<String, String> paramMap) throws Exception;
	//获取检验报告的详情
	String getJyDetail(Map<String, String> paramMap) throws Exception;
	//获取检查报告的列表
	String getJcList(Map<String, String> paramMap) throws Exception;
	//获取检查报告的详情
	String getJcDetail(Map<String, String> paramMap) throws Exception;
	
}
