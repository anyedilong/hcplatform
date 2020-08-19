package com.java.moudle.system.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.service.SysLnrService;
import com.java.until.StringUntil;
import com.java.until.SysUtil;

/**
 * <p>Title: SysUserController.java</p>
 * <p>Description : 联系人信息管理</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * @author : tz
 * @date : 2020/3/4 15:01
 * @version : V1.0.0
 */

@RestController
@RequestMapping("/sys/lnr")
public class SysLnrController  extends BaseController {
    
	@Inject
    private SysLnrService lnrService;

	
	/**
	 * @Description: 判断联系人是否已经添加
	 * @param @return
	 * @return JsonResult
	 * @throws
	 */
    @RequestMapping("isExist")
    public JsonResult isExist () {
       try {
    	   String param = getParam(request);
           if(StringUntil.isNull(param)) {
        	   return jsonResult(null, 9001, "参数不能为空!");
           }
           JSONObject paramObj = JSON.parseObject(param);
           String lxrsfzh = paramObj.getString("lxrsfzh");
           if(StringUntil.isNull(lxrsfzh)) {
        	   return jsonResult(null, 9001, "参数不能为空!");
           }
           String custormerId = SysUtil.sysUser(request, response).getId();
           //根据需求不能添加重复的联系人
           int count = lnrService.isExist(lxrsfzh, custormerId);
           if(count > 0) {
        	   return jsonResult(null, 9001, "请不要重复添加联系人!");
           }
           return jsonResult();
       }catch(Exception e) {
    	   e.printStackTrace();
    	   return jsonResult(null, -1, "系统错误");
       }
    }

}
