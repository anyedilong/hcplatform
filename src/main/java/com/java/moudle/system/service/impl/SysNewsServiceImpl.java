package com.java.moudle.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysNewsDao;
import com.java.moudle.system.domain.SysNews;
import com.java.moudle.system.dto.SysNewsDto;
import com.java.moudle.system.service.SysNewsService;
import com.java.until.dba.PageModel;


@Named
@Transactional(readOnly=false)
public class SysNewsServiceImpl extends BaseServiceImpl<SysNewsDao, SysNews> implements SysNewsService {

	@SuppressWarnings("unchecked")
	@Override
	public void getNewsPage(SysNewsDto news, PageModel page) {
		dao.getNewsPage(news, page);
		List<SysNewsDto> list = page.getList();
		if(list != null && list.size() > 0) {
			for(int i = 1; i <= list.size(); i++) {
				int xh = (page.getPageNo() - 1) * page.getPageSize() + i;
				list.get(i-1).setXh(xh+"");
			}
		}
	}

	@Override
	public String getRollNews() {
		String msg = "";
		List<SysNews> list = dao.getRollNewsList();
		if(list != null && list.size() > 0) {
			for(SysNews info : list) {
				msg += info.getSynopsis()+"  ";
			}
		}
		return msg;
	}

	@Override
	public Map<String, String> upateNewsStatus(SysNews news) {
		Map<String, String> map = new HashMap<>();
		SysNews sysNews = dao.get(news.getId());
		//撤回，把发布日期设置为空
		if("2".equals(news.getStatus())) {
			sysNews.setPubTime(null);
		}
		//发布，设置发布日期
		if("3".equals(news.getStatus())) {
			sysNews.setPubTime(new Date());
		}
		//置顶，判断是否已有三条
		if("4".equals(news.getStatus())) {
			int count = dao.getNewsCount(news);
			if(count >= 3) {
				map.put("code", "10000");
				map.put("msg", "最多可设置3条置顶信息，若要继续设置置顶信息，请将原置顶信息进行取消置顶操作！");
				return map;
			}
		}
		sysNews.setStatus(news.getStatus());
		dao.save(sysNews);
		map.put("code", "0");
		map.put("msg", "已处理");
		return map;
	}
	
	@Override
	public SysNewsDto getNewsDetail(String id) {
		return dao.getNewsDetail(id);
	}
	
	@Override
	public int getNewsCount(SysNews news) {
		return dao.getNewsCount(news);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getFrontNewsPage(SysNews news, PageModel page) {
		dao.getFrontNewsPage(news, page);
		List<SysNews> list = page.getList();
		if(list == null || list.size() == 0) {
			List<SysNews> rollNewsList = dao.getRollNewsList();
			if(rollNewsList != null && rollNewsList.size() > 0) {
				list.add(rollNewsList.get(0));
			}
		}
		page.setList(list);
	}
}
