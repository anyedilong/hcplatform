package com.java.moudle.system.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.SysLinksDto;
import com.java.until.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysLinksDao;
import com.java.moudle.system.domain.SysLinks;
import com.java.moudle.system.service.SysLinksService;
import com.java.until.dba.PageModel;

import java.util.Date;
import java.util.regex.Pattern;


@Named
@Transactional(readOnly=false)
public class SysLinksServiceImpl extends BaseServiceImpl<SysLinksDao, SysLinks> implements SysLinksService {

	@Inject
	private SysLinksDao sysLinksDao;

	@Override
	public void getLinksPage(SysLinks links, PageModel page) {
		dao.getLinksPage(links, page);
	}

	@Override
	public void getLinklist(SysLinksDto dto, PageModel pageModel) {
		sysLinksDao.getLinklist(dto, pageModel);
	}

	@Override
	public JsonResult addLink(SysLinks sysLinks) {
		// 判断友情链接名称是否重复
		SysLinks sysLinks1 = sysLinksDao.getSysLinksByName(sysLinks.getName().trim());

		if (sysLinks1 != null) {
			return new JsonResult(null, 9001, "该友情链接名称已存在，请重新输入！");
		}

		// 判断连接名称是否含有特殊字符
		String patt = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		boolean isName =  Pattern.matches(patt, sysLinks.getName());
		if (isName) {
			return new JsonResult(null,9001, "链接名称不准使用特殊字符!");
		}

		// 判断超连接是否正确
		String pattern = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
		boolean isUrl = Pattern.matches(pattern, sysLinks.getLinkUrl());
		if (!isUrl) {
			return new JsonResult(null,9001, "请输入正确的超链接地址!");
		}

		// 参数封装
		sysLinks.setId(UUIDUtil.getUUID());
		sysLinks.setCreateTime(new Date());
		sysLinks.setStatus("0");
		sysLinksDao.save(sysLinks);
		return new JsonResult();
	}

	@Override
	public JsonResult delLink(SysLinks sysLinks) {
		sysLinksDao.updateStatus(sysLinks.getId(), "1");
		return new JsonResult();
	}

}
