package com.java.moudle.tripartdock.region.service;

import java.util.List;
import java.util.Map;

import com.java.moudle.tripartdock.region.dto.HospitalImageDto;
import com.java.moudle.tripartdock.region.dto.HospitalInftoDto;
import com.java.moudle.tripartdock.region.dto.HospitalJcDto;
import com.java.moudle.tripartdock.region.dto.HospitalOrderDto;
import com.java.moudle.tripartdock.region.dto.HospitalOrderItemDto;
import com.java.moudle.tripartdock.region.dto.HospitalOutCostDto;
import com.java.moudle.tripartdock.region.dto.HospitalZdDto;
import com.java.until.dba.PageModel;

public interface RegionHospitalService {

    //查询住院列表
	PageModel getHospitalPage(PageModel page, Map<String, String> paramMap) throws Exception;
	//查询住院的详情
	HospitalInftoDto getHospitalDetail(Map<String, String> paramMap) throws Exception;
    //查询住院花费明细的详情
	List<HospitalOutCostDto> getHospitalCostList(Map<String, String> paramMap) throws Exception;
	//查询住院诊断的详情
	List<HospitalZdDto> getHospitalZDList(Map<String, String> paramMap) throws Exception;
	//查询住院的病案首页
	String getHospitalBasy(Map<String, String> paramMap) throws Exception;
	//查询住院影像的详情
	List<HospitalImageDto> getHospitalImageList(Map<String, String> paramMap) throws Exception;
	//门诊影像的转换
	String getCoverImageFormat(Map<String, String> paramMap) throws Exception;
	//查询住院检查的详情
	List<HospitalJcDto> getHospitalJcList(Map<String, String> paramMap) throws Exception;
	//查询住院的详情（手机app）
	HospitalInftoDto getAppHospitalDetail(Map<String, String> paramMap) throws Exception;
	//查询住院医嘱列表
	List<HospitalOrderDto> getHospitalOrderList(Map<String, String> paramMap) throws Exception;
	//查询住院医嘱详情
	HospitalOrderDto getHospitalOrderDetail(Map<String, String> paramMap) throws Exception;
	//查询住院医嘱明细列表
	List<HospitalOrderItemDto> getHospitalOrderItemList(Map<String, String> paramMap) throws Exception;
}
