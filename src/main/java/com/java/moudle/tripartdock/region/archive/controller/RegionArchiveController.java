package com.java.moudle.tripartdock.region.archive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.archive.service.RegionArchiceService;
import com.java.until.StringUntil;


/**
 * @ClassName: RegionArchiveController
 * @Description: 档案对接接口
 * @author Administrator
 * @date 2019年9月6日
 */
@RestController
@RequestMapping("/health/archive")
public class RegionArchiveController extends BaseController {

	@Autowired
	private RegionArchiceService archiceService;
	
	
	/**
	 * @Description: 查询档案的详情（健康浏览器-管理员）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getArchiveDetailBySfzh")
	public JsonResult getArchiveDetailBySfzh() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String sfzh = paramObj.getString("sfzh");
			if(StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "身份证号为空");
			}
			return jsonResult(archiceService.getArchiveDetailBySfzh(sfzh));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询病史信息的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getBSxxDetail")
	public JsonResult getBSxxDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String jmId = paramObj.getString("jmId");
			if(StringUntil.isNull(jmId)) {
				return jsonResult(null, 10001, "居民id为空");
			}
			return jsonResult(archiceService.getBSxxDetail(jmId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询家族史信息的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJZSxxDetail")
	public JsonResult getJZSxxDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String jmId = paramObj.getString("jmId");
			if(StringUntil.isNull(jmId)) {
				return jsonResult(null, 10001, "居民id为空");
			}
			return jsonResult(archiceService.getJZSxxDetail(jmId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询生活环境信息的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getSHfsDetail")
	public JsonResult getSHfsDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String jmId = paramObj.getString("jmId");
			if(StringUntil.isNull(jmId)) {
				return jsonResult(null, 10001, "居民id为空");
			}
			return jsonResult(archiceService.getSHfsDetail(jmId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 通过身份证号获取人群类型
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("queryRqlxBySfzh")
	public JsonResult queryRqlxBySfzh() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String sfzh = paramObj.getString("sfzh");
			if(StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "身份证号为空");
			}
			String rqlx = archiceService.queryRqlxBySfzh(sfzh);
			if(!StringUntil.isNull(rqlx)) {
				return jsonResult(JSONObject.parse(rqlx));
			}
			return jsonResult();
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
}
