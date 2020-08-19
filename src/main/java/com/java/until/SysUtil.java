package com.java.until;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.moudle.system.dto.LoginInfoDto;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.ras.AesUtil;



public class SysUtil {

	/**
	 * <li>解析前端加密的授权</li> 
	 * <li>方法名称:getSecurityKey</li>
	 * <li>参数:@param client,username,token
	 * <li>参数:@return</li>
	 */
	public static final Map<String, Object> getSecurityKey(ServletRequest request) {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String token = httpRequest.getHeader("authToken");
			
			if (!StringUntil.isNull(token) && !"undefined".equals(token)) {
				//待授权中心完善后修改
				String decrypt = AesUtil.aesDecrypt(token);
				String[] decyptArr = decrypt.split(",");
				if(decyptArr.length == 4) {
					//获取redis里面的key（client+username）
					resultMap.put("securitykey", decyptArr[0] + decyptArr[1]);
					//登录验证中心获取的token
					resultMap.put("securitytoken", decyptArr[2]);
					resultMap.put("userType", decyptArr[3]);
					return resultMap;
				}
			}
		}catch(Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * 	获取登录用户信息
	 * @return
	 */
	public static LoginInfoDto sysUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> map = SysUtil.getSecurityKey(request);
			if(map == null) {
				return null;
			}
			//获取登录用户信息，为空查询数据库并保存到redis中
			String token = (String) map.get("securitytoken");
			String userType = (String) map.get("userType");
			String securitykey = (String) map.get("securitykey");
			if("1".equals(userType)) {
				return CacheUntil.get(RedisCacheEmun.USER_CACHE, token, LoginInfoDto.class);
			}else {
				return CacheUntil.get(RedisCacheEmun.USER_CACHE, securitykey+"b8a12d6d58a24601bb98760c50a60735", LoginInfoDto.class);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * 	更新缓存中登录用户信息
	 * @return
	 */
	public static boolean updateCaUser(HttpServletRequest request, LoginInfoDto user) {
		try {
			Map<String, Object> map = SysUtil.getSecurityKey(request);
			if(map == null) {
				return false;
			}
			//获取登录用户信息，为空查询数据库并保存到redis中
			String token = (String) map.get("securitytoken");
			String userType = (String) map.get("userType");
			String securitykey = (String) map.get("securitykey");
			if("1".equals(userType)) {
				CacheUntil.put(RedisCacheEmun.USER_CACHE, token, user);
			}else {
				CacheUntil.put(RedisCacheEmun.USER_CACHE, securitykey+"b8a12d6d58a24601bb98760c50a60735", user);
			}
			return true;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return false;
	}
}
