package com.java.moudle.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dao.SysCustormerDao;
import com.java.moudle.system.dao.SysCustormerLnrDao;
import com.java.moudle.system.dao.SysLnrDao;
import com.java.moudle.system.dao.SysLnrFileDao;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.domain.SysCustormerLnr;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.domain.SysLnrFile;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.LxrDto;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.moudle.system.dto.SysCustormerDto;
import com.java.moudle.system.dto.UpdateLxrDto;
import com.java.moudle.system.dto.UpdatePhoneDto;
import com.java.moudle.system.service.SysCustormerService;
import com.java.until.NameValue;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;
import com.java.until.dba.PageModel;
import com.java.until.http.HttpUtil;
import com.java.until.ras.BCrypt;
import com.java.until.validate.VerificationUtil;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 09:48
 **/
@Service
@Transactional
public class SysCustormerServiceImpl extends BaseServiceImpl<SysCustormerDao, SysCustormer> implements SysCustormerService {

    @Inject
    private SysCustormerDao sysCustormerDao;

    @Inject
    private SysLnrDao sysLnrDao;

    @Inject
    private SysLnrFileDao sysLnrFileDao;

    @Inject
    private SysCustormerLnrDao sysCustormerLnrDao;

    @Value("${encryStr}")
    private String encryStr;

    @Inject
    private RestTemplate restTemplate;
 
    @Override
    public JsonResult updatePassword(SysCustomerDto dto) {

        // 手机验证码验证
//        if (!VerificationUtil.verificationCode(dto.getPhone(), dto.getCode())) {
//            return new JsonResult(null, 1001, "手机验证码错误！");
//        }

        // 根据手机号修改密码
        sysCustormerDao.updatePassword(dto.getPhone(), dto.getPassword(), BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        
        return new JsonResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addLxr(AddLxrDto addLxrDto) {

        // 判断是否是本人(不能添加多个自己作为联系人)
        SysCustormer sysCustormer = sysCustormerDao.get(addLxrDto.getCustormerId());
        if (sysCustormer != null) {

            // 如果添加的联系人身份证号与客户表身份证号相同,则说明是添加自己为联系人
            if (addLxrDto.getLxrSfzh().equals(sysCustormer.getSfzh())) {
                return new JsonResult(null, 9001, "不能重复添加自己为联系人!");
            }
        } else {
            return new JsonResult(null, 9001, "未查询到客户信息!");
        }

        // 封装联系人
        SysLnr sysLnr = new SysLnr();
        sysLnr.setId(UUIDUtil.getUUID());
        sysLnr.setName(addLxrDto.getName());
        sysLnr.setPhone(addLxrDto.getPhone());
        sysLnr.setSfzh(addLxrDto.getLxrSfzh());
        sysLnr.setIsChild(addLxrDto.getIsChild());
        sysLnr.setGuardianName(addLxrDto.getGuardianName());

        // 封装联系人文件信息
        SysLnrFile sysLnrFile = new SysLnrFile();
        sysLnrFile.setId(UUIDUtil.getUUID());
        sysLnrFile.setLnrId(sysLnr.getId());
        sysLnrFile.setSfzhzmUrl(addLxrDto.getZmUrl());
        sysLnrFile.setSfzhfmUrl(addLxrDto.getFmUrl());

        // 封装联系人和用户关联信息
        SysCustormerLnr sysCustormerLnr = new SysCustormerLnr();
        sysCustormerLnr.setId(UUIDUtil.getUUID());
        sysCustormerLnr.setCustormerId(addLxrDto.getCustormerId());
        sysCustormerLnr.setLnrId(sysLnr.getId());
        sysCustormerLnr.setGx(addLxrDto.getGx());
        sysCustormerLnr.setGxOther(addLxrDto.getGxOther());

        sysLnrDao.save(sysLnr);
        sysLnrFileDao.save(sysLnrFile);
        sysCustormerLnrDao.save(sysCustormerLnr);
        return new JsonResult();
    }

    @Override
    public void getLxrList(String custormerId, PageModel pageModel) {
        sysLnrDao.getLxrList(custormerId, pageModel);
        List<LxrDto> lxrDtoList = pageModel.getList();
        lxrDtoList.forEach(lxrDto -> {
            if ("其他".equals(lxrDto.getGx())) {
                lxrDto.setGx(lxrDto.getGxOther());
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult delLxr(String id, String custormerId) {

        if (!custormerId.equals(id)) {
            // 删除联系人
            sysLnrDao.delLxr(id);
            // 删除关联表
            sysCustormerLnrDao.deleteSysCustormerLnr(id, custormerId);
            return new JsonResult();
        }
        return new JsonResult(null, 9001, "不能删除自己");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLxr(UpdateLxrDto updateLxrDto) {
        SysLnr sysLnr = sysLnrDao.get(updateLxrDto.getId());

        // 判断上传的联系人姓名是否为空
        if (StringUtils.isNotBlank(updateLxrDto.getName())) {
            sysLnr.setName(updateLxrDto.getName());
        }

        // 判断上传的联系人手机号是否为空
        if (StringUtils.isNotBlank(updateLxrDto.getPhone())) {
            sysLnr.setPhone(updateLxrDto.getPhone());
        }

        // 判断监护人姓名是否为空(儿童)
        if (StringUtils.isNotBlank(updateLxrDto.getGuardianName())) {
            sysLnr.setGuardianName(updateLxrDto.getGuardianName());
        }

        // 判断关系gx是否为空
        if (StringUtils.isNotBlank(updateLxrDto.getGx())) {

            if ("8".equals(updateLxrDto.getGx())) {
                sysCustormerLnrDao.updateLxrGxOther(updateLxrDto);
            } else {
                sysCustormerLnrDao.updateLxrGx(updateLxrDto.getGx(), updateLxrDto.getId(), updateLxrDto.getCustormerId());
            }
        }

        // 判断上传的联系人身份证号号是否为空
        if (StringUtils.isNotBlank(updateLxrDto.getLxrSfzh())) {
            sysLnr.setSfzh(updateLxrDto.getLxrSfzh());
        }

        SysLnrFile sysLnrFile = sysLnrFileDao.getSysLnrFileByLnrId(updateLxrDto.getId());

        // 判断正面照片是否为空
        if (StringUtils.isNotBlank(sysLnrFile.getSfzhzmUrl())) {
            sysLnrFile.setSfzhzmUrl(updateLxrDto.getZmUrl());
        }

        // 判断反面照片是否为空
        if (StringUtils.isNotBlank(updateLxrDto.getFmUrl())) {
            sysLnrFile.setSfzhfmUrl(updateLxrDto.getFmUrl());
        }

        sysLnrDao.save(sysLnr);
        sysLnrFileDao.save(sysLnrFile);

    }
    
    @Override
	public List<LoginInfoDto> getCutormerList(String sfzh) {
    	List<LoginInfoDto> list = dao.getCutormerList(sfzh);
    	if(list == null || list.size() == 0) {
    		list = new ArrayList<>();
    	}
		return list;
	}

	@Override
	public SysCustormer queryInfoByCon(String id, String username) {
		return dao.queryInfoByCon(id, username);
	}

	@Override
	public void createUser(SysCustormer custormer) {
		// 密码加密
        if (StringUtils.isNotBlank(custormer.getPassword())) {
            custormer.setPwd(custormer.getPassword());
            String  encryptionPwd = BCrypt.hashpw(custormer.getPassword(), BCrypt.gensalt());
            custormer.setPassword(encryptionPwd);
        }

		// 设置值
		custormer.setId(UUIDUtil.getUUID());
		custormer.setCreateTime(new Date());
		custormer.setStatus("0");
		//custormer.setIsRealName(0);
		custormer.setUsername(custormer.getPhone());
		dao.save(custormer);

		/* 默认添加本人为联系人 */

        // 封装联系人
        SysLnr sysLnr = new SysLnr();
        sysLnr.setId(UUIDUtil.getUUID());
        sysLnr.setName(custormer.getName());
        sysLnr.setPhone(custormer.getPhone());
        sysLnr.setSfzh(custormer.getSfzh());
        sysLnr.setIsChild("2");

        // 封装关联表
        SysCustormerLnr sysCustormerLnr = new SysCustormerLnr();
        sysCustormerLnr.setId(UUIDUtil.getUUID());
        sysCustormerLnr.setCustormerId(custormer.getId());
        sysCustormerLnr.setLnrId(sysLnr.getId());

        // 默认为 1本人
        sysCustormerLnr.setGx("1");

        // 保存
        sysLnrDao.save(sysLnr);
        sysCustormerLnrDao.save(sysCustormerLnr);
	}

    @Override
    public JsonResult updatePhone(UpdatePhoneDto updatePhoneDto) {

        // 验证旧手机号验证码
        if (!VerificationUtil.verificationCode2(updatePhoneDto.getOldPhone(), updatePhoneDto.getOldCode())) {
            return new JsonResult(null, 9001, "原手机验证码错误！");
        }

        // 验证新手机号验证码
        if (!VerificationUtil.verificationCode2(updatePhoneDto.getNewPhone(), updatePhoneDto.getNewCode())) {
            return new JsonResult(null, 9001, "新手机验证码错误！");
        }

        // 删除缓存验证码
        //CacheUntil.del(RedisCacheEmun.USER_CACHE.getRedisTemplate(), updatePhoneDto.getOldPhone());
        //CacheUntil.del(RedisCacheEmun.USER_CACHE.getRedisTemplate(), updatePhoneDto.getNewPhone());

        // 修改手机号
        sysCustormerDao.updatePhone(updatePhoneDto);
        // 验证成功,删除缓存验证码
        CacheUntil.del(RedisCacheEmun.USER_CACHE.getRedisTemplate(), updatePhoneDto.getOldPhone());
        CacheUntil.del(RedisCacheEmun.USER_CACHE.getRedisTemplate(), updatePhoneDto.getNewPhone());
        return new JsonResult();
    }

	@Override
	public SysCustormer getCUstormerInfoByPhone(String telephone) {
		return dao.getCUstormerInfoByPhone(telephone);
	}

	@Override
	public SysCustomerDto getPersonData(String sfzh) {
		
		return sysCustormerDao.getPersonData(sfzh);
	}

	@Override
	public void updatePersonalData(String sfzh, String custormerUrl) {
		sysCustormerDao.updatePersonData(sfzh, custormerUrl);
		
	}

    @Override
    public void userlist(PageModel pageModel, SysCustormerDto sysCustormerDto) {
        sysCustormerDao.userlist(pageModel, sysCustormerDto);
    }

    @Override
    public JsonResult userstatus(String status, String id) {
        sysCustormerDao.userstatus(status, id);
        if("1".equals(status)) {
        	SysCustormer custormer = sysCustormerDao.get(id);
        	CacheUntil.put(RedisCacheEmun.USER_CACHE, "benefit"+custormer.getPhone()+encryStr, custormer);
        }
        return new JsonResult();
    }
    
    @Override
	public void updateUserHeadImage(String id, String headImage) {
    	sysCustormerDao.updateUserHeadImage(id, headImage);
	}

    /**
     * idCard = "身份证号"
     * name = "姓名"
     * accountNo = "银行卡号"
     * mobile = "手机号"
     * type = "类型 1身份证二要素实名制检测 2银行卡四要素实名制检测 3银行卡三要素实名制检测 4银行卡四要素实名制检测"
     * 银行卡实名制对接 三要素/四要素
     * JsonResult.retCode == 0验证通过
     * @throws IOException 
     */
	@Override
	public JsonResult realNameTest(String type, String idCard, String name, String mobile, String accountNo) throws IOException {
		List<NameValue>  list=new 	ArrayList<NameValue>();
        String check3url = "http://bcard3and4.market.alicloudapi.com/bank3CheckNew"; // 银行卡三要素地址
        String check4url = "http://bcard3and4.market.alicloudapi.com/bankCheck4New"; // 银行卡四要素地址
        String idCardCheckUrl = "http://bcard3and4.market.alicloudapi.com/IDCard"; // 身份证二要素地址
        String telvertify = "http://telvertify.market.alicloudapi.com/lianzhuo/telvertify"; // 身份证三要素地址
        String appCode = "46b04f89a6cc4817949a867768da9952"; // 335e74e4c90043678833ccea11432b35
        String result = "";
        if ("1".equals(type)) { // 身份证二要素实名制检测
    		list.add(new NameValue("idCard",idCard));
    		list.add(new NameValue("name", name));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
            result = HttpUtil.realNamePost(idCardCheckUrl, "?"+paramobj, appCode);
            if ("-1".equals(result)) {
                return new JsonResult("原因：网络不好；验证次数用尽；", -1, "接口调用失败");
            }
            System.out.println("身份证二要素实名制检测返回：" + result);
            JSONObject resultObj = JSON.parseObject(result);
            String status = resultObj.get("status").toString();
            String msg = resultObj.get("msg").toString();
            if ("01".equals(status)) {
            	return new JsonResult(JSON.parse(result), 0, msg);
            } else if ("205".equals(status)) {
            	return new JsonResult(status, -1, "您输入的身份证号码与本人不匹配，请重新输入！");
            } else {
                return new JsonResult(status, -1, "您的实名认证未通过，请重新进行实名认证！");
            }
        } else if ("2".equals(type)) { // 身份证三要素实名制检测
    		list.add(new NameValue("id",idCard));
    		list.add(new NameValue("name", name));
    		list.add(new NameValue("telnumber", mobile));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
        	String code = "46b04f89a6cc4817949a867768da9952"; // 335e74e4c90043678833ccea11432b35
        	result = HttpUtil.realNamePost(telvertify, "?" + paramobj, code);
        	System.out.println("身份证三要素实名制检测：" + result);
            JSONObject resultObj = JSON.parseObject(result);
            String status = resultObj.getJSONObject("resp").getString("code");
            String msg = resultObj.getJSONObject("resp").getString("desc");
            if ("0".equals(status)) {
            	return new JsonResult(JSON.parse(result), 0, msg);
            } else if ("10".equals(status)) {
            	return new JsonResult(status, -1, "您输入的身份证号码与本人不匹配，请重新输入！");
            } else {
                return new JsonResult(status, -1, "您的实名认证未通过，请重新进行实名认证！");
            }
        } else if ("3".equals(type)) { // 银行卡三要素实名制检测
    		list.add(new NameValue("idCard",idCard));
    		list.add(new NameValue("name", name));
    		list.add(new NameValue("accountNo", accountNo));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
            result = HttpUtil.realNamePost(check3url, "?" + paramobj, appCode);
        } else if ("4".equals(type)) { // 银行卡四要素实名制检测
    		list.add(new NameValue("idCard",idCard));
    		list.add(new NameValue("name", name));
    		list.add(new NameValue("accountNo", accountNo));
    		list.add(new NameValue("mobile", mobile));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
            result = HttpUtil.realNamePost(check4url, "?" + paramobj, appCode);
        } else {
        	return new JsonResult("type不存在", -1, "type不存在");
        }
        System.out.println("实名认证返回值：" + result);
        // 处理返回值
        if ("-1".equals(result)) {
            return new JsonResult("原因：网络不好；验证次数用尽；", -1, "接口调用失败");
        }
        JSONObject resultObj = JSON.parseObject(result);
        String status = resultObj.get("status").toString();
        String msg = resultObj.get("msg").toString();
        if ("01".equals(status)) {
        	return new JsonResult(JSON.parse(result), 0, msg);
        } else {
            return new JsonResult(status, -1, "您提交的实名认证信息有错误，请重新填写！");
        }
	}
	
	/**
     * idCard = "身份证号"
     * name = "姓名"
     * accountNo = "银行卡号"
     * mobile = "手机号"
     * type = "类型 1身份证二要素实名制检测 2银行卡四要素实名制检测 3银行卡三要素实名制检测 4银行卡四要素实名制检测"
     * JsonResult.retCode == 0验证通过
     * @throws IOException 
     */
	@Override
	public JsonResult realNameConfirm(String type, String idCard, String name, String mobile, String accountNo, Integer cardType, String key) throws IOException {
		List<NameValue>  list=new 	ArrayList<NameValue>();
        String check3url = "http://bcard3and4.market.alicloudapi.com/bank3CheckNew"; // 银行卡三要素地址
        String idCardCheckUrl = "http://bcard3and4.market.alicloudapi.com/IDCard"; // 身份证二要素地址
        String appCode = "46b04f89a6cc4817949a867768da9952"; // 335e74e4c90043678833ccea11432b35
        String result = "";
        if ("1".equals(type)) { // 身份证二要素实名制检测
    		list.add(new NameValue("idCard",idCard));
    		list.add(new NameValue("name", name));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
            result = HttpUtil.realNamePost(idCardCheckUrl, "?"+paramobj, appCode);
            if ("-1".equals(result)) {
                return new JsonResult("原因：网络不好；验证次数用尽；", -1, "接口调用失败");
            }
           // System.out.println("身份证二要素实名制检测返回：" + result);
            JSONObject resultObj = JSON.parseObject(result);
            String status = resultObj.get("status").toString();
            String msg = resultObj.get("msg").toString();
            if ("01".equals(status)) {
            	//修改用户实名状态
            	onAuthSaveLnrInfo(mobile, type, idCard, name, accountNo, cardType, key);
            	return new JsonResult("", 0, msg);
            } else if ("205".equals(status)) {
            	return new JsonResult(status, -1, "您输入的身份证号码与本人不匹配，请重新输入！");
            } else {
                return new JsonResult(status, -1, "您的实名认证未通过，请重新进行实名认证！");
            }
        } else if ("3".equals(type)) { // 银行卡三要素实名制检测
    		list.add(new NameValue("idCard",idCard));
    		list.add(new NameValue("name", name));
    		list.add(new NameValue("accountNo", accountNo));
    		String paramobj = URLEncodedUtils.format(list,"UTF-8");
            result = HttpUtil.realNamePost(check3url, "?" + paramobj, appCode);
            // 处理返回值
            if ("-1".equals(result)) {
                return new JsonResult("原因：网络不好；验证次数用尽；", -1, "接口调用失败");
            }
            JSONObject resultObj = JSON.parseObject(result);
            String status = resultObj.get("status").toString();
            String msg = resultObj.get("msg").toString();
            if ("01".equals(status)) {
            	//修改用户实名状态
            	onAuthSaveLnrInfo(mobile, type, idCard, name, accountNo, cardType, key);
            	return new JsonResult("", 0, msg);
            } else {
                return new JsonResult(status, -1, "您提交的实名认证信息有错误，请重新填写！");
            }
        } else {
        	return new JsonResult("type不存在", -1, "type不存在");
        }
	}
	
	//注册后添加默认就诊人
    @Override
    public void savePatient (SysCustormer info) {
        String url = PropertiesUtil.getRegister("saveOrupdatePatientBaseInfo");
        String xb = "";
        if (StringUtils.isNotBlank(info.getSfzh())) {
            String str = info.getSfzh().substring(16, 17);
            int num = Integer.parseInt(str) % 2;
            if (num == 0) {
                xb = "女";
            } else {
                xb = "男";
            }
        }
        url = url + "?name=" + info.getName() + "&sfzh=" + info.getSfzh() + "&phone=" + info.getPhone()
                + "&patientSign=1" + "&username=" + info.getPhone() + "&xb=" + xb;
        logger.info("实名认证添加默认就诊人请求：" + url);
        ResponseEntity<String> postForEntity = restTemplate.getForEntity(url, String.class);
        String body = postForEntity.getBody();
        logger.info("实名认证添加默认就诊人返回：" + body);
    }
    
    //实名认证后，把账号默认设置为家庭成员
    private void onAuthSaveLnrInfo(String mobile, String type, String sfzh, 
    			String name, String accountNo, Integer cardType, String key) {
    	//修改用户是否实名状态
    	SysCustormer custormer = dao.getCUstormerInfoByPhone(mobile);
    	Integer isReal = "3".equals(type) ? 2 : 1;
    	custormer.setSfzh(sfzh);
    	custormer.setName(name);
    	custormer.setIsRealName(isReal);
    	custormer.setBankCardNumber(accountNo);
    	custormer.setBankCardType(cardType);
    	dao.save(custormer);
    	//添加默认就诊人
        savePatient(custormer);
    	//更新缓存中用户信息
    	if(!StringUtils.isNull(key)) {
    		CacheUntil.put(RedisCacheEmun.USER_CACHE, key+encryStr, custormer);
    	}
    	/* 默认添加本人为联系人 */

        // 封装联系人
        SysLnr lnrTemp = sysLnrDao.getLnrInfoByCon(custormer.getId(), custormer.getUsername());
        if(lnrTemp != null) {
        	lnrTemp.setName(custormer.getName());
        	lnrTemp.setPhone(custormer.getPhone());
        	lnrTemp.setSfzh(custormer.getSfzh());
        	lnrTemp.setIsChild("2");
            // 保存
            sysLnrDao.save(lnrTemp);
        }else {
        	// 封装联系人
            SysLnr sysLnr = new SysLnr();
            sysLnr.setId(UUIDUtil.getUUID());
            sysLnr.setName(custormer.getName());
            sysLnr.setPhone(custormer.getPhone());
            sysLnr.setSfzh(custormer.getSfzh());
            sysLnr.setIsChild("2");

            // 封装关联表
            SysCustormerLnr sysCustormerLnr = new SysCustormerLnr();
            sysCustormerLnr.setId(UUIDUtil.getUUID());
            sysCustormerLnr.setCustormerId(custormer.getId());
            sysCustormerLnr.setLnrId(sysLnr.getId());

            // 默认为 1本人
            sysCustormerLnr.setGx("1");

            // 保存
            sysLnrDao.save(sysLnr);
            sysCustormerLnrDao.save(sysCustormerLnr);
        }
    }
}
