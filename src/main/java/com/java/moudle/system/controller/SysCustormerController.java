package com.java.moudle.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.moudle.system.dto.SysCustormerDto;
import com.java.moudle.system.dto.UpdateLxrDto;
import com.java.moudle.system.dto.UpdatePhoneDto;
import com.java.moudle.system.service.SysBankcardTypeService;
import com.java.moudle.system.service.SysCustormerService;
import com.java.moudle.system.service.SysLnrService;
import com.java.moudle.tripartdock.healthdoctor.service.SyncCustomerService;
import com.java.until.ImageUtil;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.DictDto;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.dba.PageModel;
import com.java.until.ftpup.UpUtils;

/**
 * 客户管理
 * @author ZhangWei
 * @Date: 2020-03-04 13:06
 **/
@RestController
@RequestMapping("sys/customer")
public class SysCustormerController extends BaseController {

    @Inject
    private SysCustormerService sysCustormerService;
    @Inject
    private SysBankcardTypeService sysBankcardTypeService;
    @Value("${ftpUrl}")
    private String ftpUrl;

    @Inject
    private SysLnrService sysLnrService;
    @Inject
    private SyncCustomerService syncCustomerService;
    
    /**
     * @Description 修改密码
     * @Author zw
     * @Date 11:56 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("updatePassword")
    public JsonResult updatePassword () {
        String param = getParam(request);
        logger.info("updatePassword修改密码入参:[{}]", param);
        SysCustomerDto sysCustomerDto = JSON.parseObject(param, SysCustomerDto.class);
        if (StringUntil.isBlank(sysCustomerDto.getPhone())) {
            return jsonResult(null, 9001, "phone手机号不能为空");
        }
        if (StringUntil.isBlank(sysCustomerDto.getPassword())) {
            return jsonResult(null, 9001, "password新密码不能为空");
        }

        JsonResult jsonResult = sysCustormerService.updatePassword(sysCustomerDto);
        
        //根据业务需求；暂时向预约挂号的用户同步 TODO
        syncCustomerService.syncCustomerInfo(sysCustomerDto.getPhone(), sysCustomerDto.getPhone(), sysCustomerDto.getPassword());
        logger.info("updatePassword修改密码出参:[{}]", JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description 新增联系人
     * @Author zw
     * @Date 11:55 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("addLxr")
    public JsonResult addLxr () {
        String param = getParam(request);
        logger.info("addLxr新增联系人入参:[{}]", param);
        AddLxrDto addLxrDto = JSON.parseObject(param, AddLxrDto.class);
        if (StringUntil.isBlank(addLxrDto.getCustormerId())) {
            return jsonResult(null, 9001, "当前客户id不能为空");
        }
        if (StringUntil.isBlank(addLxrDto.getIsChild())) {
            return jsonResult(null, 9001, "是否儿童不能为空");
        }
        if (StringUntil.isBlank(addLxrDto.getName())) {
            return jsonResult(null, 9001, "name联系人姓名不能为空");
        }
        if (StringUntil.isBlank(addLxrDto.getLxrSfzh())) {
            return jsonResult(null, 9001, "lxrSfzh联系人身份证号不能为空");
        }
        if (StringUntil.isBlank(addLxrDto.getPhone())) {
            return jsonResult(null, 9001, "phone手机号不能为空");
        }
        if (StringUntil.isBlank(addLxrDto.getCode())) {
            return jsonResult(null, 9001, "code验证码不能为空");
        }
        if (StringUtils.isBlank(addLxrDto.getZmUrl())) {
            return jsonResult(null, 9001, "请上传身份证照片！");
        }
        if (StringUtils.isBlank(addLxrDto.getFmUrl())) {
            return jsonResult(null, 9001, "请上传身份证照片！");
        }
        if (StringUntil.isBlank(addLxrDto.getGx())) {
            return jsonResult(null, 9001, "gx与本人关系不能为空");
        }
        if ("1".equals(addLxrDto.getGx())) {
            return jsonResult(null, 9001, "不能重复添加自己为联系人!");
        }
        if ("8".equals(addLxrDto.getGx())) {
            if (StringUtils.isBlank(addLxrDto.getGxOther())) {
                return jsonResult(null, 9001, "联系人关系不能为空");
            }
        }
        if ("1".equals(addLxrDto.getIsChild())) {
            if (StringUntil.isBlank(addLxrDto.getGuardianName())) {
                return jsonResult(null, 9001, "监护人姓名不能为空");
            }
        }
        //根据需求不能添加重复的联系人
        int count = sysLnrService.isExist(addLxrDto.getLxrSfzh(), addLxrDto.getCustormerId());
        if(count > 0) {
        	return jsonResult(null, 9001, "请不要重复添加联系人!");
        }
        
        JsonResult jsonResult = sysCustormerService.addLxr(addLxrDto);
        logger.info("addLxr新增联系人出参:[{}]", JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description 获取联系人列表
     * @Author zw
     * @Date 11:56 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("getLxrList")
    public JsonResult getLxrList () {
        String param = getParam(request);
        logger.info("getLxrList获取联系人列表入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("custormerId") || StringUntil.isBlank(json.get("custormerId").toString())) {
            return jsonResult(null,9001, "当前登陆客户id不能为空");
        }

        PageModel pageModel = new PageModel();
        if (json.containsKey("pageNo") && StringUntil.isNotBlank(json.get("pageNo").toString())) {
            pageModel.setPageNo(Integer.parseInt(json.get("pageNo").toString()));
        }
        if (json.containsKey("pageSize") && StringUntil.isNotBlank(json.get("pageSize").toString())) {
            pageModel.setPageSize(Integer.parseInt(json.get("pageSize").toString()));
        }

        sysCustormerService.getLxrList(json.get("custormerId").toString(), pageModel);
        logger.info("getLxrList获取联系人列表出参:[{}]", JSON.toJSONString(pageModel));
        return jsonResult(pageModel);
    }

    /**
     * @Description 删除联系人
     * @Author zw
     * @Date 11:56 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("delLxr")
    public JsonResult delLxr () {
        String param = getParam(request);
        logger.info("delLxr删除联系人入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("id") || StringUntil.isBlank(json.get("id").toString())) {
            return jsonResult(null, 9001, "id 联系人id不能为空");
        }
        if (!json.containsKey("custormerId") || StringUntil.isBlank(json.get("custormerId").toString())) {
            return jsonResult(null, 9001, "客户id不能为空");
        }
        JsonResult jsonResult = sysCustormerService.delLxr(json.get("id").toString(), json.get("custormerId").toString());
        logger.info("delLxr删除联系人出参:[{}]", JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description 修改联系人信息
     * @Author zw
     * @Date 11:57 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("updateLxr")
    public JsonResult updateLxr () {
        String param = getParam(request);
        logger.info("updateLxr修改联系人入参:[{}]", param);
        UpdateLxrDto updateLxrDto = JSON.parseObject(param, UpdateLxrDto.class);
        if (StringUntil.isBlank(updateLxrDto.getId())) {
            return jsonResult(null, 9001, "id联系人id不能为空");
        }
        if (StringUntil.isBlank(updateLxrDto.getCustormerId())) {
            return jsonResult(null, 9001, "客户id不能为空");
        }

        sysCustormerService.updateLxr(updateLxrDto);
        logger.info("updateLxr修改联系人出参:[{}]", JSON.toJSONString(jsonResult()));
        return jsonResult();
    }

    /**
	 * @Description: 获取登录者的联系人
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
    @RequestMapping("getLoginCutormerList")
	public JsonResult getLoginCutormerList() {
		try {
			LoginInfoDto user = SysUtil.sysUser(request, response);
			if(user == null) {
				return jsonResult(null, 1000, "用户未登录");
			}
			List<LoginInfoDto> list = new ArrayList<>();
			//list.add(user);
			List<LoginInfoDto> custors = sysCustormerService.getCutormerList(user.getSfzh());
			if(custors != null && custors.size() > 0) {
				list.addAll(custors);
			}
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
	}

    /**
     * 获取银行卡类型列表
     */
    @RequestMapping("getBankTypeList")
    public JsonResult getBankTypeList() {
    	try {
    		List<DictDto> list = sysBankcardTypeService.getSysBankCardTypeList();
			return jsonResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
    }

    /**
     * 注册/创建用户接口
     */
    @RequestMapping("createUser")
    public JsonResult createUser() {
    	try {
	        String param = getParam(request);
            logger.info("创建用户请求参数：" + param);
			SysCustormer custormer = JSON.parseObject(param, SysCustormer.class);
			// 查询数据库中 手机号是否被注册
			SysCustormer cus = sysCustormerService.getCUstormerInfoByPhone(custormer.getPhone());
			if (cus != null && !"123456".equals(cus.getPwd())) {
				if ("1".equals(cus.getStatus())) {
					return jsonResult(null, 9001, "您的账户已被禁用，不能再次注册！请联系运营商。");
				} else {
					return jsonResult(null, 9001, "手机号已被注册");
				}
			} else {
				// 实名制
				if(cus != null) {
					custormer.setId(cus.getId());
				}
				String password = custormer.getPassword();
				sysCustormerService.createUser(custormer);
				//根据业务需求；暂时向预约挂号的用户同步 TODO
		        syncCustomerService.syncCustomerInfo(custormer.getPhone(), custormer.getPhone(), password);
                //添加默认就诊人
		        sysCustormerService.savePatient(custormer);
				return jsonResult();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误");
		}
    }
    
    /**
     * 验证账号是否被禁用
     */
    @RequestMapping("checkAccount")
    public JsonResult checkAccount() {
        String param = getParam(request);
        JSONObject obj = JSONObject.parseObject(param);
        String phone = obj.containsKey("phone") ? obj.getString("phone") : "";
        if (StringUntil.isNull(phone)) {
        	return jsonResult("手机号不能为空", -1, "手机号不能为空");
        }
		SysCustormer cus = sysCustormerService.getCUstormerInfoByPhone(phone);
    	if (cus != null && "1".equals(cus.getStatus())) {
    		return jsonResult("您的账户已被禁用，请联系运营商", -1, "您的账户已被禁用，请联系运营商");
    	} else {
    		return jsonResult();
    	}
    }
    
    /**
     * 获取图文验证码接口
     */
    @RequestMapping("getImageCode")
    public String getImageCode(HttpServletRequest request, HttpServletResponse response, String correspondingCode) throws Exception{
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ImageUtil imageUtil = new ImageUtil(150, 40, 6,30);
        String code = imageUtil.getCode();
        System.out.println("验证码key:" + correspondingCode);
        System.out.println("图片验证码:" + code);
        // 存radis
        CacheUntil.put(RedisCacheEmun.DICT_CACHE, correspondingCode, code);
        imageUtil.write(response.getOutputStream());
        return null;
    }

    /**
     * 验证图文验证码接口
     */
    @RequestMapping("testImageCode")
    public JsonResult testImageCode() throws Exception{
    	String param = getParam(request);
		JSONObject input = JSON.parseObject(param);
		String correspondingCode = "boletong";
		if (!input.containsKey("correspondingCode")) {
			return jsonResult(null, 9001, "correspondingCode不能为空");
		}
		correspondingCode = input.get("correspondingCode").toString();
		String imageCode = "";
		if (!input.containsKey("imageCode")) {
			return jsonResult(null, 9001, "imageCode不能为空");
		}
		imageCode = input.get("imageCode").toString();

        // radis取值
        String code = CacheUntil.get(RedisCacheEmun.DICT_CACHE, correspondingCode, String.class);
        Map<String, String> result = new HashMap<>();
        if (imageCode.equals(code)) {
        	result.put("result", "0");
        	result.put("remarks", "验证通过");
        	CacheUntil.delete(RedisCacheEmun.DICT_CACHE, correspondingCode);
        } else {
        	result.put("result", "-1");
        	result.put("remarks", "验证码错误");
        }
		return jsonResult(result);
    }

    /**
     * 银行卡实名制对接 三要素/四要素
     * idCard = "身份证号"
     * name = "姓名"
     * accountNo = "银行卡号"
     * mobile = "手机号"
     * type = "类型 1身份证二要素实名制检测 2银行卡四要素实名制检测 3银行卡三要素实名制检测 4银行卡四要素实名制检测"
     * @throws IOException 
     */
    @RequestMapping("realNameTest")
    public JsonResult realNameTest() throws IOException {
        String param = getParam(request);
        System.err.println("入参：" + param);
        JSONObject obj = JSON.parseObject(param);
        String type = obj.get("type").toString();
        
        String idCard = obj.containsKey("idCard") ? obj.getString("idCard") : "";
        String name = obj.containsKey("name") ? obj.getString("name") : "";
        String accountNo = obj.containsKey("accountNo") ? obj.getString("accountNo") : "";
        String mobile = obj.containsKey("mobile") ? obj.getString("mobile") : "";
        JsonResult result = sysCustormerService.realNameTest(type, idCard, name, mobile, accountNo);
        return result;
    }
    
    /**
     *	 身份证二要素/银行卡三要素实名制检测
     * idCard = "身份证号"
     * name = "姓名"
     * accountNo = "银行卡号"
     * mobile = "手机号"
     * type = "类型 1身份证二要素实名制检测 2银行卡四要素实名制检测 3银行卡三要素实名制检测 4银行卡四要素实名制检测"
     * @throws IOException 
     */
    @RequestMapping("realNameConfirm")
    public JsonResult realNameConfirm() throws IOException {
        String param = getParam(request);
        logger.info("实名认证请求参数：" + param);
        JSONObject obj = JSON.parseObject(param);
        String type = obj.get("type").toString();
        
        String idCard = obj.containsKey("idCard") ? obj.getString("idCard") : "";
        String name = obj.containsKey("name") ? obj.getString("name") : "";
        String accountNo = obj.containsKey("accountNo") ? obj.getString("accountNo") : "";
        String mobile = obj.containsKey("mobile") ? obj.getString("mobile") : "";
        Integer cardType = obj.containsKey("cardType") ? obj.getInteger("cardType") : null;
        //获取登录时的key
        Map<String, Object> securityKey = SysUtil.getSecurityKey(request);
        String key = "";
        if(securityKey != null) {
        	key = (String)securityKey.get("securitykey");
        }
        JsonResult result = sysCustormerService.realNameConfirm(type, idCard, name, mobile, accountNo, cardType, key);
        logger.info("实名认证返回：" + param);
        return result;
    }
    
    /**
     * @Author zw
     * @Description 修改用户手机号
     * @Date 09:17 2020-03-10
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("updatePhone")
    public JsonResult updatePhone () {
        String param = getParam(request);
        logger.info("updatePhone修改用户手机号入参:[{}]", param);
        UpdatePhoneDto updatePhoneDto = JSON.parseObject(param, UpdatePhoneDto.class);
        if (StringUntil.isBlank(updatePhoneDto.getId())) {
            return jsonResult(null, 9001, "id用户id不能为空");
        }
        if (StringUntil.isBlank(updatePhoneDto.getOldPhone())) {
            return jsonResult(null, 9001, "oldPhone原手机号不能为空");
        }
        if (StringUntil.isBlank(updatePhoneDto.getNewPhone())) {
            return jsonResult(null, 9001, "newPhone新手机号不能为空");
        }
        if (StringUntil.isBlank(updatePhoneDto.getOldCode())) {
            return jsonResult(null, 9001, "oldCode原手机号验证码不能为空");
        }
        if (StringUntil.isBlank(updatePhoneDto.getNewCode())) {
            return jsonResult(null, 9001, "newCode新手机号验证码不能为空");
        }

        if (updatePhoneDto.getOldPhone().equals(updatePhoneDto.getNewPhone())) {
            return jsonResult(null, 9001, "新手机号不能与原手机号相同");
        }

        JsonResult jsonResult = sysCustormerService.updatePhone(updatePhoneDto);
        LoginInfoDto user = SysUtil.sysUser(request, response);
		user.setUsername(updatePhoneDto.getNewPhone());
		SysUtil.updateCaUser(request, user);
		//根据业务需求；暂时向预约挂号的用户同步 TODO
		SysCustormer sysCustormer = sysCustormerService.get(updatePhoneDto.getId());
        syncCustomerService.syncCustomerInfo(updatePhoneDto.getOldPhone(), updatePhoneDto.getNewPhone(), sysCustormer.getPassword());
        logger.info("updatePhone修改用户手机号出参:[{}]", jsonResult);
        return jsonResult;
    }
    /**
               * 个人资料详情
     * 
     * @return
     * @author lnz
     * @date 2020-03-11 09:57:42
     */
    @RequestMapping("personalData")
    public JsonResult personalData() {
    	try {
    		 String param = getParam(request);
    		 JSONObject jsonObject = JSONObject.parseObject(param);
    		 String sfzh = jsonObject.getString("sfzh");
    		 if (StringUtils.isNull(sfzh)) {
    			  return jsonResult(null, 9001, "身份证号不能为空");
			}
    		 SysCustomerDto dto = sysCustormerService.getPersonData(sfzh);
    		 return jsonResult(dto);
		} catch (Exception e) {
			e.getMessage();
			return  jsonResult(e.getMessage());
		}
    }
    /**
     * 	修改个人资料头像
     * 
     * @return
     * @author lnz
     * @date 2020-03-11 01:42:47
     */
    @RequestMapping("updatePersonalData")
    public JsonResult updatePersonalData() {
    	try {
    		 String param = getParam(request);
    		 JSONObject jsonObject = JSONObject.parseObject(param);
    		 String sfzh = jsonObject.getString("sfzh");
    		 String custormerUrl = jsonObject.getString("custormerUrl");
    		 if (StringUtils.isNull(sfzh)) {
    			  return jsonResult(null, 9001, "身份证号不能为空");
			}
    		 sysCustormerService.updatePersonalData(sfzh,custormerUrl);
    		 return jsonResult(null,0,"保存成功");
		} catch (Exception e) {
			e.getMessage();
			return  jsonResult(e.getMessage());
		}
    }

    /**
     * @Description 机构端用户列表
     * @Author zw
     * @Date 11:49 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("userlist")
    public JsonResult userlist () {
        String param = getParam(request);
        logger.info("userlist用户列表入参:[{}]", param);
        SysCustormerDto sysCustormerDto = JSON.parseObject(param, SysCustormerDto.class);
        PageModel pageModel = new PageModel();
        if (sysCustormerDto.getPageNo() != 0) {
            pageModel.setPageNo(sysCustormerDto.getPageNo());
        }
        if (sysCustormerDto.getPageSize() != 0) {
            pageModel.setPageSize(sysCustormerDto.getPageSize());
        }

        sysCustormerService.userlist(pageModel, sysCustormerDto);
        logger.info("userlist用户列表出参:[{}]", JSON.toJSONString(pageModel));
        return jsonResult(pageModel);
    }

    /**
     * @Description 机构端用户状态修改
     * @Author zw
     * @Date 14:23 2020-03-18
     * @Param
     * @return com.java.moudle.common.message.JsonResult
     **/
    @RequestMapping("userstatus")
    public JsonResult userstatus () {
        String param = getParam(request);
        logger.info("userstatus机构端用户状态修改入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("id") || StringUntil.isBlank(json.get("id").toString())) {
            return jsonResult(null, 9001, "用户id不能为空");
        }
        if (!json.containsKey("status") || StringUntil.isBlank(json.get("status").toString())) {
            return jsonResult(null, 9001, "用户状态不能为空");
        }

        JsonResult jsonResult = sysCustormerService.userstatus(json.get("status").toString(), json.get("id").toString());
        logger.info("userstatus机构端用户状态修改出参:[{}]", JSON.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * @Description: 获取网站端用户
     * @param @return
     * @return JsonResult
     * @throws
     */
    @RequestMapping("getLoginInfo")
    public JsonResult getLoginInfo() {
    	try {
    		LoginInfoDto user = SysUtil.sysUser(request, response);
    		if(user != null) {
    			user.setPassword("");
        		user.setAuthorities("");
        		user.setUsername(user.getPhone());
    		}
    		return jsonResult(user);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return jsonResult(null, -1, "系统错误");
    	}
    }
    
    /**
	 * @Description: 上传头像
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("saveHeadImage")
	public JsonResult saveHeadImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("fileData");
			if(file != null) {
				String originalName = file.getOriginalFilename();
				String puf = originalName.substring(originalName.lastIndexOf("."));
				String pngName = UUIDUtil.getUUID()+puf;
				//ftp上传
				boolean upload = UpUtils.upload(file.getInputStream(), "/certificate/", pngName);
				if(upload) {
					LoginInfoDto user = SysUtil.sysUser(request, response);
					sysCustormerService.updateUserHeadImage(user.getId(), ftpUrl+"/certificate/"+pngName);
					user.setCustormerUrl(ftpUrl+"/certificate/"+pngName);
					SysUtil.updateCaUser(request, user);
					return jsonResult(ftpUrl+"/certificate/"+pngName);
				}else {
					return jsonResult(null, 10000, "上传的头像失败");
				}
			}else {
				return jsonResult(null, 10000, "请选择上传的头像");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return jsonResult(null, -1, "系统错误"); 
		}
	}

	/**
	 * @Description 获取联系人详情
	 * @Author zw
	 * @Date 16:37 2020-03-30
	 * @Param
	 * @return com.java.moudle.common.message.JsonResult
	 **/
	@RequestMapping("getSysLnrDetails")
	public JsonResult getSysLnrDetails() {
        String param = getParam(request);
        logger.info("getSysLnrDetails获取联系人详情  入参:[{}]", param);
        JSONObject json = JSON.parseObject(param);
        if (!json.containsKey("id") || StringUtils.isBlank(json.get("id").toString())) {
            return jsonResult(null, 9001, "联系人id不能为空");
        }

        AddLxrDto addLxrDto = sysLnrService.getSysLnrDetails(json.get("id").toString());

        logger.info("getSysLnrDetails获取联系人详情  出参:[{}]", addLxrDto);
	    return jsonResult(addLxrDto);
    }
}
