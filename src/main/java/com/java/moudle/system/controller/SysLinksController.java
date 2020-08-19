package com.java.moudle.system.controller;

import javax.inject.Inject;
import javax.print.DocFlavor;

import com.alibaba.fastjson.JSON;
import com.java.moudle.system.dto.SysLinksDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysLinks;
import com.java.moudle.system.service.SysLinksService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 联系我们管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/links")
public class SysLinksController extends BaseController {
    
	@Inject
    private SysLinksService linksService;

	@RequestMapping("getLinksPage")
    public JsonResult getLinksPage() {
    	try {
    		String param = getParam(request);
    		SysLinks links = JSONObject.parseObject(param, SysLinks.class);
    		JSONObject paramObj = JSONObject.parseObject(param);
    		Integer pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? 1 : paramObj.getInteger("pageNo");
    		Integer pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? 10 : paramObj.getInteger("pageSize");
    		PageModel page = new PageModel(pageNo, pageSize);
    		linksService.getLinksPage(links, page);
    		return jsonResult(page);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }

    /**
	 * @Description 获取友情链接列表
	 * @Author zw
     * @Date 15:51 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getLinklist")
    public JsonResult getLinklist () {
		String param = getParam(request);
		logger.info("getLinklist获取友情链接列表入参:[{}]", param);
		SysLinksDto dto = JSON.parseObject(param, SysLinksDto.class);
		PageModel pageModel = new PageModel();
		if (dto.getPageNo() != 0) {
			pageModel.setPageNo(dto.getPageNo());
		}
		if (dto.getPageSize() != 0) {
			pageModel.setPageSize(dto.getPageSize());
		}
		linksService.getLinklist(dto, pageModel);
		logger.info("getLinklist获取友情链接列表出参:[{}]", JSON.toJSONString(pageModel));
		return jsonResult(pageModel);
	}

	/**
	 * @Description 添加友情链接
	 * @Author zw
	 * @Date 16:08 2020-03-18
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("addLink")
	public JsonResult addLink () {
		String param = getParam(request);
		logger.info("addLink添加友情链接入参:[{}]", param);
		SysLinks sysLinks = JSON.parseObject(param, SysLinks.class);
		if (StringUntil.isBlank(sysLinks.getName())) {
			return jsonResult(null, 9001, "链接名称不能为空");
		}
		if (StringUntil.isBlank(sysLinks.getLinkUrl())) {
			return jsonResult(null, 9001, "链接url不能为空");
		}
		JsonResult jsonResult = linksService.addLink(sysLinks);
		logger.info("addLink添加友情链接出参:[{}]", JSON.toJSONString(jsonResult));
		return jsonResult;
	}
	
	/**
	 * @Description 删除链接
	 * @Author zw
	 * @Date 16:20 2020-03-18
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("delLink")
	public JsonResult delLink () {
		String param = getParam(request);
		logger.info("delLink删除链接入参:[{}]", param);
		SysLinks sysLinks = JSON.parseObject(param, SysLinks.class);
		if (StringUntil.isBlank(sysLinks.getId())) {
			return jsonResult(null, 9001, "id不能为空");
		}

		JsonResult jsonResult = linksService.delLink(sysLinks);
		logger.info("delLink删除链接入参:[{}]", JSON.toJSONString(jsonResult));
		return jsonResult;
	}

}
