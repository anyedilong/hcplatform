package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.moudle.system.service.MainService;
import com.java.until.StringUtils;
/**
 * 登录
 * @author lnz
 * @date 2020-03-04 01:16:00
 */
@RestController
@RequestMapping("main")
public class MainController extends BaseController{
	
	@Inject
	private MainService mainService;
	
	 /**
	      *    登录
	  * @return
	  * @author lnz
	  * @date 2020-03-04 01:59:55
	  */
    @RequestMapping("login")
    public JsonResult login () {
        String param = getParam(request);
        logger.info("居民登陆请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (json == null || json.size() > 0) {
            SysCustomerDto dto = JSON.toJavaObject(json, SysCustomerDto.class);
            if (StringUtils.isBlank(dto.getPhone()))
                return new JsonResult(null, 9001, "手机号不能为空！");
            if (StringUtils.isBlank(dto.getPassword()))
                return new JsonResult(null, 9001, "密码不能为空！");
            JsonResult res = mainService.login(dto);
            logger.info("居民登陆返回：" + JSON.toJSONString(res));
            return res;
        }
        return jsonResult(null, 9001, "参数不能为空！");
    }
    /**
     * @Description: 授权中心回调查询用户接口
     * @param @param username
     * @param @return
     * @return JSONObject
     * @throws
     */
    @RequestMapping("loginfor")
    public JSONObject getResidentInfo (String username){
        logger.info("获取居民信息请求参数：" + username);
        if (StringUtils.isNotBlank(username)) {
        	JSONObject response = new JSONObject();
            SysCustomerDto customerDto = new SysCustomerDto();
            customerDto.setPhone(username);
            SysCustomerDto info = mainService.getResidentInfo(customerDto);
            logger.info("获取居民信息返回：" + JSON.toJSONString(info));
            response = JSONObject.parseObject(JSON.toJSONString(info));
            return response;
        }
        return null;
    }
    
    /**
	 * @Description 验证用户是否在惠民平台登录过，自动登录
	 * @return
	 * @author tz
	 */
	@RequestMapping("singleLogin")
	public JsonResult singleLogin() {
		String param = getParam(request);
		JSONObject paramObj = JSONObject.parseObject(param);
		String clientIp = paramObj.getString("clientIp");
		if(StringUtils.isNull(clientIp)) {
			return null;
		}
		return mainService.singleLogin(clientIp);
	}
    
    /**
     * 	手机验证码登录
     * @return
     * @author lnz
     * @date 2020-03-04 01:59:36
     */
    @RequestMapping("yzmlogin")
    public JsonResult yzmlogin () {
        String param = getParam(request);
        logger.info("居民登陆请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (json == null || json.size() > 0) {
            SysCustomerDto dto = JSON.toJavaObject(json, SysCustomerDto.class);
            if (StringUtils.isBlank(dto.getPhone()))
                return new JsonResult(null, 9001, "手机号不能为空！");
            if (StringUtils.isBlank(dto.getCode()))
                return new JsonResult(null, 9001, "验证码不能为空！");
            JsonResult res = mainService.yzmlogin(dto);
            logger.info("居民登陆返回：" + JSON.toJSONString(res));
            return res;
        }
        return jsonResult(null, 9001, "参数不能为空！");
    }
    /**
     * 	获取手机验证码
     * 
     * @return
     * @author lnz
     * @date 2020-03-04 02:14:23
     */
    @PostMapping("getCode")
    public JsonResult getCode () {
        String param = getParam(request);
        logger.info("获取手机验证码请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (json == null || json.size() > 0) {
            SysCustomerDto dto = JSON.toJavaObject(json, SysCustomerDto.class);
            if (StringUtils.isBlank(dto.getPhone()))
                return new JsonResult(null, 9001, "手机号不能为空！");
            JsonResult res = mainService.getCode(dto);
            logger.info("获取手机验证码返回：" + JSON.toJSONString(res));
            return res;
        }
        return jsonResult(null, 9001, "参数不能为空！");
    }
    
}
