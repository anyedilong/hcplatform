package com.java.moudle.common.controller;


import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.SysUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.ras.AesUtil;
import com.java.until.ras.BCrypt;

/**
 * <br>
 * <b>功能：</b>LoginController<br>
 * <b>作者：</b>blt<br>
 * <b>版权所有：<b>版权所有(C) 2016，blt<br>
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

	@Inject
	private RestTemplate restTemplate;
	@Inject
	private SysUserService userService;
	@Value("${encryStr}")
    private String encryStr;
	
	
	/**
	 * @Description 根据用户名和秘密从授权中心获取授权码
	 * @return
	 * @author tz
	 */
	@RequestMapping("oauth")
	public JsonResult oauth() {
		String username = "";
		String password = "";
		try {
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			username = paramObj.getString("username");
			password = paramObj.getString("password");
			if(StringUntil.isNull(username)) {
				return jsonResult(null, 10000, "用户名不能为空");
			}
			if(StringUntil.isNull(password)) {
				return jsonResult(null, 10000, "密码不能为空");
			}
			String url = "http://oa/oa/oauth/token?grant_type=password&username="+username+"&password="+password+"&client_id=sysbenefit&client_secret=123456";
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			String token = JSONObject.parseObject(body).getString("access_token");
			return jsonResult(token);
		}catch(Exception e) {
			e.printStackTrace();
			SysUser info = userService.getUserInfoByName(username);
			if(info == null) {
				return jsonResult(null, 10000, "您输入的用户名不正确！"); 
			}else if(!BCrypt.checkpw(password, info.getPassword())) {
				return jsonResult(null, 10000, "您输入的密码不正确！");
			}else {
				return jsonResult(null, 10000, "认证失败！"); 
			}
		}
	}
	
	/**
	 * @Description 根据用户名查询用户信息
	 * @return
	 * @author tz
	 */
	@RequestMapping("getUserInfo")
	public JSONObject getUserInfo(String username) {
		try {
			SysUser info = userService.queryInfoByCon("", username);
			if(info != null) {
				info.setAuthorities("admin_role");
			}
			return JSONObject.parseObject(JSON.toJSONString(info));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Value("${identCode}")
	private String identCode;
	
	/**
	 * @Description 用户退出
	 * @return
	 * @author tz
	 */
	@RequestMapping("exit")
	public JsonResult exit() {
		try {
			Map<String, Object> map = SysUtil.getSecurityKey(request);
			if(map == null) {
				return jsonResult(null, 1004,"用户未登录"); 
			}
			// 客户端请求的缓存key
			String token = (String) map.get("securitytoken");
			String userType = (String) map.get("userType");
			String securitykey = (String) map.get("securitykey");
			// 验证缓存中无数据，需要重新获取缓存信息
			String jsonStr = CacheUntil.get(RedisCacheEmun.USER_CACHE, securitykey, String.class);
			if (null == jsonStr) {
				return jsonResult(null, 1004,"用户未登录"); 
			}
			//从缓存中获取token (key的值待定)
			String refreshToken = "refresh_auth:" + JSONObject.parseObject(jsonStr).getString("refreshToken");
			//删除权限中心的缓存用户信息
			CacheUntil.delete(RedisCacheEmun.USER_CACHE, refreshToken);
			//删除权限中心的缓存token信息
			CacheUntil.delete(RedisCacheEmun.USER_CACHE, securitykey);
			
			if("1".equals(userType)) {
				CacheUntil.delete(RedisCacheEmun.USER_CACHE, token);
			}else {
				String param = getParam(request);
				JSONObject paramObj = JSONObject.parseObject(param);
				String clientIp = paramObj.getString("clientIp");
			 	if(!StringUntil.isNull(clientIp)) {
			 		String key = AesUtil.aesEncrypt(clientIp+identCode);
				 	if(!StringUntil.isNull(key)) {
				 		//删除单点登录的缓存的用户账号
				 		CacheUntil.delete(RedisCacheEmun.USER_CACHE, key);
				 	}
			 	}
			 	//删除缓存的用户信息
				CacheUntil.delete(RedisCacheEmun.USER_CACHE, securitykey+encryStr);
			}
			return jsonResult();
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null,-1,"系统错误"); 
		}
	}
	
}
