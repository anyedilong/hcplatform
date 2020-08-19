package com.java.moudle.system.service;

import java.util.List;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.until.dba.PageModel;

public interface SysNewsCategoryService extends BaseService<SysNewsCategory> {
   
	//查询资讯类别的list(网站端)
	List<SysNewsCategory> getNewsCategoryList();
	//查询资讯类别的列表
	void getNewsCategoryPage(SysNewsCategory newsCat, PageModel page);
	//查询资讯类别是否被占用
	int getNewCatByCon(String id, String name, String orderNum);
	//保存资讯类别信息
	void saveNewsCat(SysNewsCategory newsCat);
	
}
