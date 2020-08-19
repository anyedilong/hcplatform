package com.java.moudle.system.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysNewsCategoryDao;
import com.java.moudle.system.domain.SysNewsCategory;
import com.java.moudle.system.service.SysNewsCategoryService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;


@Named
@Transactional(readOnly=false)
public class SysNewsCategoryServiceImpl extends BaseServiceImpl<SysNewsCategoryDao, SysNewsCategory> implements SysNewsCategoryService {

	@Override
	public List<SysNewsCategory> getNewsCategoryList() {
		return dao.getNewsCategoryList();
	}

	@Override
	public void getNewsCategoryPage(SysNewsCategory newsCat, PageModel page) {
		dao.getNewsCategoryPage(newsCat, page);
		@SuppressWarnings("unchecked")
		List<SysNewsCategory> list = page.getList();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setXh((page.getPageNo()-1)*page.getPageSize()+i+1+"");
			}
		}
		page.setList(list);
	}

	@Override
	public int getNewCatByCon(String id, String name, String orderNum) {
		return dao.getNewCatByCon(id, name, orderNum);
	}

	@Override
	public void saveNewsCat(SysNewsCategory newsCat) {
		List<SysNewsCategory> list = dao.getNewsCategoryList();
		//总条数为10时，两个数据的排序交换
		if(list != null && list.size() >= 10) {
			SysNewsCategory cat1 = null;
			for(SysNewsCategory info : list) {
				if(info.getOrderNum() == newsCat.getOrderNum()) {
					cat1 = info;
				}
			}
			cat1.setOrderNum(newsCat.getOldOrderNum());
			dao.save(cat1);
		}else {
			//当前的排序是否已被用
			SysNewsCategory info = dao.getNewCatInfoByCon("", newsCat.getOrderNum()+"");
			if(info != null) {
				if(!StringUntil.isNull(newsCat.getId())) {
					//修改时，入参中有该条类别以前的排序
					info.setOrderNum(newsCat.getOldOrderNum());
				}else {
					//新增时，得到空缺中最大的排序
					String[] numArr = new String[21];
					Integer orderNum = 21;
					for(int i = 0; i < list.size(); i++) {
						numArr[list.get(i).getOrderNum()] = list.get(i).getOrderNum()+"";
					}
					for(int j = 1; j < numArr.length; j++) {
						if(numArr[j] == null) {
							orderNum = j;
							break;
						}
					}
					info.setOrderNum(orderNum);
				}
				dao.save(info);
			}
		}
		if(StringUntil.isNull(newsCat.getId())) {
			newsCat.setId(UUIDUtil.getUUID());
		}
		newsCat.setStatus("0");
		dao.save(newsCat);
	}

	
}
