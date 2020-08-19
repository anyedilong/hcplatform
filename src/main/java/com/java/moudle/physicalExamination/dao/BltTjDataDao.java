package com.java.moudle.physicalExamination.dao;

import javax.inject.Named;

import com.java.moudle.physicalExamination.dao.repository.BltTjDataRepository;
import com.java.moudle.physicalExamination.domain.BltTjData;
import com.java.until.dba.BaseDao;

@Named
public class BltTjDataDao extends BaseDao<BltTjDataRepository, BltTjData>{

	public void deleteByInfoId(String infoId) {
		repository.deleteByInfoId(infoId);
	}

}
