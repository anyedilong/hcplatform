package com.java.moudle.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysNewsHealthDao;
import com.java.moudle.system.dao.SysNewsHealthInspectDao;
import com.java.moudle.system.domain.SysNewsHealth;
import com.java.moudle.system.domain.SysNewsHealthInspect;
import com.java.moudle.system.service.SysNewsHealthInspectService;
import com.java.until.StringUntil;
import com.java.until.ToJavaUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import com.java.until.ftpup.UpUtils;


@Named
@Transactional(readOnly=false)
public class SysNewsHealthInspectServiceImpl extends BaseServiceImpl<SysNewsHealthInspectDao, SysNewsHealthInspect> implements SysNewsHealthInspectService {

	@Inject
	private SysNewsHealthDao newsHealthDao;
	@Value("${ftpUrl}")
    private String ftpUrl;
	
	
	@Override
	public int getCountByCatId(String catId) {
		return dao.getCountByCatId(catId);
	}

	@Override
	public void getNewsHealthPage(SysNewsHealthInspect newsHealth, PageModel page) {
		dao.getNewsHealthPage(newsHealth, page);
	}
	
	@Override
	public Map<String, String> saveNewsHealthDetail(SysNewsHealthInspect info) {
		Map<String, String> resultMap = new HashMap<>();
		
		//判断标题是否已存在
		int count = dao.getNewsHealthCount(info);
		if(count > 0) {
			resultMap.put("code", "10000");
			resultMap.put("msg", "资讯标题已重复，请重新输入！");
			return resultMap;
		}
		//保存封面图片文件
		String fileUrl = UpUtils.saveImage(info.getNewsfile(), ftpUrl);
		if("10000".equals(fileUrl)) {
			resultMap.put("code", "10000");
			resultMap.put("msg", "上传失败");
			return resultMap;
		}else if("-1".equals(fileUrl)) {
			resultMap.put("code", "-1");
			resultMap.put("msg", "系统错误");
			return resultMap;
		}else if(fileUrl.contains("certificate")){
			info.setNewsUrl(fileUrl);
		}
		
		if(StringUntil.isNull(info.getId())) {
			info.setId(UUIDUtil.getUUID());
			info.setCreateTime(new Date());
		}
		info.setStatus("0");
		info.setDeleteFlg("0");
		info.setPubTime(null);
		dao.save(info);
		resultMap.put("code", "0");
		resultMap.put("msg", "已处理");
		return resultMap;
	}

	@Override
	public Map<String, String> updateNewsHealthStatus(SysNewsHealthInspect newsHealth) {
		Map<String, String> map = new HashMap<>();
		SysNewsHealthInspect info = dao.get(newsHealth.getId());
		//提交-审核中
		if("3".equals(newsHealth.getStatus())) {
			info.setPubTime(null);
		}
		//审核通过
//		if("2".equals(newsHealth.getStatus())) {
//			info.setPubTime(null);
//		}
		//发布，设置发布日期
		if("5".equals(newsHealth.getStatus())) {
			info.setPubTime(new Date());
			//资讯发布时，发布表中新增一条数据
			SysNewsHealth temp = new SysNewsHealth();
			ToJavaUtils.copyFields(info, temp);
			newsHealthDao.save(temp);
		}
		//置顶，判断是否已有三条
		if("6".equals(newsHealth.getStatus())) {
			int count = dao.getNewsHealthCount(newsHealth);
			if(count >= 3) {
				map.put("code", "10000");
				map.put("msg", "最多可设置3条置顶信息，若要继续设置置顶信息，请将原置顶信息进行取消置顶操作！");
				return map;
			}
			newsHealthDao.updateStatusById(info.getId(), newsHealth.getStatus());
		}
		//退回
		if("1".equals(newsHealth.getStatus())) {
			info.setNewsRefund(newsHealth.getNewsRefund());
			//newsHealthDao.delNewsHealth(info.getId());
		}
		//撤回
		if("2".equals(newsHealth.getStatus())) {
			info.setPubTime(null);
			newsHealthDao.delNewsHealth(info.getId());
		}
		info.setStatus(newsHealth.getStatus());
		dao.save(info);
		map.put("code", "0");
		map.put("msg", "已处理");
		return map;
	}

}
