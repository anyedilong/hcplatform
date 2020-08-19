package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.transaction.Transactional;

import com.java.moudle.system.dao.repository.SysPhysicalHealthcareRepository;
import com.java.moudle.system.domain.SysPhysicalHealthcare;
import com.java.until.dba.BaseDao;

@Named
public class SysPhysicalHealthcareDao extends BaseDao<SysPhysicalHealthcareRepository, SysPhysicalHealthcare>{

	@Transactional(rollbackOn = Exception.class)
	public SysPhysicalHealthcare getGuidanceByType(String physicalType) {
		String sql = " select t.* from SYS_PHYSICAL_HEALTHCARE t where t.PHYSICAL_TYPE = :physicalType ";
		Map<String, String> param = new HashMap<>();
		param.put("physicalType", physicalType);
		return queryOne(sql, param, SysPhysicalHealthcare.class);
	}

}
