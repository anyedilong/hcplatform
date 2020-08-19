package com.java.moudle.system.service.impl;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.system.service.SysBankcardTypeService;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.DictDto;

@Named
public class SysBankcardTypeServiceImpl implements SysBankcardTypeService{

	@Override
	public List<DictDto> getSysBankCardTypeList() {
		// 从缓存中获取银行列表
		List<DictDto> list = CacheUntil.getArray("BankCardType");
		return list;
	}
	
}
