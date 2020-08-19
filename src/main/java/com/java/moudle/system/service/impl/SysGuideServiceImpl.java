package com.java.moudle.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysGuideDao;
import com.java.moudle.system.dao.SysGuideInspectDao;
import com.java.moudle.system.domain.SysGuide;
import com.java.moudle.system.domain.SysGuideInspect;
import com.java.moudle.system.dto.SysGuideDto;
import com.java.moudle.system.service.SysGuideService;
import com.java.until.StringUntil;
import com.java.until.ToJavaUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import com.java.until.ftpup.UpUtils;

@Named
public class SysGuideServiceImpl extends BaseServiceImpl<SysGuideDao, SysGuide> implements SysGuideService {

	@Inject
	private SysGuideInspectDao inspectDao;
	
	@Override
	public List<SysGuide> getGuideList(String searchContent) {
		return dao.getGuideList(searchContent);
	}

	@Override
	public void getGuidePage(SysGuide guide, PageModel page) {
		dao.getGuidePage(guide, page);
	}

	@Override
	public SysGuide getGuideInfo(String id) {
		return dao.getGuideInfo(id);
	}
	
	@Override
	public SysGuideInspect getGuideInfoAdmin(String id) {
		return inspectDao.getGuideInfo(id);
	}
	
	@Override
	public int addGuildeInspectInfo(SysGuideDto dto) {
		// id为空时查询办事指南,是否有重复标题
		SysGuideInspect info = inspectDao.getCuideInfoByTitle(dto.getTitle(), dto.getId());
		if (info != null) {
			return 1; // 1代表有重复
		}
		
		if (dto.getFile() != null) {
			// 上传文件
			JSONObject res = UpUtils.uploadImage(dto.getFile(), "/certificate/");
			dto.setGuideUrl(res.get("path").toString());
		}
		
		// 保存对象
		if (StringUntil.isNull(dto.getId())) {
			dto.setId(UUIDUtil.getUUID());
			dto.setCreateTime(new Date());
		}
		dto.setDeleteFlg("0");
		dto.setStatus("0");
		SysGuideInspect inspect = new SysGuideInspect();
		ToJavaUtils.copyFields(dto, inspect);
		inspectDao.save(inspect);
		return 0; // 0代表添加或更新成功
	}

	@Override
	public SysGuideInspect getGuideInspectInfo(String id) {
		return inspectDao.get(id);
	}

	@Override
	public void updateInspectInfo(SysGuideInspect guideInspect) {
		inspectDao.save(guideInspect);
	}

	@Override
	public void addGuildeInfo(SysGuide guideInfo) {
		dao.save(guideInfo);
	}

	@Override
	public void getGuideInpectPage(SysGuideInspect guide, PageModel page) {
		inspectDao.getGuideInpectPage(guide, page);
	}
}
