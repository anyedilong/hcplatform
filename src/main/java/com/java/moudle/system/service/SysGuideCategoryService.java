package com.java.moudle.system.service;

import java.util.List;
import java.util.Map;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysGuideCategory;
import com.java.until.dba.PageModel;

public interface SysGuideCategoryService extends BaseService<SysGuideCategory> {
   
	//查询办事指南类别的list(网站端)
	List<SysGuideCategory> getGuideCategoryList();
	//查询办事指南类别的列表
	void getGuideCategoryPage(SysGuideCategory info, PageModel page);
	//保存办事指南类别
	Map<String, String> saveGuideCat(SysGuideCategory info);
	//查询该类别下的指南数量
	int getCountByCatId(String id);
	//获取类别的排序
	List<Integer> getCategoryNum();
}
