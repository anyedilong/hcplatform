package com.java.moudle.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysNews;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.SysNewsDto;
import com.java.moudle.system.service.SysNewsService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 系统消息管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/system")
public class SysNewsController  extends BaseController {
    
	@Inject
    private SysNewsService newsService;
	@Inject
    private SysUserService userService;

	/**
	 * @Description: 获取系统消息的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
    @RequestMapping("list")
	public JsonResult getNewsPage(){
    	try {
    		String param = getParam(request);
    		SysNewsDto news = JSONObject.parseObject(param, SysNewsDto.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		
    		//权限判断
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		if(user != null) {
    			List<String> childs = userService.getUserChilds(user.getId(), user.getRole().getRoleType());
    			news.setUserIds(childs);
    			news.setRoleType(user.getRole().getRoleType());
    		}else {
    			return jsonResult(null, 10000, "请先进行登录操作！");
    		}
    		newsService.getNewsPage(news, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
	}
    
    /**
   	 * @Description: 保存系统消息
   	 * @param @return
   	 * @return JsonResult
   	 * @throws
   	 */
       @RequestMapping("news")
   	public JsonResult news(){
       	try {
       		String param = getParam(request);
    		SysNews news = JSONObject.parseObject(param, SysNews.class);
    		if(StringUtils.isNull(news.getTitle())) {
				return jsonResult(null, 10000, "请输入系统消息标题！");
			}
    		//判断标题是否为空
    		int count = newsService.getNewsCount(news);
    		if(count > 0) {
				return jsonResult(null, 10000, "您输入的系统标题重复，请重新输入！");
			}
    		if(StringUtils.isNull(news.getSynopsis())) {
				return jsonResult(null, 10000, "请输入系统消息简介！");
			}
    		if(StringUtils.isNull(news.getContent())) {
				return jsonResult(null, 10000, "请输入系统消息内容！");
			}
    		if(StringUntil.isNull(news.getId())) {
    			news.setId(UUIDUtil.getUUID());
    		}
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		news.setCreateUser(user.getId());
    		news.setCreateTime(new Date());
    		news.setPubTime(new Date());
    		news.setStatus("1");
    		news.setDeleteFlg("0");
    		newsService.save(news);
       		return jsonResult();
       	}catch(Exception e) {
       		e.printStackTrace();
       		return jsonResult(null, -1, "系统错误");
       	}
   	}
       
   /**
  	 * @Description: 更新系统消息状态
  	 * @param @return
  	 * @return JsonResult
  	 * @throws
  	 */
      @RequestMapping("savestatus")
  	public JsonResult savestatus(){
      	try {
      		String param = getParam(request);
	   		SysNews news = JSONObject.parseObject(param, SysNews.class);
	   		if(StringUtils.isNull(news.getId())) {
				return jsonResult(null, 10000, "缺少参数：标识");
			}
    		if(StringUtils.isNull(news.getStatus())) {
				return jsonResult(null, 10000, "缺少参数：状态");
			}
	   		Map<String, String> resultMap = newsService.upateNewsStatus(news);
	   		int code = Integer.parseInt(resultMap.get("code"));
	   		String msg = resultMap.get("msg");
      		return jsonResult(null, code, msg);
      	}catch(Exception e) {
      		e.printStackTrace();
      		return jsonResult(null, -1, "系统错误");
      	}
  	}
      
    /**
	 * @Description: 查看系统消息详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
    @RequestMapping("xq")
	public JsonResult getNewsDetail(){
    	try {
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
	   		if(StringUtils.isNull(id)) {
				return jsonResult(null, 10000, "缺少参数：标识");
			}
	   		SysNewsDto news = newsService.getNewsDetail(id);
	    	return jsonResult(news);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
	}
    
    /**
	 * @Description: 删除系统消息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
    @RequestMapping("delete")
	public JsonResult delete(){
    	try {
    		String param = getParam(request);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		String id = paramObj.getString("id");
	   		if(StringUtils.isNull(id)) {
				return jsonResult(null, 10000, "缺少参数：标识");
			}
	   		SysNews news = newsService.get(id);
	   		news.setDeleteFlg("1");
	   		newsService.save(news);
    		return jsonResult();
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
	}
       
    /**
   	 * @Description: 获取系统消息(网站端)
   	 * @param @return
   	 * @return JsonResult
   	 * @throws
   	 */
    @RequestMapping("/front/getNewsPage")
   	public JsonResult getFrontNewsPage(){
    	   try {
       		String param = getParam(request);
       		SysNews news = JSONObject.parseObject(param, SysNews.class);
       		JSONObject paramObj = JSONObject.parseObject(param);
       		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
       		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
       		PageModel page = new PageModel(pageNo, pageSize);
       		newsService.getFrontNewsPage(news, page);
       		return jsonResult(page);
       	}catch(Exception e) {
       		e.printStackTrace();
       		return jsonResult(null, -1, "系统错误");
       	}
   	}
       
   /**
   	 * @Description: 查看系统消息详情(网站端)
   	 * @param @return
   	 * @return JsonResult
   	 * @throws
   	 */
    @RequestMapping("/front/getNewsInfo")
   	public JsonResult getNewsInfo(){
       	try {
       		String param = getParam(request);
       		JSONObject paramObj = JSONObject.parseObject(param);
       		String id = paramObj.getString("id");
   	   		if(StringUtils.isNull(id)) {
   				return jsonResult(null, 10000, "缺少参数：标识");
   			}
   	   		SysNewsDto news = newsService.getNewsDetail(id);
   	    	return jsonResult(news);
       	}catch(Exception e) {
       		e.printStackTrace();
       		return jsonResult(null, -1, "系统错误");
       	}
   	}

}
