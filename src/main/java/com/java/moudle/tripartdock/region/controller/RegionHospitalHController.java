package com.java.moudle.tripartdock.region.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.service.RegionHospitalService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;

/**
 * @ClassName: OutpatientController
 * @Description: 三方对接 查询住院信息
 * @author Administrator
 * @date 2019年9月12日
 */
@RestController
@RequestMapping("/region/hospital")
public class RegionHospitalHController extends BaseController {

    @Inject
    private RegionHospitalService hospitalService;

    /**
	 * @Description: 获取住院的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalPage")
	public JsonResult getHospitalPage() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			//证件编号
			String sfzh = paramObj.getString("sfzh");
			String pageSize = StringUntil.isNull(paramObj.getString("pageSize")) ? "10" : paramObj.getString("pageSize");
			String pageNo = StringUntil.isNull(paramObj.getString("pageNo")) ? "1" : paramObj.getString("pageNo");
			
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("sfzh", sfzh);
			paramMap.put("pageSize", pageSize+"");
			paramMap.put("pageNo", pageNo+"");
			PageModel page = new PageModel(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			
			return jsonResult(hospitalService.getHospitalPage(page, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 查询住院详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalDetail")
	public JsonResult getHospitalDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "住院id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("id", id);

			return jsonResult(hospitalService.getHospitalDetail(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院花费明细的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalCostList")
	public JsonResult getHospitalCostList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String zyId = paramObj.getString("zyId");
			if(StringUntil.isNull(zyId)) {
				return jsonResult(null, 10001, "住院id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("zyId", zyId);

			return jsonResult(hospitalService.getHospitalCostList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院诊断的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalZDList")
	public JsonResult getHospitalZDList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String zyId = paramObj.getString("zyId");
			if(StringUntil.isNull(zyId)) {
				return jsonResult(null, 10001, "住院id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("zyId", zyId);

			return jsonResult(hospitalService.getHospitalZDList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取住院的病案首页
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalBasy")
	public JsonResult getHospitalBasy() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String arId = paramObj.getString("arId");
			String orgCode = paramObj.getString("orgCode");
			if(StringUntil.isNull(arId)) {
				return jsonResult(null, 10001, "住院登记码不能为空");
			}
			if(StringUntil.isNull(orgCode)) {
				return jsonResult(null, 10001, "机构code不能为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("id", arId);
			paramMap.put("orgCode", orgCode);
			String result = hospitalService.getHospitalBasy(paramMap);
			return jsonResult(JSONObject.parseObject(result));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 住院影像dcm文件转换为图片
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCoverImageFormat")
	public JsonResult getCoverImageFormat() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String imageId = paramObj.getString("imageId");
			String imagePath = paramObj.getString("imagePath");
			if(StringUntil.isNull(imageId)) {
				return jsonResult(null, 10001, "影像id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("imageId", imageId);
			paramMap.put("imagePath", imagePath);

			return jsonResult(hospitalService.getCoverImageFormat(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院详情（手机app）
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getAppHospitalDetail")
	public JsonResult getAppHospitalDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "住院id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("id", id);

			return jsonResult(hospitalService.getAppHospitalDetail(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院医嘱列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalOrderList")
	public JsonResult getHospitalOrderList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String zyId = paramObj.getString("zyId");
			if(StringUntil.isNull(zyId)) {
				return jsonResult(null, 10001, "住院id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("zyId", zyId);

			return jsonResult(hospitalService.getHospitalOrderList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院医嘱详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalOrderDetail")
	public JsonResult getHospitalOrderDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "住院医嘱标识为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("id", id);

			return jsonResult(hospitalService.getHospitalOrderDetail(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询住院医嘱明细列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getHospitalOrderItemList")
	public JsonResult getHospitalOrderItemList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String zyId = paramObj.getString("zyId");
			if(StringUntil.isNull(zyId)) {
				return jsonResult(null, 10001, "住院标识为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("zyId", zyId);

			return jsonResult(hospitalService.getHospitalOrderItemList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
}
