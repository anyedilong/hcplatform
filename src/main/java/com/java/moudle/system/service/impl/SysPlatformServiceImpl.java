package com.java.moudle.system.service.impl;

import javax.inject.Named;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.SaveWzszDto;
import com.java.until.ftpup.UpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysPlatformDao;
import com.java.moudle.system.domain.SysPlatform;
import com.java.moudle.system.service.SysPlatformService;


@Named
@Transactional(readOnly=false)
public class SysPlatformServiceImpl extends BaseServiceImpl<SysPlatformDao, SysPlatform> implements SysPlatformService {

	@Value("${ftpUrl}")
	private String ftpUrl;

	@Override
	public SysPlatform getPlatDetail() {
		return dao.getPlatDetail();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JsonResult siteSettings(SaveWzszDto dto, int isId) {
		SysPlatform sysPlatform = new SysPlatform();

		// 封装SysPlatform
		sysPlatform.setId(dto.getId());
		sysPlatform.setWelcomeContent(dto.getWelcomeContent());

		// 上传logo并获取地址
		if (dto.getLogo() != null) {
			JSONObject logoJson = UpUtils.uploadImage(dto.getLogo(), "/certificate/");
			if (!"0".equals(logoJson.get("resultCode").toString()) || !"0".equals(logoJson.get("resultCode").toString())) {
				return new JsonResult(null, 9001, "上传图片失败");
			}
			sysPlatform.setLogo(ftpUrl + logoJson.get("path").toString());
		}

		if (isId == 0) {
			// 保存网站信息
			dao.save(sysPlatform);
		} else {
			if (dto.getLogo() != null) {

				// 修改网站信息 (logo和欢迎词)
				dao.siteSettings(sysPlatform);
			} else {
				// 欢迎词
				dao.siteSettings2(sysPlatform);
			}
		}

		return new JsonResult();
	}


}
