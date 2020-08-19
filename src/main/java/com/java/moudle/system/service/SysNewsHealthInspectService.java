package com.java.moudle.system.service;

import java.util.Map;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysNewsHealthInspect;
import com.java.until.dba.PageModel;

public interface SysNewsHealthInspectService extends BaseService<SysNewsHealthInspect> {
   
	
	//获取资讯类型下有多少资讯
	int getCountByCatId(String catId);
	//查询资讯列表
	void getNewsHealthPage(SysNewsHealthInspect newsHealth, PageModel page);
	//保存资讯信息
	Map<String, String> saveNewsHealthDetail(SysNewsHealthInspect info);
	//更新资讯的状态
	Map<String, String> updateNewsHealthStatus(SysNewsHealthInspect newsHealth);
}
