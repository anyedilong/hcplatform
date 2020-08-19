package com.java.moudle.physicalExamination.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.physicalExamination.domain.BltTjBaseData;
import com.java.moudle.physicalExamination.domain.BltTjInfo;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.until.cache.DictDto;

public interface PhysicalCruxService {

	void addOrUpdateGjData(LoginInfoDto sysUser, JSONObject input);

	List<BltTjBaseData> queryBaseGjData(JSONObject input);

	List<BltTjBaseData> getBaseDataInfoByParentId(String id);

	BltTjInfo queryGjInfo(String org, String rqlx);

	List<DictDto> getRqlx();
	
	List<BltTjBaseData> getSelectedTjData(String rqlx);
	
	//根据人群类型获取温馨提示
	List<String> getTipsByRqlx(String rqlx);
}
