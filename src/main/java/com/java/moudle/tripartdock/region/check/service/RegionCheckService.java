package com.java.moudle.tripartdock.region.check.service;

import java.util.List;
import java.util.Map;

import com.java.moudle.tripartdock.region.check.dto.CheckDto;
import com.java.moudle.tripartdock.region.check.dto.JkjcDto;
import com.java.moudle.tripartdock.region.check.dto.JkwtbDto;
import com.java.moudle.tripartdock.region.check.dto.JzbcsDto;
import com.java.moudle.tripartdock.region.check.dto.JzsDto;
import com.java.moudle.tripartdock.region.check.dto.PgzdjlbDto;
import com.java.moudle.tripartdock.region.check.dto.ShfsDto;
import com.java.moudle.tripartdock.region.check.dto.XzjcjlbDto;
import com.java.moudle.tripartdock.region.check.dto.YbzzbDto;
import com.java.moudle.tripartdock.region.check.dto.YjjlbDto;
import com.java.moudle.tripartdock.region.check.dto.ZqgnbDto;
import com.java.moudle.tripartdock.region.check.dto.ZysDto;
import com.java.until.dba.PageModel;

public interface RegionCheckService {
    
	//健康体检对比
	Map<String, Object> getFirstFjhDetail(String sfzh, String jcrq) throws Exception;
	//获取体检的列表
	PageModel getCheckPage(PageModel page, String jmId) throws Exception;
	//获取体检的详情
	CheckDto getCheckDetail(String tjId) throws Exception;
	//获取健康检查详情
	JkjcDto getJkjcDetail(String tjId) throws Exception;
	//获取健康问题详情
	JkwtbDto getJkwtbDetail(String tjId) throws Exception;
	//获取评估指导详情
	PgzdjlbDto getPgzdjlbDetail(String tjId) throws Exception;
	//获取生活方式详情
	ShfsDto getShfsDetail(String tjId) throws Exception;
	//获取辅助检查详情
	XzjcjlbDto getXzjcjlbDetail(String tjId) throws Exception;
	//获取一般症状详情
	YbzzbDto getYbzzbDetail(String tjId) throws Exception;
	//获取脏器功能详情
	ZqgnbDto getZqgnbDetail(String tjId) throws Exception;
	//获取家族病床史详情
	List<JzbcsDto> getJzbcsList(String tjId) throws Exception;
	//获取接种史详情
	List<JzsDto> getJzsList(String tjId) throws Exception;
	//获取药剂记录详情
	List<YjjlbDto> getYjjlbList(String tjId) throws Exception;
	//获取住院史详情
	List<ZysDto> getZysList(String tjId) throws Exception;
	
}
