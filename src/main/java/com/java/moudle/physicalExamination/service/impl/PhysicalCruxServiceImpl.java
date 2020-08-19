package com.java.moudle.physicalExamination.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.physicalExamination.dao.BltTjBaseDataDao;
import com.java.moudle.physicalExamination.dao.BltTjDataDao;
import com.java.moudle.physicalExamination.dao.BltTjInfoDao;
import com.java.moudle.physicalExamination.domain.BltTjBaseData;
import com.java.moudle.physicalExamination.domain.BltTjData;
import com.java.moudle.physicalExamination.domain.BltTjInfo;
import com.java.moudle.physicalExamination.service.PhysicalCruxService;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.DictDto;

@Named
@Transactional
public class PhysicalCruxServiceImpl implements PhysicalCruxService{

	@Inject
	private BltTjBaseDataDao tjBaseDataDao;
	
	@Inject
	private BltTjInfoDao tjInfoDao;

	@Inject
	private BltTjDataDao tjDataDao;
	
	@Override
	public void addOrUpdateGjData(LoginInfoDto sysUser, JSONObject input) {
		String orgName = sysUser.getOrgName();
		String user = sysUser.getId();
		String rqlxCode = input.getString("rqlxCode");
		String guideInfo = input.getString("guideInfo");
		JSONArray gjInfo = input.getJSONArray("gjInfo");
		Date time = new Date();
		String infoId = ""; // BltTjInfo表的主键
		
		// 代表是更新，大部分时候均为更新，先删除数据，再进行添加操作
		if (input.containsKey("id") &&  !"".equals(input.getString("id"))) {
			infoId = input.getString("id");
			// 根据这个id删除关键数据表id相关数据
			tjDataDao.deleteByInfoId(infoId);
		} else {
			infoId = UUIDUtil.getUUID();
		}
		
		// 保存体检关键信息表
		BltTjInfo tjInfo = new BltTjInfo();
		tjInfo.setId(infoId);
		tjInfo.setGuideInfo(guideInfo);
		tjInfo.setOrgName(orgName);
		tjInfo.setUpdateTime(time);
		tjInfo.setUpdateUser(user);
		tjInfo.setRqlxCode(rqlxCode);
		tjInfoDao.save(tjInfo);
		
		// 保存体检关键信息关联表
		BltTjData tjData = new BltTjData();
		tjData.setTjInfoId(infoId);
		for (int i = 0; i < gjInfo.size(); i++) {
			String baseId = gjInfo.get(i).toString();
			
			// 保存选中数据
			tjData.setId(UUIDUtil.getUUID());
			tjData.setBasedataId(baseId);
			tjDataDao.save(tjData);
		}
	}

	@Override
	public List<BltTjBaseData> queryBaseGjData(JSONObject input) {
		List<BltTjBaseData> list = tjBaseDataDao.queryGjFirstData();
		return list;
	}

	@Override
	public List<BltTjBaseData> getBaseDataInfoByParentId(String id) {
		return tjBaseDataDao.getBaseDataInfoByParentId(id);
	}

	@Override
	public BltTjInfo queryGjInfo(String org, String rqlx) {
		return tjInfoDao.queryGjInfo(org, rqlx);
	}

	@Override
	public List<DictDto> getRqlx() {
		List<DictDto> array = CacheUntil.getArray("tjRqlx");		
		return array;
	}

	@Override
	public List<BltTjBaseData> getSelectedTjData(String rqlx) {
		return tjBaseDataDao.getSelectedTjData(rqlx);
	}

	@Override
	public List<String> getTipsByRqlx(String rqlx) {
		return tjInfoDao.getTipsByRqlx(rqlx);
	}

}
