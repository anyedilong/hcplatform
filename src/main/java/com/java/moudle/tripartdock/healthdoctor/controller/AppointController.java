package com.java.moudle.tripartdock.healthdoctor.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.tripartdock.healthdoctor.domain.SubscribeInfoHistory;
import com.java.moudle.tripartdock.healthdoctor.service.SubscribeHistoryService;
import com.java.until.StringUntil;
import com.java.until.SysUtil;
import com.java.until.ToJavaUtils;
import com.java.until.UUIDUtil;


@RestController
@RequestMapping("/healthdoctor/appoint")
public class AppointController extends BaseController {
	
	@Inject
	private RestTemplate restTemplate;
	@Inject
	private SubscribeHistoryService subscribeHistoryService;
	
	/**
     * @Description: 根据父级区划code获取下一级的区划
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getAreaList")
    public JsonResult getAreaList(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String areaCode = paramJson.getString("areaCode");
			String url = PropertiesUtil.getRegister("getAreaList");
			url = url+"?areaCode="+areaCode;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    /**
     * @Description: 获取医院类型
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("queryDictAreaList")
    public JsonResult queryDictAreaList() {
    	try {
			String url = PropertiesUtil.getRegister("queryDictAreaList");
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(e.getMessage(), -1, "系统错误");
    	}
    }
	
    /**
     * @Description: 获取字典的集合
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("queryDictConList")
    public JsonResult queryDictConList() {
    	try {
    		String param = getParam(request);
    		JSONObject paramJson = JSON.parseObject(param);
    		String code = paramJson.getString("code");
			if (StringUntil.isNull(code)) {
				return jsonResult(null, 10000, "code不能为空");
			}
			String url = PropertiesUtil.getRegister("queryDictConList");
    		url = url+"?code="+code;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(e.getMessage(), -1, "系统错误");
    	}
    }
    
	/**
     * @Description: 查询该区域的医院
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getHospitalList")
    public JsonResult getHospitalList(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String areaCode = paramJson.getString("areaCode");
			String name = paramJson.getString("name");
			String type = paramJson.getString("type");
			String deptCodes = paramJson.getString("deptCodes");
			String pageNo = paramJson.containsKey("pageNo") ? paramJson.getString("pageNo") : "";
			String pageSize = paramJson.containsKey("pageSize") ? paramJson.getString("pageSize") : "";
			String isPage = paramJson.containsKey("isPage") ? paramJson.getString("isPage") : "1"; // 是不是分页 0 不是 1是
			if (StringUntil.isNull(pageNo) && "1".equals(isPage)) {
				return jsonResult(null, 10000, "参数页码不能为空");
			}
			if (StringUntil.isNull(pageSize) && "1".equals(isPage)) {
				return jsonResult(null, 10000, "参数页面大小不能为空");
			}
			
			String url = "";
			if ("0".equals(isPage)) {
				url = PropertiesUtil.getRegister("getHospitalListNoPage"); // 不分页
			} else {
				url = PropertiesUtil.getRegister("getHospitalList"); // 分页
			}
			url = url+"?areaCode="+areaCode+"&type="+type+"&deptCodes="+deptCodes+"&pageNo="+pageNo+"&pageSize="+pageSize+"&name="+name;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * @Description: 查看医院详情
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getHospitalDetail")
    public JsonResult getHospitalDetail(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "医院标识不能为空");
			} 
			String url = PropertiesUtil.getRegister("getHospitalDetail");
			url = url+"?id="+id;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * @Description: 查询该区域的科室（网站端）
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getDepartmentList")
    public JsonResult getDepartmentList(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String name = paramJson.getString("name");
			String hospitId = paramJson.getString("hospitId");
			String parentCode = paramJson.getString("parentCode");
			String pageNo = paramJson.containsKey("pageNo") ? paramJson.getString("pageNo") : "";
			String pageSize = paramJson.containsKey("pageSize") ? paramJson.getString("pageSize") : "";
			String isPage = paramJson.containsKey("isPage") ? paramJson.getString("isPage") : "1"; // 是不是分页 0 不是 1是
			String url = "";
			if ("0".equals(isPage)) {
				url = PropertiesUtil.getRegister("getDepartmentListNoPage"); // 不分页
			} else {
				url = PropertiesUtil.getRegister("getDepartmentList"); // 分页
			}
			url = url+"?name="+name+"&hospitId="+hospitId+"&parentCode="+parentCode+"&pageNo="+pageNo+"&pageSize="+pageSize;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * @Description: 查询该医院的科室（网站端）
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getDepartmentInfoList")
    public JsonResult getDepartmentInfoList(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitId = paramJson.getString("hospitId");
			String pageNo = paramJson.containsKey("pageNo") ? paramJson.getString("pageNo") : "";
			String pageSize = paramJson.containsKey("pageSize") ? paramJson.getString("pageSize") : "";
			
			String url = PropertiesUtil.getRegister("getDepartmentInfoList");
			url = url+"?hospitId="+hospitId+"&pageNo="+pageNo+"&pageSize="+pageSize;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
     * @Description: 查询科室的详情
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getDepartmentDetail")
    public JsonResult getDepartmentDetail(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String code = paramJson.getString("code");
			String hospitId = paramJson.getString("hospitId");
			
			String url = PropertiesUtil.getRegister("getDepartmentDetail");
			url = url+"?code="+code+"&hospitId="+hospitId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}

    /**
     * 	查询医生列表
     */
    @RequestMapping("getDoctorList")
    public JsonResult getDoctorList() {
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitId = paramJson.containsKey("hospitId") ? paramJson.getString("hospitId") : "";
			String status = paramJson.containsKey("status") ? paramJson.getString("status") : ""; // 1 推荐 2 不推荐
			String name = paramJson.containsKey("name") ? paramJson.getString("name") : "";
			String depict = paramJson.containsKey("depict") ? paramJson.getString("depict") : "";
			String deptCode = paramJson.containsKey("deptCode") ? paramJson.getString("deptCode") : "";
			String id = paramJson.containsKey("id") ? paramJson.getString("id") : "";
			String pageNo = paramJson.containsKey("pageNo") ? paramJson.getString("pageNo") : "";
			String pageSize = paramJson.containsKey("pageSize") ? paramJson.getString("pageSize") : "";
			String isFollow = paramJson.containsKey("isFollow") ? paramJson.getString("isFollow") : "0"; // 查询关注医生 1 是 0 否 默认否
			String areaCode = paramJson.containsKey("areaCode") ? paramJson.getString("areaCode") : ""; // 区域code
					
			String url = PropertiesUtil.getRegister("getDoctorListApp");
			url = url + "?hospitId=" + hospitId + "&status=" + status + "&name=" + name + "&depict=" + depict + 
					"&deptCode=" + deptCode + "&id=" + id+ "&pageNo=" + pageNo+ "&pageSize=" + pageSize+"&isFollow="+isFollow+"&areaCode="+areaCode;
			
			// 查询关注医生
			if ("1".equals(isFollow)) {
				LoginInfoDto sysUser = SysUtil.sysUser(request, response);
				if (sysUser == null) {
					return jsonResult(null, -1, "未登录");
				}
				url = url + "&createUser=" + sysUser.getUsername();
			}
			
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
    }
    
    /**
     * @Description: 查询医生的详情
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getDoctorDetail")
    public JsonResult getDoctorDetail(){
    	try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			}
			String url = PropertiesUtil.getRegister("getDoctorDetail");
			url = url+"?id="+id;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
    
    /**
	 * @Description: 获取医生的未来七天所在科室
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getDoctorDepart")
	public JsonResult getDoctorDepart(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			} 
			String url = PropertiesUtil.getRegister("getDoctorDepart");
			url = url+"?doctorId="+doctorId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取医生的预约排班
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getDoctorAppointDate")
	public JsonResult getDoctorAppointDate(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			String departId = paramJson.getString("departId");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			} 
			if (StringUntil.isNull(departId)) {
				return jsonResult(null, 10000, "科室标识不能为空");
			} 
			String url = PropertiesUtil.getRegister("getDoctorAppointDateApp");
			url = url+"?doctorId="+doctorId+"&departId="+departId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取医生（上午、下午或者夜诊）的预约时间点
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getAppointTimeByDoctor")
	public JsonResult getAppointTimeByDoctor(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			String departId = paramJson.getString("departId");
			String subDate = paramJson.getString("subDate");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			} 
			if (StringUntil.isNull(departId)) {
				return jsonResult(null, 10000, "科室标识不能为空");
			} 
			if (StringUntil.isNull(subDate)) {
				return jsonResult(null, 10000, "预约日期不能为空");
			}
			String url = PropertiesUtil.getRegister("getAppointTimeByDoctor");
			url = url+"?doctorId="+doctorId+"&departId="+departId+"&subDate="+subDate;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取医生（上午、下午或者夜诊）的预约时间点
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getDoctorAppointTime")
	public JsonResult getDoctorAppointTime(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			String departId = paramJson.getString("departId");
			String subDate = paramJson.getString("subDate");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			} 
			if (StringUntil.isNull(departId)) {
				return jsonResult(null, 10000, "科室标识不能为空");
			} 
			if (StringUntil.isNull(subDate)) {
				return jsonResult(null, 10000, "预约日期不能为空");
			}
			String url = PropertiesUtil.getRegister("getDoctorAppointTime");
			url = url+"?doctorId="+doctorId+"&departId="+departId+"&subDate="+subDate;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 预约成功，给his返回预约id
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveSubscribeInfo")
	public JsonResult saveSubscribeInfo(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			String subDate = paramJson.getString("subDate");
			String medicalPersonnelId = paramJson.getString("medicalPersonnelId");
			String medicalPersonnelName = paramJson.getString("medicalPersonnelName");
			String diseaseInfo = paramJson.getString("diseaseInfo");
			String cardNum = paramJson.getString("cardNum");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			}
			if (StringUntil.isNull(subDate)) {
				return jsonResult(null, 10000, "预约日期不能为空");
			}
			if (StringUntil.isNull(medicalPersonnelId)) {
				return jsonResult(null, 10000, "就诊人不能为空");
			}
			LoginInfoDto sysUser = SysUtil.sysUser(request, response);
			if (sysUser == null) {
				return jsonResult(null, -1, "未登录");
			}
			String url = PropertiesUtil.getRegister("saveSubscribeByHC");
			url = url+"?doctorId="+doctorId+"&subDate="+subDate+"&medicalPersonnelId="+medicalPersonnelId+"&phone="+sysUser.getUsername()+
					"&medicalPersonnelName="+medicalPersonnelName+"&diseaseInfo="+diseaseInfo+"&cardNum="+cardNum;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			
			JsonResult jsonMedol = JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
			String code = jsonMedol.getRetCode()+"";
			String msg = jsonMedol.getRetMsg();
			String subId = "";
			if("0".equals(code)) {
				LoginInfoDto user = SysUtil.sysUser(request, response);
				subId = (String) jsonMedol.getData();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SubscribeInfoHistory subHis = JSON.parseObject(param, SubscribeInfoHistory.class);
				subHis.setVisitTime(sdf.parse(subDate));
				subHis.setCreateTime(new Date());
				subHis.setCreateUser(user.getId());
				subHis.setSubscribeId(subId);
				subscribeHistoryService.save(subHis);
			}
			return jsonResult(subId, Integer.parseInt(code), msg);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取预约订单的详情
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("querySubscribeDetail")
	public JsonResult querySubscribeDetail(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String subId = paramJson.getString("subId");
			if (StringUntil.isNull(subId)) {
				return jsonResult(null, 10000, "订单标识不能为空");
			}
			String url = PropertiesUtil.getRegister("querySubscribeDetail");
			url = url+"?subId="+subId;
			
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 取消预约
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("cancelSubscribe")
	public JsonResult cancelSubscribe(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String subId = paramJson.getString("subId");
			if (StringUntil.isNull(subId)) {
				return jsonResult(null, 10000, "预约标识不能为空");
			}
			String quitReason = paramJson.getString("quitReason");
			if (StringUntil.isNull(quitReason)) {
				return jsonResult(null, 10000, "取消原因不能为空");
			}
			LoginInfoDto sysUser = SysUtil.sysUser(request, response);
			if (sysUser == null) {
				return jsonResult(null, -1, "未登录");
			}
			String url = PropertiesUtil.getRegister("cancelSubscribe");
			url = url+"?subId="+subId+"&quitReason="+quitReason+"&username="+sysUser.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			JsonResult result = JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
			if(result.getRetCode() == 0) {
				SubscribeInfoHistory subHis = new SubscribeInfoHistory();
				LoginInfoDto user = SysUtil.sysUser(request, response);
				SubscribeInfoHistory subHisInfo = subscribeHistoryService.getSubHisInfo(subId);
				ToJavaUtils.copyFields(subHisInfo, subHis);
				subHis.setId(UUIDUtil.getUUID());
				subHis.setCreateTime(new Date());
				subHis.setCreateUser(user.getId());
				subHis.setQuitReason(quitReason);
				subHis.setStatus("4");
				subscribeHistoryService.save(subHis);
				return jsonResult();
			}else {
				return result;
			}
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 从his获取某个日期有排班的医生
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getAppointDoctors")
	public JsonResult getAppointDoctors(){
		try{
    		String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitalId = paramJson.getString("hospitalId");
			String deptCode = paramJson.getString("deptCode");
			String parentCode = paramJson.getString("parentCode");
			String queryDate = paramJson.getString("queryDate");
			String type = paramJson.getString("type");
			String pageSize = paramJson.getString("pageSize");
			String pageNo = paramJson.getString("pageNo");
			if (StringUntil.isNull(hospitalId)) {
				return jsonResult(null, 10000, "医院标识不能为空");
			}
			if (StringUntil.isNull(pageSize)) {
				return jsonResult(null, 10000, "页面大小不能为空");
			}
			if (StringUntil.isNull(pageNo)) {
				return jsonResult(null, 10000, "页码不能为空");
			}
			String url = PropertiesUtil.getRegister("getAppointDoctors");
			url = url+"?hospitalId="+hospitalId+"&deptCode="+deptCode+"&parentCode="+parentCode+
						"&queryDate="+queryDate+"&type="+type+"&pageSize="+pageSize+"&pageNo="+pageNo;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取就诊人列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getPatientList")
	public JsonResult getPatientList(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitalId = paramJson.getString("hospitalId");
			
			String url = PropertiesUtil.getRegister("getPatientList");
			url = url+"?username="+user.getUsername()+"&hospitalId="+hospitalId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 查询默认就诊人
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getPatientDefaultInfo")
	public JsonResult getPatientDefaultInfo(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitalId = paramJson.getString("hospitalId");
			
			String url = PropertiesUtil.getRegister("getPatientDefaultInfo");
			url = url+"?username="+user.getUsername()+"&hospitalId="+hospitalId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 查询就诊人基本信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getPatientBaseInfo")
	public JsonResult getPatientBaseInfo(){
		try{
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "就诊人标识不能为空");
			}
			String hospitalId = paramJson.getString("hospitalId");
			
			String url = PropertiesUtil.getRegister("getPatientBaseInfo");
			url = url+"?id="+id+"&hospitalId="+hospitalId;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 删除就诊人信息 -> 更改就诊人状态信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("deletePatientBaseInfo")
	public JsonResult deletePatientBaseInfo(){
		try{
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "就诊人标识不能为空");
			}
			String url = PropertiesUtil.getRegister("deletePatientBaseInfo");
			url = url+"?id="+id+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 设置默认就诊人
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("setPatientDefault")
	public JsonResult setPatientDefault(){
		try{
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			if (StringUntil.isNull(id)) {
				return jsonResult(null, 10000, "就诊人标识不能为空");
			}
			String url = PropertiesUtil.getRegister("setPatientDefault");
			url = url+"?id="+id+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取就诊人的某个医院的就诊卡号
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getMpCard")
	public JsonResult getMpCard(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitalId = paramJson.getString("hospitalId");
			String mpId = paramJson.getString("mpId");
			if (StringUntil.isNull(hospitalId)) {
				return jsonResult(null, 10000, "医院标识不能为空");
			}
			if (StringUntil.isNull(mpId)) {
				return jsonResult(null, 10000, "就诊人不能为空");
			}
			String url = PropertiesUtil.getRegister("getMpCard");
			url = url+"?hospitalId="+hospitalId+"&mpId="+mpId+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取登录人的就诊人的就诊卡信息
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getMpCardInfoByUserId")
	public JsonResult getMpCardInfoByUserId(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			
			String url = PropertiesUtil.getRegister("getMpCardInfoByUserId");
			url = url+"?username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 就诊人的就诊卡号保存
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveMpCard")
	public JsonResult saveMpCard(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String hospitalId = paramJson.getString("hospitalId");
			String mpId = paramJson.getString("mpId");
			String cardNum = paramJson.getString("cardNum");
			if (StringUntil.isNull(hospitalId)) {
				return jsonResult(null, 10000, "医院标识不能为空");
			}
			if (StringUntil.isNull(mpId)) {
				return jsonResult(null, 10000, "就诊人不能为空");
			}
			if (StringUntil.isNull(cardNum)) {
				return jsonResult(null, 10000, "就诊卡号不能为空");
			}
			String url = PropertiesUtil.getRegister("saveMpCard");
			url = url+"?hospitalId="+hospitalId+"&mpId="+mpId+"&cardNum="+cardNum+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 就诊人的就诊卡号删除
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("deleteMpCard")
	public JsonResult deleteMpCard(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.getString("id");
			
			String url = PropertiesUtil.getRegister("deleteMpCard");
			url = url+"?id="+id+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取评论条件
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getComment")
	public JsonResult getComment(){
		try{
			String url = PropertiesUtil.getRegister("getComment");
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 获取预约信息列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getMySubscribeList")
	public JsonResult getMySubscribeList(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String startTime = paramJson.getString("startTime");
			String endTime = paramJson.getString("endTime");
			String status = paramJson.getString("status");
			String ifevaluate = paramJson.getString("ifevaluate");
			String pageNo = paramJson.getString("pageNo");
			String pageSize = paramJson.getString("pageSize");
			
			String url = PropertiesUtil.getRegister("getMySubscribeList");
			url = url+"?username="+user.getUsername()+"&startTime="+startTime+"&endTime="+endTime+"&status="+status
					+"&ifevaluate="+ifevaluate+"&pageNo="+pageNo+"&pageSize="+pageSize;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * @Description: 评论与投诉保存
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("docterEvaluate")
	public JsonResult docterEvaluate(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String id = paramJson.containsKey("id") ? paramJson.getString("id") : "";
			String subscribeId = paramJson.getString("subscribeId");
			String evaluationType = paramJson.getString("evaluationType");
			String evaluationContent = paramJson.getString("evaluationContent");
			String complaintContent = paramJson.getString("complaintContent");
			
			String url = PropertiesUtil.getRegister("docterEvaluate");
			url = url+"?id="+id+"&subscribeId="+subscribeId+"&evaluationType="+evaluationType
					+"&evaluationContent="+evaluationContent+"&complaintContent="+complaintContent;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * 关注医生列表
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("getFollowDoctorInfo")
	public JsonResult getFollowDoctorInfo(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			
			String url = PropertiesUtil.getRegister("getFollowDoctorInfo");
			url = url+"?username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * 检查就诊人是否已经添加
	 */
	@RequestMapping("checkPatientExist")
	public JsonResult checkPatientExist() {
		try {
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			}
			String param = getParam(request);
			JSONObject obj = JSONObject.parseObject(param);
			String sfzh = obj.containsKey("sfzh") ? obj.getString("sfzh") : "";
			String url = PropertiesUtil.getRegister("checkPatientExist");
			url = url+"?username=" + user.getUsername()+ "&sfzh=" + sfzh;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class); 
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
		} catch (Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * 新增或者更新就诊人
	 */
	@RequestMapping("saveOrupdatePatientBaseInfo")
	public JsonResult saveOrupdatePatientBaseInfo() {
		try {
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			}
			String param = getParam(request);
			JSONObject obj = JSONObject.parseObject(param);
			String id = obj.containsKey("id") ? obj.getString("id") : "";
			String name = obj.containsKey("name") ? obj.getString("name") : "";
			String sfzh = obj.containsKey("sfzh") ? obj.getString("sfzh") : "";
			String phone = obj.containsKey("phone") ? obj.getString("phone") : "";
			String addressProvince = obj.containsKey("addressProvince") ? obj.getString("addressProvince") : "";
			String addressCity = obj.containsKey("addressCity") ? obj.getString("addressCity") : "";
			String addressDetail = obj.containsKey("addressDetail") ? obj.getString("addressDetail") : "";
			String patientSign = obj.containsKey("patientSign") ? obj.getString("patientSign") : "";
			String xb = obj.containsKey("xb") ? obj.getString("xb") : "";
			String addressRegion = obj.containsKey("addressRegion") ? obj.getString("addressRegion") : "";
			String addressStreet = obj.containsKey("addressStreet") ? obj.getString("addressStreet") : "";
			String url = PropertiesUtil.getRegister("saveOrupdatePatientBaseInfo");
			url = url+"?id=" + id + "&name=" + name + "&sfzh=" + sfzh + "&phone=" + phone + "&addressProvince=" + addressProvince + "&addressCity=" + addressCity+ "&username=" + user.getUsername()
					+ "&addressDetail=" + addressDetail + "&patientSign=" + patientSign + "&xb=" + xb + "&addressRegion=" + addressRegion + "&addressStreet=" + addressStreet;
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class); 
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
		} catch (Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
		}
	}
	
	/**
	 * 医生关注保存
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveDoctorConcern")
	public JsonResult saveDoctorConcern(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			}
			String url = PropertiesUtil.getRegister("saveHcDoctorConcern");
			url = url+"?doctorId="+doctorId+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * 查询医生是否被关注
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("queryDoctorConcern")
	public JsonResult queryDoctorConcern(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			}
			String url = PropertiesUtil.getRegister("queryHcDoctorConcern");
			url = url+"?doctorId="+doctorId+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	/**
	 * 取消医生关注
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("quitDoctorConcern")
	public JsonResult quitDoctorConcern(){
		try{
    		LoginInfoDto user = SysUtil.sysUser(request, response);
			if (user == null) {
				return jsonResult(null, 10000, "用户未登录");
			} 
			String param = getParam(request);
			JSONObject paramJson = JSON.parseObject(param);
			String doctorId = paramJson.getString("doctorId");
			if (StringUntil.isNull(doctorId)) {
				return jsonResult(null, 10000, "医生标识不能为空");
			}
			String url = PropertiesUtil.getRegister("quitHcDoctorConcern");
			url = url+"?doctorId="+doctorId+"&username="+user.getUsername();
			ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
			String body = postForEntity.getBody();
			return JSONObject.parseObject(coverJsonpData(body), JsonResult.class);
    	}catch (Exception e){
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
	    }
	}
	
	
	/**
	 * @Description: 去掉jsonp格式多余的字符
	 * @param @param jsonpDataStr
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String coverJsonpData(String jsonpDataStr) {
		String str = jsonpDataStr.substring(5, jsonpDataStr.length()-1);
		return str;
	}
	
}