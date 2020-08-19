package com.java.moudle.common.controller;


import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.service.SysCustormerService;
import com.java.until.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.java.moudle.common.message.JsonResult;
import com.java.until.AliyunSMSUtil;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.ftpup.UpUtils;
import com.java.until.validate.VerificationUtil;

/**
 * <br>
 * <b>功能：</b>CustomerController<br>
 * <b>作者：</b>blt<br>
 * <b>版权所有：<b>版权所有(C) 2016，blt<br>
 */
@RestController
@RequestMapping("commontools")
public class CommonToolsController extends BaseController {

	@Value("${ftpUrl}")
    private String ftpUrl;

	@Inject
	private SysCustormerService sysCustormerService;
	
	/**
	 * @Description: 获取手机验证码
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getIdentCode")
	public JsonResult getIdentCode() {
		try {
			String param = getParam(request);
			JSONObject json = JSON.parseObject(param);
			logger.info("getIdentCode获取手机验证码入参:[{}]", param);
			String telephone = null;
			if (json.containsKey("telephone")) {
				telephone = json.get("telephone").toString();
			}
			// 判断是否注册还是登陆 如果是注册判断用户手机号是否注册过
			if (json.containsKey("login") && StringUtils.isNotBlank(json.get("login").toString())) {
				String login = json.get("login").toString();
				SysCustormer sysCustormer = sysCustormerService.getCUstormerInfoByPhone(telephone);
				// login 1 注册 2登录
				if ("1".equals(login)) {
					if (sysCustormer != null) {
						return jsonResult(null, 9001,"您的账户已注册，请登录");
					}
				} else if ("2".equals(login)){
					if (sysCustormer == null) {
						return jsonResult(null, 9001,"该手机号未注册, 请先注册");
					}
					
				}
			}


			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
	        cal.add(Calendar.MINUTE, -1);
	        if(!StringUntil.isNull(telephone)) {
	        	//获取前五分钟是否发送过验证码
	        	//QuerySendDetailsResponse querySendDetails = AliyunSMSUtil.querySendDetails(telephone, cal.getTime());

	        	//从缓存中获取本次的验证码 不在判断验证码时效
//		        String indentCodeTemp = CacheUntil.get(RedisCacheEmun.USER_CACHE, telephone, String.class);
//	        	//校验验证码是否有一分钟 或者 缓存中验证码是否被使用过(使用后删除)
//	        	if(StringUntil.isNull(indentCodeTemp)) {
	        		//发送的用户名和验证码
	        		String identCode = UUIDUtil.getIdentCode();
	        		SendSmsResponse sendSms = AliyunSMSUtil.sendSms(telephone, identCode);
	        		if("OK".equals(sendSms.getCode())) {
	        			CacheUntil.put(RedisCacheEmun.USER_CACHE, telephone, identCode, 60 * 30);
						logger.info("getIdentCode获取手机验证码, 已发送");
	        			return jsonResult(null, 0, "已发送");
	        		}else {
						logger.info("getIdentCode获取手机验证码, 不要频繁点击");
	        			return jsonResult(null, 10000, "不要频繁点击");
	        		}
//	        	}else {
//					logger.info("getIdentCode获取手机验证码, 您的验证码未过期，请查看");
//	        		return jsonResult(null, 10000, "您的验证码未过期，请查看");
//	        	}
	        }
			logger.info("getIdentCode获取手机验证码, 请填写手机号");
	        return jsonResult(null, 10000, "请填写手机号");
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("getIdentCode获取手机验证码, 系统错误");
			return jsonResult(null, -1, "系统错误"); 
		}
	}
	
	/**
     * @Author zw
     * @Description 校验手机验证码
     * @Date 15:47 2020-03-06
     * @Param
     * @return JsonResult
     **/
    @PostMapping("checkCode")
    public JsonResult checkCode () {
		String param = getParam(request);
		JSONObject json = JSON.parseObject(param);
		String phone = null , code = null;
		if (json.containsKey("phone")) {
			phone = json.get("phone").toString();
		}
		if (json.containsKey("code")) {
			code = json.get("code").toString();
		}

        if(StringUntil.isNull(phone)) {
        	return jsonResult(null, 10000, "手机号不能为空！");
        }
        if(StringUntil.isNull(code)) {
        	return jsonResult(null, 10000, "验证码不能为空！");
        }

        // 验证手机验证码
        if (!VerificationUtil.verificationCode2(phone, code)) {
            return new JsonResult(null, 9001, "手机验证码错误！");
        }

        return new JsonResult();
    }
    
    /**
	 * @Description: 上传图片，保存到ftp上
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveImage")
	public JsonResult saveImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("fileData");
			if(file != null) {
				String originalName = file.getOriginalFilename();
				String puf = originalName.substring(originalName.lastIndexOf("."));
				String pngName = UUIDUtil.getUUID()+puf;
				//ftp上传
				boolean upload = UpUtils.upload(file.getInputStream(), "/certificate/", pngName);
				if(upload) {
					return jsonResult(ftpUrl+"/certificate/"+pngName);
				}else {
					return jsonResult(null, 10000, "上传的文件失败");
				}
			}else {
				return jsonResult(null, 10000, "请选择上传的文件");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误"); 
		}
	}
	
}
