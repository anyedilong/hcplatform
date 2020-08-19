package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysGuide;
import com.java.moudle.system.domain.SysGuideInspect;
import com.java.moudle.system.dto.SysGuideDto;
import com.java.until.dba.PageModel;

public interface SysGuideService extends BaseService<SysGuide> {

	List<SysGuide> getGuideList(String searchContent);
	//查询办事指南列表
	void getGuidePage(SysGuide guide, PageModel page);
	//查询办事指南的详情 
	SysGuide getGuideInfo(String id);
	// 添加办事指南详情
	int addGuildeInspectInfo(SysGuideDto guideInfo);
	// 查询办事指南审核表详情
	SysGuideInspect getGuideInspectInfo(String id);
	// 更新办事指南审核表详情
	void updateInspectInfo(SysGuideInspect guideInspect);
	// 向办事指南表添加一条详情
	void addGuildeInfo(SysGuide guideInfo);
	// 查询审核表信息列表
	void getGuideInpectPage(SysGuideInspect guide, PageModel page);
	// 查询办事指南审核表
	SysGuideInspect getGuideInfoAdmin(String id);
	
}
