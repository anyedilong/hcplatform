package com.java.moudle.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysGuideCategoryDao;
import com.java.moudle.system.dao.SysGuideInspectDao;
import com.java.moudle.system.domain.SysGuideCategory;
import com.java.moudle.system.service.SysGuideCategoryService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import com.java.until.ftpup.UpUtils;


@Named
@Transactional(readOnly=false)
public class SysGuideCategoryServiceImpl extends BaseServiceImpl<SysGuideCategoryDao, SysGuideCategory> implements SysGuideCategoryService {

	@Inject
	private SysGuideInspectDao guideInspectDao;
	
	@Override
	public List<SysGuideCategory> getGuideCategoryList() {
		List<SysGuideCategory> list = dao.getGuideCategoryList();
		if(list == null || list.size() == 0) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public void getGuideCategoryPage(SysGuideCategory info, PageModel page) {
		dao.getGuideCategoryPage(info, page);
		@SuppressWarnings("unchecked")
		List<SysGuideCategory> list = page.getList();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setXh((page.getPageNo()-1)*page.getPageSize()+i+1+"");
			}
		}
		page.setList(list);
	}

	@Value("${ftpUrl}")
    private String ftpUrl;
	
	@Override
	public Map<String, String> saveGuideCat(SysGuideCategory info) {
		Map<String, String> resultMap = new HashMap<>();
		
		SysGuideCategory catInfo = dao.getCatByOrderNum("", info.getName(), info.getId());
		if(catInfo != null && !StringUntil.isNull(catInfo.getId())) {
			resultMap.put("code", "10000");
			resultMap.put("msg", "类别名称不能重复！");
			return resultMap;
		}
		//保存封面图片文件
		String fileUrl = UpUtils.saveImage(info.getImageFile(), ftpUrl);
		if("10000".equals(fileUrl)) {
			resultMap.put("code", "10000");
			resultMap.put("msg", "文件上传失败！");
			return resultMap;
		}else if("-1".equals(fileUrl)) {
			resultMap.put("code", "-1");
			resultMap.put("msg", "系统错误");
			return resultMap;
		}else if(fileUrl.contains("certificate")){
			info.setImageUrl(fileUrl);
		}
		if(StringUntil.isNull(info.getId())) {
			info.setId(UUIDUtil.getUUID());
		}
		/*-----------------需求变更----------------------------------------
		//排序号计算
		//查询排序号是否被占用
		SysGuideCategory cat = dao.get(info.getId());
		SysGuideCategory occCat = dao.getCatByOrderNum(info.getOrderNum(), "", "");
		//没有被占用，直接保存；被占用，交换排序号
		if(cat != null && occCat != null && !cat.getId().equals(occCat.getId())) {
			occCat.setOrderNum(cat.getOrderNum());
		}else if(cat == null && occCat != null) {
			//获取第一个没有占用的排序号
			for(int i = 1; i < 1000; i++) {
				SysGuideCategory temp = dao.getCatByOrderNum(i+"", "", "");
				//需求变更；最多只能有九个；下标大于九的删除
				if(temp == null || i > 9) {
					//occCat.setOrderNum(i+"");
					occCat.setStatus("1");
					break;
				}
			}
		}
		if(occCat != null) {
			dao.save(occCat);
		}
		-----------------需求变更----------------------------------------**/
		info.setStatus("0");
		dao.save(info);
		resultMap.put("code", "0");
		resultMap.put("msg", "保存成功");
		return resultMap;
	}

	@Override
	public int getCountByCatId(String id) {
		return guideInspectDao.getCountByCatId(id);
	}

	@Override
	public List<Integer> getCategoryNum() {
		List<Integer> list = new ArrayList<>();
		List<Integer> numList = dao.getCategoryNum();
		if(numList == null || numList.size() == 0) {
			numList = new ArrayList<>();
		}
		for(int i = 1; i <= 9; i++) {
			boolean flag = false;
			for(int j = 0; j < numList.size(); j++) {
				if(i == numList.get(j)) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				list.add(i);
			}
		}
		return list;
	}
	
}
