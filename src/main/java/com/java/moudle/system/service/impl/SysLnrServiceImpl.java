package com.java.moudle.system.service.impl;

import javax.inject.Named;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysLnrDao;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.service.SysLnrService;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:32
 **/
@Named
public class SysLnrServiceImpl extends BaseServiceImpl<SysLnrDao, SysLnr> implements SysLnrService {

    @Override
    public AddLxrDto getSysLnrDetails(String id) {
        return dao.getSysLnrDetails(id);
    }

	@Override
	public int isExist(String lxrSfzh, String customeerId) {
		return dao.isExist(lxrSfzh, customeerId);
	}

	@Override
	public SysLnr getLnrInfo(String sfzh) {
		return dao.getLnrInfo(sfzh);
	}
}
