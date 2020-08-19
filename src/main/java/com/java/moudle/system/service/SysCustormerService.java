package com.java.moudle.system.service;

import java.io.IOException;
import java.util.List;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.*;
import com.java.until.dba.PageModel;

public interface SysCustormerService extends BaseService<SysCustormer> {


    JsonResult updatePassword(SysCustomerDto dto);

    JsonResult addLxr(AddLxrDto addLxrDto);

    void getLxrList(String sfzh, PageModel pageModel);

    JsonResult delLxr(String id, String custormerId);

    void updateLxr(UpdateLxrDto updateLxrDto);

    //获取登录者的联系人
  	List<LoginInfoDto> getCutormerList(String sfzh);

  	SysCustormer queryInfoByCon(String id, String username);
  	
  	SysCustormer getCUstormerInfoByPhone(String telephone);

  	/**
  	 * 注册/新增联系人
  	 * @param custormer
  	 */
	void createUser(SysCustormer custormer);

    JsonResult updatePhone(UpdatePhoneDto updatePhoneDto);

    //获取个人资料
    SysCustomerDto getPersonData(String sfzh);
    //修改个人资料
    void updatePersonalData(String sfzh,String custormerUrl);

    void userlist(PageModel pageModel, SysCustormerDto sysCustormerDto);

    JsonResult userstatus(String status, String id);
    
    void updateUserHeadImage(String id, String headImage);

	JsonResult realNameTest(String type, String idCard, String name, String mobile, String accountNo) throws IOException;
	
	JsonResult realNameConfirm(String type, String idCard, String name, String mobile, String accountNo, Integer cardType, String key) throws IOException;

	void savePatient (SysCustormer info);
}
