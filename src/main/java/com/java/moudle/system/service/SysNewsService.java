package com.java.moudle.system.service;

import java.util.Map;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysNews;
import com.java.moudle.system.dto.SysNewsDto;
import com.java.until.dba.PageModel;

public interface SysNewsService extends BaseService<SysNews> {
   
	//获取系统消息的列表
	void getNewsPage(SysNewsDto news, PageModel page);
	//保存系统消息
	String getRollNews();
	//更新系统消息状态
	Map<String, String> upateNewsStatus(SysNews news);
	//查看系统消息详情
	SysNewsDto getNewsDetail(String id);
	//根据某个条件获取消息是否存在
	int getNewsCount(SysNews news);
	//获取系统消息的列表(网站端)
	void getFrontNewsPage(SysNews news, PageModel page);
}
