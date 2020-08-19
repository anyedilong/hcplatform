package com.java.moudle.tripartdock.help.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.tripartdock.help.admin.BltCommonProblem;
import com.java.moudle.tripartdock.help.dao.BltCommonProblemDao;
import com.java.moudle.tripartdock.help.service.HelpService;
import com.java.until.DictUtil;

@Service
public class HelpServiceImpl extends BaseServiceImpl<BltCommonProblemDao, BltCommonProblem> implements HelpService {

	@Inject
	BltCommonProblemDao bltCommonProblemDao;
	
	@Override
	public Map<String, List<BltCommonProblem>> getCommonProblem() {
		
		Map<String, List<BltCommonProblem>> map = new HashMap<>();
		
		List<BltCommonProblem> list = bltCommonProblemDao.getCommonProblem();
		if(list != null && list.size() > 0) {
			for(BltCommonProblem info : list) {
				String wtfl = info.getWtfl().toString();
				String wtflms = DictUtil.getDictValue("wtfl", wtfl);
				List<BltCommonProblem> wtflList;
				if(map.containsKey(wtflms)) {
					wtflList = map.get(wtflms);
				}else {
					wtflList = new ArrayList<>();
					map.put(wtflms, wtflList);
				}
				wtflList.add(info);
			}
		}
		return map;
	}

}
