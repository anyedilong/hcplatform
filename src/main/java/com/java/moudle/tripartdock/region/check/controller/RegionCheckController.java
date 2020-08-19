package com.java.moudle.tripartdock.region.check.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.check.service.RegionCheckService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * @ClassName: RegionCheckController
 * @Description: 三方对接 保存体检信息
 * @author Administrator
 * @date 2019年7月29日
 */
@RestController
@RequestMapping("/health/check")
public class RegionCheckController extends BaseController {

	@Autowired
	private RegionCheckService checkService;
	
	
	/**
	 *  健康体检对比
	 * @return
	 * @author lnz
	 * @date 2020-03-05 09:41:26
	 */
	@RequestMapping("hmjktjlb")
	public JsonResult getFirstFjhDetail() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh = paramJson.getString("sfzh");
			if (StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "参数身份证号不能为空");
			}
			String jcrq = paramJson.getString("jcrq");
			jcrq = StringUntil.isNull(jcrq) ? "" : jcrq;
			Map<String, Object> resultMap = checkService.getFirstFjhDetail(sfzh, jcrq);
			return jsonResult(resultMap);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	
	/**
	 * @Description: 获取体检的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckPage")
	public JsonResult getCheckPage() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//证件编号
			String jmId = paramObj.getString("jmId");
			// 分页
			Integer pageSize = 10, pageNo = 1;
			JSONObject pageObj = paramObj.getJSONObject("page");
			if (null != pageObj) {
				pageSize = StringUntil.toInteger(pageObj.getString("pageSize"));// 页大小
				pageNo = StringUntil.toInteger(pageObj.getString("pageNo"));// 页索引
			}
			PageModel page = new PageModel(pageNo, pageSize);
			return jsonResult(checkService.getCheckPage(page, jmId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 查询体检的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckDetail")
	public JsonResult getCheckDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getCheckDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取健康检查详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJkjcDetail")
	public JsonResult getJkjcDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("tjId", tjId);

			return jsonResult(checkService.getJkjcDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取健康问题详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJkwtbDetail")
	public JsonResult getJkwtbDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getJkwtbDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取评估指导详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getPgzdjlbDetail")
	public JsonResult getPgzdjlbDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getPgzdjlbDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取生活方式详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getShfsDetail")
	public JsonResult getShfsDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getShfsDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取辅助检查详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getXzjcjlbDetail")
	public JsonResult getXzjcjlbDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getXzjcjlbDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取一般症状详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getYbzzbDetail")
	public JsonResult getYbzzbDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getYbzzbDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取脏器功能详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getZqgnbDetail")
	public JsonResult getZqgnbDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getZqgnbDetail(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取家族病床史详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJzbcsList")
	public JsonResult getJzbcsList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getJzbcsList(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取接种史详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJzsList")
	public JsonResult getJzsList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getJzsList(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取药剂记录详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getYjjlbList")
	public JsonResult getYjjlbList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getYjjlbList(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取住院史详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getZysList")
	public JsonResult getZysList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String tjId = paramObj.getString("tjId");
			if(StringUntil.isNull(tjId)) {
				return jsonResult(null, 10001, "体检id为空");
			}

			return jsonResult(checkService.getZysList(tjId));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	

}
