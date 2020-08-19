package com.java.moudle.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dao.MainDao;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.moudle.system.service.MainService;
import com.java.until.ToJavaUtils;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.encryption.BCrypt;
import com.java.until.ras.AesUtil;

@Named
@Transactional(readOnly = true)
public class MainServiceImpl  implements MainService{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	private MainDao mainDao;
	@Inject
	private RestTemplate restTemplate;
	@Value("${identCode}")
	private String identCode;
	
	@Override
	public JsonResult login(SysCustomerDto dto) {
		try {
	        SysCustomerDto temp = mainDao.getCustomer(dto);
	        if (temp == null)
	            return new JsonResult(null, 9001, "您的手机号码尚未注册，请以验证码方式进行登录！");
//	   
//	        boolean is = BCrypt.checkpw(dto.getPassword(), temp.getPassword());
//	        if (!is)
//	            return new JsonResult(null, 9001, "密码错误！");
	
	        String url = "http://oa/oa/oauth/token?grant_type=password&username=" + dto.getPhone() + "&password=" + 
	        		dto.getPassword() + "&client_id=benefit&client_secret=123456&clientIp="+dto.getClientIp();
            String jsonStr = restTemplate.getForEntity(url, String.class).getBody();
            JSONObject json = JSONObject.parseObject(jsonStr);
            String accessToken = json.getString("access_token");
            
            SysCustormer custormer = mainDao.getCustomerDetail(dto.getPhone());
            Map<String, Object> map = new HashMap<>();
			map.put("token", accessToken);
			map.put("sfzh", custormer.getSfzh());
			map.put("name", custormer.getName());
			map.put("custormerUrl", custormer.getCustormerUrl());
			if (StringUtils.isNotBlank(dto.getOpenId())) {
				custormer.setOpenId(dto.getOpenId());
				mainDao.save(custormer);
			}
            return new JsonResult(map);
        } catch (Exception e) {
        	e.printStackTrace();
        	SysCustormer custormer = mainDao.getCustomerDetail(dto.getPhone());
			if(custormer == null) {
				return new JsonResult(null, 10000, "您输入的用户名不正确，请重新输入！"); 
			}else if(!BCrypt.checkpw(dto.getPassword(), custormer.getPassword())) {
				return new JsonResult(null, 10000, "您输入的密码不正确，请重新输入！");
			}else if("1".equals(custormer.getStatus())){
				return new JsonResult(null, 10000, "账户已被禁用，请联系运营商！");
			}else {
				return new JsonResult(null, 10000, "认证失败！"); 
			}
        }
	}


	//获取验证码
    @Override
    public JsonResult getCode(SysCustomerDto dto) {
        String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, dto.getPhone(), String.class);
        //删除缓存中的验证码
        if ("again".equals(dto.getAgain())) {
            CacheUntil.delete(RedisCacheEmun.USER_CACHE.getRedisTemplate(), dto.getPhone());
        }
        String cacheCode1 = CacheUntil.get(RedisCacheEmun.USER_CACHE, dto.getPhone(), String.class);
        String url = "http://appointment/commontools/getIdentCode?telephone=" + dto.getPhone();
        String jsonStr = null;
        try {
            jsonStr = restTemplate.getForEntity(url, String.class).getBody();
        } catch (Exception e) {
            logger.error("获取验证码失败！", e);
        }
        logger.info("获取手机验证码：" + dto.getPhone() + "/" + jsonStr);
        if (StringUtils.isNotBlank(jsonStr)) {
            jsonStr = jsonStr.substring(jsonStr.indexOf("(") + 1, jsonStr.indexOf(")"));
        }
        JSONObject json = JSONObject.parseObject(jsonStr);
        if (json == null || json.size() == 0)
            return new JsonResult(null, 9001, "获取验证码失败！");
        return new JsonResult(null, json.getInteger("retCode"), json.getString("retMsg"));
    }


	@Override
	public JsonResult yzmlogin(SysCustomerDto dto) {

        SysCustomerDto temp = mainDao.getCustomer(dto);
        if (temp == null)
            return new JsonResult(null, 9001, "账号不存在！");
   
        String code = dto.getCode();
        String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, dto.getPhone(), String.class);
        if (!code.equals(cacheCode))
            return new JsonResult(null, 1001, "手机验证码错误！");
        SysCustormer info = mainDao.get(temp.getId());
        info.setPassword(null);
        SysCustomerDto response = new SysCustomerDto();
        ToJavaUtils.copyFields(info, response);

        String accessToken = "";
        //获取缓存中的授权码
        String str = CacheUntil.get(RedisCacheEmun.USER_CACHE, "resident" + dto.getPhone(), String.class);
        JSONObject tokenJson = JSONObject.parseObject(str);
        //缓存没有授权码重新获取
        if (tokenJson == null || tokenJson.size() == 0) {
            String url = "http://oa/oa/oauth/token?grant_type=password&username=" + dto.getPhone() + 
            		"&password=" + dto.getPassword() + "&client_id=resident&client_secret=123456&clientIp="+dto.getClientIp();
            String jsonStr = null;
            try {
                jsonStr = restTemplate.getForEntity(url, String.class).getBody();
            } catch (Exception e) {
                logger.error("获取token失败！", e);
            }
            logger.info("账号：" + dto.getUsername() + "生成验证信息：" + jsonStr);
            JSONObject json = JSONObject.parseObject(jsonStr);
            if (json == null || json.size() == 0)
                return new JsonResult(null, 9001, "登陆失败！");
            accessToken = json.getString("access_token");
        } else {
            accessToken = tokenJson.getString("accessToken");
        }
        response.setAccessToken(accessToken);

        return new JsonResult(response);
    
	
	}


	@Override
	public SysCustomerDto getResidentInfo(SysCustomerDto customerDto) {
        SysCustomerDto sysCustomerDto = mainDao.getCustomers(customerDto);
        sysCustomerDto.setUsername(sysCustomerDto.getPhone());
        sysCustomerDto.setAuthorities("admin_role");
        sysCustomerDto.setPhone(null);
        return sysCustomerDto;
	}

	@Override
	public JsonResult singleLogin(String clientIp) {
		try {
		 	String key = AesUtil.aesEncrypt(clientIp+identCode);
			String aesUsername = CacheUntil.get(RedisCacheEmun.USER_CACHE, key, String.class);
			if(aesUsername == null) {
				return new JsonResult(null);
			}
			String username = AesUtil.aesDecrypt(aesUsername);
			if(username == null) {
				CacheUntil.delete(RedisCacheEmun.USER_CACHE, key);
				return new JsonResult(null);
			}
			SysCustormer user = getCustomerDto(username);
			String url = "http://oa/oa/oauth/token?grant_type=password&username=" + user.getPhone() + "&password=" + 
					user.getPwd() + "&client_id=benefit&client_secret=123456&clientIp="+clientIp;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			String token = JSONObject.parseObject(body).getString("access_token");
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("token", token);
			resultMap.put("username", username);
			return new JsonResult(resultMap);
		}catch(Exception e) {
			e.printStackTrace();
			return new JsonResult(null, 0, "");
		}
	}
	
	/**
	 * @Description 根据用户名查询用户信息
	 * @return
	 * @author tz
	 */
	private SysCustormer getCustomerDto(String username) {
		try {
			SysCustormer info = mainDao.getCustomerDetail(username);
			return info;
		}catch(Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
}
