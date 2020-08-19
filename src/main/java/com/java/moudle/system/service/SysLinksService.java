package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysLinks;
import com.java.moudle.system.dto.SysLinksDto;
import com.java.until.dba.PageModel;

public interface SysLinksService extends BaseService<SysLinks> {
   
	
	void getLinksPage(SysLinks links, PageModel page);

	void getLinklist(SysLinksDto dto, PageModel pageModel);

	JsonResult addLink(SysLinks sysLinks);

	JsonResult delLink(SysLinks sysLinks);
}
