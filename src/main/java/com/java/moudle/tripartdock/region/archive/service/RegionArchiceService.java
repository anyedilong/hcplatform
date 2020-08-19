package com.java.moudle.tripartdock.region.archive.service;

import java.util.List;

import com.java.moudle.tripartdock.region.archive.dto.BsxxDto;
import com.java.moudle.tripartdock.region.archive.dto.JbxxjlbDto;
import com.java.moudle.tripartdock.region.archive.dto.JzsxxbDto;
import com.java.moudle.tripartdock.region.archive.dto.ShhjbDto;

public interface RegionArchiceService {
    
	//获取档案的详情-管理员登录
	JbxxjlbDto getArchiveDetailBySfzh(String sfzh) throws Exception;
	//查询病史信息的详情
	List<BsxxDto> getBSxxDetail(String jmId) throws Exception;
	//查询家族史信息的详情
	JzsxxbDto getJZSxxDetail(String jmId) throws Exception;
	//查询生活环境信息的详情
	ShhjbDto getSHfsDetail(String jmId) throws Exception;
	//通过身份证号获取人群类型
	String queryRqlxBySfzh(String sfzh) throws Exception;
	
}
