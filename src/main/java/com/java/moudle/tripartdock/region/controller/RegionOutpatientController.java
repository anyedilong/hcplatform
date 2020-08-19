package com.java.moudle.tripartdock.region.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.tripartdock.region.service.RegionOutpatientService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;


/**
 * @ClassName: OutpatientController
 * @Description: 三方对接 查询门诊信息
 * @author Administrator
 * @date 2019年9月12日
 */
@RestController
@RequestMapping("/region/outpatient")
public class RegionOutpatientController extends BaseController {

    @Inject
    private RegionOutpatientService outpatientService;

    /**
	 * @Description: 获取门诊的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOutpatientPage")
	public JsonResult getOutpatientPage() {
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
			PageModel page = new PageModel(Integer.parseInt(pageNo), Integer.parseInt(pageNo));
			return jsonResult(outpatientService.getOutpatientPage(page, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}

	/**
	 * @Description: 查询门诊详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOutpatientDetail")
	public JsonResult getHospitalDetail() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "门诊id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("medicalRecordId", id);

			return jsonResult(outpatientService.getOutpatientDetail(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询门诊处方的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOutpatientCfList")
	public JsonResult getOutpatientCfList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String mzId = paramObj.getString("mzId");
			if(StringUntil.isNull(mzId)) {
				return jsonResult(null, 10001, "门诊id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("mzId", mzId);

			return jsonResult(outpatientService.getOutpatientCfList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
    
	/**
	 * @Description: 查询门诊诊断的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getOutpatientZDList")
	public JsonResult getOutpatientZDList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String mzId = paramObj.getString("mzId");
			if(StringUntil.isNull(mzId)) {
				return jsonResult(null, 10001, "门诊id为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("mzId", mzId);

			return jsonResult(outpatientService.getOutpatientZDList(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 门诊影像dcm文件转换为图片
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

			return jsonResult(outpatientService.getCoverImageFormat(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验报告的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJyList")
	public JsonResult getJyList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String sfzh = paramObj.getString("sfzh");
			String pageNo = paramObj.getString("pageNo");
			String pageSize = paramObj.getString("pageSize");
			if(StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "身份证号不能为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("sfzh", sfzh);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			String result = outpatientService.getJyList(paramMap);
			return jsonResult(JSONObject.parseObject(result));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检验报告的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJyXq")
	public JsonResult getJyXq() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String itemType = paramObj.getString("itemType");
			String id = paramObj.getString("id");
			String type = paramObj.getString("code");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "就诊标识不能为空");
			}
			if(StringUntil.isNull(itemType)) {
				return jsonResult(null, 10001, "检验类型不能为空");
			}
			if(StringUntil.isNull(type)) {
				return jsonResult(null, 10001, "就诊类型不能为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("itemType", itemType);
			paramMap.put("id", id);
			paramMap.put("type", type);
			String result = outpatientService.getJyDetail(paramMap);
			return jsonResult(JSONObject.parseArray(result));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检查报告的列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJcList")
	public JsonResult getJcList() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String sfzh = paramObj.getString("sfzh");
			String pageNo = paramObj.getString("pageNo");
			String pageSize = paramObj.getString("pageSize");
			if(StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "身份证号不能为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("sfzh", sfzh);
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			String result = outpatientService.getJcList(paramMap);
			return jsonResult(JSONObject.parseObject(result));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取检查报告的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getJcXq")
	public JsonResult getJcXq() {
		try {
			// 获取参数
			String param = getParam(request);
			JSONObject paramObj = JSONObject.parseObject(param);
			String id = paramObj.getString("id");
			String type = paramObj.getString("code");
			String imageType = paramObj.getString("imageType");
			if(StringUntil.isNull(id)) {
				return jsonResult(null, 10001, "就诊标识不能为空");
			}
			if(StringUntil.isNull(type)) {
				return jsonResult(null, 10001, "就诊类型不能为空");
			}
			if(StringUntil.isNull(imageType)) {
				return jsonResult(null, 10001, "检验类型不能为空");
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("id", id);
			paramMap.put("type", type);
			paramMap.put("imageType", imageType);
			String result = outpatientService.getJcDetail(paramMap);
			return jsonResult(JSONObject.parseObject(result));
		} catch (Exception e) {
			e.printStackTrace();
			return jsonResult(null, 90001, e.getMessage());
		}
	}
}
