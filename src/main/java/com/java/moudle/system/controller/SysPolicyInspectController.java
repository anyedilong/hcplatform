package com.java.moudle.system.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.controller.BaseController;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.PolicyDto;
import com.java.moudle.system.service.SysPolicyInspectService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.StringUtils;
import com.java.until.SysUtil;
import com.java.until.dba.PageModel;

/**
 * 政策法规
 * nyw
 */
@RestController
@RequestMapping("/sys/policyInspect")
public class SysPolicyInspectController extends BaseController {

    @Autowired
    private SysPolicyInspectService sysPolicyInspectService;
    @Inject
    private SysUserService userService;

    //分页查询政策法规
    @PostMapping("getPolicyPage")
    public JsonResult getPolicyPage() {
        try {
            String params = getParam(request);
            logger.info("分页查询政策法规请求参数：" + params);
            PolicyDto dto = JSON.parseObject(params, PolicyDto.class);
            if (dto.getPageNo() == null)
                dto.setPageNo(1);
            if (dto.getPageSize() == null)
                dto.setPageSize(10);
            PageModel page = new PageModel(dto.getPageNo(), dto.getPageSize());
            LoginInfoDto user = SysUtil.sysUser(request, response);
            if (user == null) {
                return jsonResult(null, 1001, "未登录！");
            }else {
    			List<String> childs = userService.getUserChilds(user.getId(), user.getRole().getRoleType());
    			dto.setUserIds(childs);
    			dto.setRoleType(user.getRole().getRoleType());
            }
            dto.setCreateUser(user.getId());
            sysPolicyInspectService.getPolicyPage(dto, page);
            logger.info("分页查询政策法规返回：" + JSON.toJSONString(page));
            return jsonResult(page);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    //查询政策法规详情
    @PostMapping("getPolicyInfo")
    public JsonResult getPolicyInfo() {
        try {
            String params = getParam(request);
            logger.info("查询政策法规详情请求参数：" + params);
            PolicyDto dto = JSON.parseObject(params, PolicyDto.class);
            if (StringUtils.isBlank(dto.getId()))
                return jsonResult(null, 9001, "ID不能为空！");
            PolicyDto resp = sysPolicyInspectService.getPolicyInfo(dto);
            logger.info("查询政策法规详情返回：" + JSON.toJSONString(resp));
            return jsonResult(resp);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    //新增政策法规
    @PostMapping("savePolicy")
    public JsonResult save(PolicyDto dto) {
        try {
            logger.info("保存政策法规请求参数：" + JSON.toJSONString(dto));
            //String msg = ValidateUtil.validateField(dto);
            //if (StringUtils.isNotBlank(msg))
                //return jsonResult(null, 9001, msg);
            if(StringUtils.isNull(dto.getPolicyTitle())) {
            	return jsonResult(null, 9001, "请输入政策法规标题！");
            }
            if(StringUtils.isNull(dto.getPolicyContent())) {
            	return jsonResult(null, 9001, "请输入政策法规内容！");
            }
            LoginInfoDto user = SysUtil.sysUser(request, response);
            if (user == null)
                return jsonResult(null, 1001, "未登录！");
            dto.setCreateUser(user.getId());
            JsonResult result = null;
            if (StringUtils.isBlank(dto.getId()))
                result = sysPolicyInspectService.savePolicy(dto);
            else
                result = sysPolicyInspectService.updatePolicy(dto);
            logger.info("保存政策法规返回：" + JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    //修改政策法规
    @PostMapping("updatePolicy")
    public JsonResult updatePolicy(PolicyDto dto) {
        try {
            logger.info("修改政策法规请求参数：" + JSON.toJSONString(dto));
            if(StringUtils.isNull(dto.getPolicyTitle())) {
            	return jsonResult(null, 9001, "请输入政策法规标题！");
            }
            if(StringUtils.isNull(dto.getPolicyContent())) {
            	return jsonResult(null, 9001, "请输入政策法规内容！");
            }
            if (StringUtils.isBlank(dto.getId()))
                return jsonResult(null, 9001, "ID不能为空！");
            JsonResult result = sysPolicyInspectService.updatePolicyInspect(dto);
            logger.info("修改政策法规返回：" + JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

    //操作政策法规
    @PostMapping("operate")
    public JsonResult operate() {
        try {
            String params = getParam(request);
            logger.info("操作政策法规请求参数：" + params);
            PolicyDto dto = JSON.parseObject(params, PolicyDto.class);
            if (StringUtils.isBlank(dto.getId()))
                return jsonResult(null, 9001, "ID不能为空！");
            LoginInfoDto user = SysUtil.sysUser(request, response);
            if (user == null)
                return jsonResult(null, 1001, "未登录！");
            dto.setCreateUser(user.getId());
            JsonResult result = sysPolicyInspectService.operate(dto);
            logger.info("操作政策法规返回：" + JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult(null, -1, "系统错误");
        }
    }

}
