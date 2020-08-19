package com.java.moudle.bphs.check.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dto.InitDictDto;
import com.java.until.DictUtil;
import com.java.until.StringUntil;
import com.java.until.http.HttpUtil;
import com.java.until.resthttp.RestTemplateUtils;

@RestController
@RequestMapping("jktj")
public class JktjController extends BaseController{
	
	@Inject
	private RestTemplate restTemplate;

	// 1 老公卫 2 新公卫
	private int flag = 1;
	
	/**
	 *  健康体检表
	 * @return
	 * @author lnz
	 * @date 2020-03-05 09:41:26
	 */
	@SuppressWarnings("unchecked")
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
			String paramStr = "{\"jcrq\":\""+jcrq+"\",\"sfzh\":"+sfzh+",\"pageNo\":\"1\",\"pageSize\":\"2\"}";
			if (sfzh != null) {
				if (flag == 1) {
					Map<String, Object> resultMap = new HashMap<>();
					//存储最新体检数据的容器
					Map<String, Object> newly = new HashMap<>();
					//存储上一次体检数据的容器
					Map<String, Object> old = new HashMap<>();
					//记录人群类型
					String rqlx = "";
					//查询最近两条体检
					String resStr = HttpUtil.doPost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCompareCheckInfo"), paramStr);
					JSONObject result = JSONObject.parseObject(resStr);
			        String data = result.get("data").toString();
			        if(!StringUntil.isNull(data)) {
			        	data = HttpUtil.lineToHump(data);
			        	JSONObject resultData = JSON.parseObject(data);
			        	if(resultData.containsKey("rqlx")) {
			        		rqlx = resultData.getString("rqlx");
			        	}
			        	if(resultData.containsKey("newly")) {
			        		Map<String, Object> temp = (Map<String, Object>)resultData.get("newly");
			        		//获取体检的基本信息
			        		resultMap.put("tjdw", temp.get("tjdw"));//体检单位
			        		resultMap.put("jcrq", temp.get("jcrq"));//检查日期
			        		resultMap.put("tjbh", temp.get("tjbh"));//体检编号
			        		resultMap.put("ys", temp.get("ys"));//医生
			        		resultMap.put("jkzd", temp.get("jkzd"));//健康指导
			        		resultMap.put("wxkz", temp.get("wxkz"));//危险控制
			        		this.coverTjData(rqlx, temp, newly);
			        	}
			        	if(resultData.containsKey("old")) {
			        		Map<String, Object> temp = (Map<String, Object>)resultData.get("old");
			        		this.coverTjData(rqlx, temp, old);
			        	}
			        }
			        //查询温馨提示(需求变动；废除)
//			        List<String> tips = pcService.getTipsByRqlx(rqlx);
//			        String tipStr = "";
//			        if(tips != null && tips.size() > 0) {
//			        	for(String tip : tips) {
//			        		if(StringUntil.isNull(tipStr)) {
//			        			tipStr = tip + "。";
//			        		}else {
//			        			tipStr += tip + "。";
//			        		}
//			        	}
//			        }
//			        tipStr = tipStr.replaceAll(" ", "");
					resultMap.put("newly", newly);
					resultMap.put("old", old);
				//	resultMap.put("tip", tipStr);
					return jsonResult(resultMap);
					//return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckInfoNew"), paramJson);
				} else {
					return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("hmjktjlb"), param);
				}
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	/**
	 * 体检列表
	 * @return
	 * @author lnz
	 * @date 2020-03-05 01:16:15
	 */
	@RequestMapping("jktjList")
	public JsonResult jktjList() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh = paramJson.getString("sfzh");
			String pageSize = paramJson.getString("pageSize");
			String pageNo = paramJson.getString("pageNo");
			if (StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "参数身份证号不能为空");
			}
			if (StringUntil.isNull(pageSize) || StringUntil.isNull(pageNo)) {
				return jsonResult(null, 10001, "页面大小或者页码不能为空");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckListBySFZH"), paramJson);
			} else {
				return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("jktjList"), param);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	
	/**
	 * 体检详情
	 *
	 * @return
	 * @author lnz
	 * @date 2020-03-05 01:16:34
	 */
	@RequestMapping("jktjDetails")
	public JsonResult jktjDetails() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (id != null) {
				if (flag == 1) {
					return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckInfoByCheckId"), paramJson);
				} else {
					return RestTemplateUtils.sendPost(restTemplate, PropertiesUtil.getFollow("jktjDetails"), param);
				}
			} else {
				return jsonResult(null, 10001, "参数为空");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询一般症状表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckYBZZ")
	public JsonResult getCheckYBZZ() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String sfzh = paramJson.getString("sfzh");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(sfzh)) {
				return jsonResult(null, 10001, "参数身份证号不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckYBZZ"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询生活方式表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckSHFS")
	public JsonResult getCheckSHFS() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckSHFS"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询健康检查表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckJKJC")
	public JsonResult getCheckJKJC() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckJKJC"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询协助检查记录表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckXZJC")
	public JsonResult getCheckXZJC() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckXZJC"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询药物信息表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckYWXX")
	public JsonResult getCheckYWXX() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckYWXX"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询评估指导记录表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckPGZD")
	public JsonResult getCheckPGZD() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckPGZD"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询住院史表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckZYS")
	public JsonResult getCheckZYS() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckZYS"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询家族病床史表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckJZBCS")
	public JsonResult getCheckJZBCS() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckJZBCS"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询预防接种
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckYFJZ")
	public JsonResult getCheckYFJZ() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckYFJZ"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询脏器功能表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckZQGN")
	public JsonResult getCheckZQGN() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckZQGN"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	/**
	 * @Description: 查询健康问题表数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getCheckJKWT")
	public JsonResult getCheckJKWT() {
		try {
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String tjid = paramJson.getString("tjid");
			String tjbh = paramJson.getString("tjbh");
			if(StringUntil.isNull(tjid)) {
				return jsonResult(null, 10001, "参数体检标识不能为空！");
			}
			if(StringUntil.isNull(tjbh)) {
				return jsonResult(null, 10001, "参数体检编号不能为空！");
			}
			if (flag == 1) {
				return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckJKWT"), paramJson);
			} else {
				//TODO
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, 10001, "系统错误");
		}
	}
	
	/**
	 * @Description: 体检关键数据项封装
	 * @param @param rqlx
	 * @param @param temp
	 * @param @param resultMap
	 * @return void
	 * @throws
	 */
	private void coverTjData(String rqlx, Map<String, Object> temp, Map<String, Object> resultMap) {
		List<InitDictDto> list = DictUtil.getDict("tjgjsj");
		for(InitDictDto info : list) {
			resultMap.put(info.getCode(), temp.get(info.getCode()));
		}
		
		//通过人群类型获取选中的关键数据项(需求变动，废除)
		//List<BltTjBaseData> baseDatas = pcService.getSelectedTjData(rqlx);
		//通过选中的关键数据项；进行数据的填充
		//if(baseDatas != null && baseDatas.size() > 0) {
			//for(BltTjBaseData info : baseDatas) {
//				JktjValueDto jktjValue = new JktjValueDto();
//				jktjValue.setName(info.getName());
//				//本次体检
//				//通过实体类中属性判断和从中取相关的值
//				if(newly.containsKey(info.getAttribute())) {
//					jktjValue.setValue1(newly.get(info.getAttribute()));
//				}
//				//通过实体类中属性有可能是字典；转译后自定义为属性+“Name”
//				if(newly.containsKey(info.getAttribute()+"Name")) {
//					jktjValue.setValue1(newly.get(info.getAttribute()+"Name"));
//				}
//				//上一次次体检
//				//通过实体类中属性判断和从中取相关的值
//				if(old.containsKey(info.getAttribute())) {
//					jktjValue.setValue2(old.get(info.getAttribute()));
//				}
//				//通过实体类中属性有可能是字典；转译后自定义为属性+“Name”
//				if(old.containsKey(info.getAttribute()+"Name")) {
//					jktjValue.setValue2(old.get(info.getAttribute()+"Name"));
//				}
//				resultList.add(jktjValue);
			//}
		//}
		//return resultList;
	}
}
