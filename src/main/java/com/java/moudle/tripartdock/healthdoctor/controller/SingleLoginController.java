package com.java.moudle.tripartdock.healthdoctor.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.domain.SysCustormerLnr;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.service.SysCustormerLnrService;
import com.java.moudle.system.service.SysCustormerService;
import com.java.moudle.system.service.SysLnrService;
import com.java.until.SysUtil;
import com.java.until.UUIDUtil;
import com.java.until.ras.BCrypt;


@RestController
@RequestMapping("/healthdoctor/appoint")
public class SingleLoginController  extends BaseController  {

	@Inject
	private RestTemplate restTemplate;
	@Inject
	private SysCustormerService custormerService;
	
	/**
	 * @Description 验证登陆者是否在预约挂号系统注册过，并获取验证码
	 * @return
	 * @author tz
	 */
	@RequestMapping("oauth")
	public JsonResult oauth() {
		try {
			
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if(user == null) {
				return jsonResult(null, 10001, "请先登录");
			}
			SysCustormer custormer = custormerService.get(user.getId());
			//获取登录用户是否在预约挂号系统注册过
			String url = PropertiesUtil.getRegister("getAregUser");
			url = url+"?username="+custormer.getUsername()+"&pwd="+custormer.getPwd();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			String code = JSONObject.parseObject(coverJsonpData(body)).getString("retCode");
			String password = "";
			if("0".equals(code)) {
				password = JSONObject.parseObject(coverJsonpData(body)).getString("data");
			}else {
				return jsonResult(null, 10001, "预约挂号系统正在维护中");
			}
			
			String oauthUrl = "http://oa/oa/oauth/token?grant_type=password&username="+custormer.getUsername()+"&password="+password+"&client_id=appointment&client_secret=123456";
			ResponseEntity<String> postForEntity1 = restTemplate.getForEntity(oauthUrl, String.class);
			String body1 = postForEntity1.getBody();
			String token = JSONObject.parseObject(body1).getString("access_token");
			return jsonResult(token);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "认证失败"); 
		}
	}
	
	@Inject
	private SysLnrService sysLnrService;
	@Inject
	private SysCustormerLnrService sysCustormerLnrService;
	
	/**
     * 	验证登陆者是否在预约挂号系统注册过, 没有自动注册
     */
    @RequestMapping("syncHcCustomer")
	public JsonResult syncHcCustomer(String oldUsername, String newUsername, String pwd, String name, String sfzh) {
    	try {
    		//确定预约人是否已是本系统用户
    		SysCustormer info = custormerService.queryInfoByCon("", oldUsername);
    		if (info == null) {
    			SysCustormer custormer = new SysCustormer();
    			custormer.setId(UUIDUtil.getUUID());
    			custormer.setPwd(pwd);
    			custormer.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
    			custormer.setCreateTime(new Date());
    			custormer.setStatus("0");
    			custormer.setIsRealName(0);
    			custormer.setUsername(newUsername);
    			custormer.setPhone(newUsername);
    			custormer.setSfzh(sfzh);
    			custormer.setName(newUsername);
	        	custormerService.save(custormer);
	        	/** 默认添加本人为联系人 */

	            // 封装联系人
	            SysLnr sysLnr = new SysLnr();
	            sysLnr.setId(UUIDUtil.getUUID());
	            sysLnr.setName(custormer.getName());
	            sysLnr.setPhone(custormer.getPhone());
	            sysLnr.setSfzh(custormer.getSfzh());

	            // 封装关联表
	            SysCustormerLnr sysCustormerLnr = new SysCustormerLnr();
	            sysCustormerLnr.setId(UUIDUtil.getUUID());
	            sysCustormerLnr.setCustormerId(custormer.getId());
	            sysCustormerLnr.setLnrId(sysLnr.getId());

	            // 默认为 1本人
	            sysCustormerLnr.setGx("1");
	            // 保存
	            sysLnrService.save(sysLnr);
	            sysCustormerLnrService.save(sysCustormerLnr);
    		}else {
    			info.setPwd(pwd);
    			info.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
    			info.setUsername(newUsername);
    			info.setPhone(newUsername);
    			custormerService.save(info);
    			
    			SysLnr lnr = sysLnrService.getLnrInfo(sfzh);
    			lnr.setPhone(newUsername);
    			sysLnrService.save(lnr);
    		}
    		return jsonResult();
    	} catch (Exception e) {
        	return jsonResult(e.toString(), -1, "同步预约挂号用户异常！");
    	}
	}
	
	/**
	 * @Description: 去掉jsonp格式多余的字符
	 * @param @param jsonpDataStr
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String coverJsonpData(String jsonpDataStr) {
		String str = jsonpDataStr.substring(5, jsonpDataStr.length()-1);
		return str;
	}
}
