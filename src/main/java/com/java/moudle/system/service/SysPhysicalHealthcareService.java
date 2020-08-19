package com.java.moudle.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysPhysicalHealthcare;
import com.java.until.cache.DictDto;

public interface SysPhysicalHealthcareService extends BaseService<SysPhysicalHealthcare>{

	SysPhysicalHealthcare getGuidanceByType(String physicalType);

	void addOrUpdateGuidanceInfo(SysPhysicalHealthcare healthcase) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	List<DictDto> getPhysicalType();

	List<JSONObject> getPhysicalByType (List<JSONObject> reqList);

}
