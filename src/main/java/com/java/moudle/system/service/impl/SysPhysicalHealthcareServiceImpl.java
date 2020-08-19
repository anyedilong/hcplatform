package com.java.moudle.system.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.java.until.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysPhysicalHealthcareDao;
import com.java.moudle.system.domain.SysPhysicalHealthcare;
import com.java.moudle.system.service.SysPhysicalHealthcareService;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.DictDto;
import com.java.until.ftpup.UpUtils;

@Named
public class SysPhysicalHealthcareServiceImpl extends BaseServiceImpl<SysPhysicalHealthcareDao, SysPhysicalHealthcare> implements SysPhysicalHealthcareService{

	@Override
	public SysPhysicalHealthcare getGuidanceByType(String physicalType) {
		return dao.getGuidanceByType(physicalType);
	}

	@Value("${ftpUrl}")
    private String ftpUrl;
	
	@Override
	public void addOrUpdateGuidanceInfo(SysPhysicalHealthcare healthcase) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (StringUntil.isNull(healthcase.getId())) {
			healthcase.setId(UUIDUtil.getUUID());
		}
		healthcase.setCreateTime(new Date());
		
		String folder = "/certificate/";
		
		Class clazz = healthcase.getClass();
		Field[] field = clazz.getDeclaredFields();
		for (Field f : field) {
			 String name = f.getName();    //获取属性的名字
			 name = name.substring(0,1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
			 String type = f.getGenericType().toString(); // 获取属性类型
			 System.out.println(type);
			 if (type.equals("interface org.springframework.web.multipart.MultipartFile")) {
				 Method getM = clazz.getMethod("get" + name);
				 MultipartFile value = (MultipartFile) getM.invoke(healthcase);
				 if (value != null) {
					// 上传文件
					JSONObject res = UpUtils.uploadImage(value, folder);
					name = name.substring(0, name.length() - 4);
					System.out.println("set" + name);
					Method setM = clazz.getMethod("set" + name, String.class);
					System.out.println(setM);
					setM.invoke(healthcase, ftpUrl + res.get("path").toString());
				}
			 }
		}
		System.out.println("对象参数：" + JSON.toJSONString(healthcase));
		dao.save(healthcase);
	}

	@Override
	public List<DictDto> getPhysicalType() {
		// 从缓存中获取保健指导列表
		List<DictDto> list = CacheUntil.getArray("PhysicalType");
		return list;
	}

	/**
	 * 根据体质类型和建议获取详情
	 * @return
	 * 聂亚威
	 */
	@Override
	public List<JSONObject> getPhysicalByType(List<JSONObject> reqList) {
		List<JSONObject> resp = new ArrayList<>();
		for (JSONObject json : reqList ) {
			String type = json.getString("type");
			String proposal = json.getString("proposal");
			String typeText = json.getString("typeText");
			if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(proposal)) {
				SysPhysicalHealthcare physical = dao.getGuidanceByType(type);
				if (physical != null) {
					//情志调摄
					if (StringUtils.isNotBlank(physical.getEmotionContent()) && proposal.contains("1")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "情志调摄");
						data.put("content", physical.getEmotionContent());
						data.put("image1", physical.getEmotionImageOne());
						data.put("image2", physical.getEmotionImageTwo());
						data.put("image3", physical.getEmotionImageThree());
						resp.add(data);
					}
					//饮食调养
					if (StringUtils.isNotBlank(physical.getDietContent()) && proposal.contains("2")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "饮食调养");
						data.put("content", physical.getDietContent());
						data.put("image1", physical.getDietImageOne());
						data.put("image2", physical.getDietImageTwo());
						data.put("image3", physical.getDietImageThree());
						resp.add(data);
					}
					//起居调摄
					if (StringUtils.isNotBlank(physical.getLivingContent()) && proposal.contains("3")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "起居调摄");
						data.put("content", physical.getLivingContent());
						data.put("image1", physical.getLivingImageOne());
						data.put("image2", physical.getLivingImageTwo());
						data.put("image3", physical.getLivingImageThree());
						resp.add(data);
					}
					//运动保健
					if (StringUtils.isNotBlank(physical.getSportsContent()) && proposal.contains("4")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "运动保健");
						data.put("content", physical.getSportsContent());
						data.put("image1", physical.getSportsImageOne());
						data.put("image2", physical.getSportsImageTwo());
						data.put("image3", physical.getSportsImageThree());
						resp.add(data);
					}
					//穴位保健
					if (StringUtils.isNotBlank(physical.getAcupointContent()) && proposal.contains("5")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "穴位保健");
						data.put("content", physical.getAcupointContent());
						data.put("image1", physical.getAcupointImageOne());
						data.put("image2", physical.getAcupointImageTwo());
						data.put("image3", physical.getAcupointImageThree());
						resp.add(data);
					}
					//其他指导
					if (StringUtils.isNotBlank(physical.getOtherContent()) && proposal.contains("6")) {
						JSONObject data = new JSONObject();
						data.put("type", typeText);
						data.put("proposal", "其他指导");
						data.put("content", physical.getOtherContent());
						data.put("image1", physical.getOtherImageOne());
						data.put("image2", physical.getOtherImageTwo());
						data.put("image3", physical.getOtherImageThree());
						resp.add(data);
					}
				}
			}
		}
		return resp;
	}
}
