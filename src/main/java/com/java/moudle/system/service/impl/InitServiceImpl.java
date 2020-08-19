package com.java.moudle.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dao.InitDao;
import com.java.moudle.system.dto.InitDictDto;
import com.java.moudle.system.service.InitService;
import com.java.until.StringUtils;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.http.HttpUtil;

//初始化，将数据放入缓存
@Named
@Transactional(readOnly = true)
public class InitServiceImpl implements InitService {

	private static ReentrantLock reenLock = new ReentrantLock();
	@Inject
	private InitDao dao;


	@Override
	public void InitDict() {
		try {
			//Object dicts = CacheUntil.get(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, Map.class);
			//if (null != dicts) {
			//	return;
			//}
			
			if(reenLock.tryLock()) {
				//Object dicts1 = CacheUntil.get(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, Map.class);
				//if(dicts1 == null) {
					Map<String, List<InitDictDto>> map = new HashMap<>();
					List<InitDictDto> list = dao.getDictAllParentList();
					if(list != null && list.size() > 0) {
						for (InitDictDto dictDto : list) {
							map.put(dictDto.getCode(), new ArrayList<>());
						}
						List<InitDictDto> listAll = dao.getDictAllList();
						if(listAll != null && listAll.size() > 0) {
							for (InitDictDto dictDto : listAll) {
								map.get(dictDto.getParentCode()).add(dictDto);
							}
						}
					}
					
					//查询所有公卫字典信息
					String sendPost = HttpUtil.doPost(PropertiesUtil.getFollow("prefix") + "/region/hisRegion/queryBltDictList", "");
					List<InitDictDto> dictList = JSON.parseArray(sendPost, InitDictDto.class);
					for (InitDictDto dict : dictList) {
						String parentCode = StringUtils.toString(dict.getParentCode());

						List<InitDictDto> itemList;
						if (!map.containsKey(parentCode)) {
							itemList = new ArrayList<InitDictDto>();
							map.put(parentCode, itemList);
						} else {
							itemList = map.get(parentCode);
						}
						itemList.add(dict);
					}
					
					
					//System.out.println(JSON.toJSONString(map));
					CacheUntil.put(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, map);
				}
			//	reenLock.unlock();
			//}else {
			//	Thread.sleep(10000);
			//	this.InitDict();
				
			//}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
