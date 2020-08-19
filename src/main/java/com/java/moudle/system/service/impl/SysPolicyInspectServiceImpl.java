package com.java.moudle.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dao.SysPolicyDao;
import com.java.moudle.system.dao.SysPolicyInspectDao;
import com.java.moudle.system.domain.SysPolicy;
import com.java.moudle.system.domain.SysPolicyInspect;
import com.java.moudle.system.dto.PolicyDto;
import com.java.moudle.system.service.SysPolicyInspectService;
import com.java.until.StringUtils;
import com.java.until.ToJavaUtils;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import com.java.until.ftpup.UpUtils;

@Service
@Transactional
public class SysPolicyInspectServiceImpl implements SysPolicyInspectService {

    @Value("${ftpUrl}")
    private String ftpUrl;
    @Autowired
    private SysPolicyInspectDao sysPolicyInspectDao;
    @Autowired
    private SysPolicyDao sysPolicyDao;

    //分页查询政策法规
    @Override
    public void getPolicyPage(PolicyDto dto, PageModel page) {
        /*SysUserDto sysUserDto = sysUserDao.getUserInfo(new SysUserDto(dto.getCreateUser()));
        if (sysUserDto != null && ("1".equals(sysUserDto.getRoleType()) || "2".equals(sysUserDto.getRoleType())))
            dto.setStatus("5");*/
        sysPolicyInspectDao.getPolicyPage(dto, page);
        /*List<PolicyDto> list = page.getList();
        if (list != null && list.size() > 0) {
            for (PolicyDto policyDto : list) {
                policyDto.setPolicyUrl(ftpUrl + policyDto.getPolicyUrl());
            }
        }*/
    }

    //查询政策法规详情
    @Override
    public PolicyDto getPolicyInfo(PolicyDto dto) {
    	SysPolicyInspect query = sysPolicyInspectDao.get(dto.getId());
        PolicyDto resp = new PolicyDto();
        ToJavaUtils.copyFields(query, resp);
        /*if (query != null)
            resp.setPolicyUrl(ftpUrl + resp.getPolicyUrl());*/
        return resp;
    }

    //新增
    @Override
    public JsonResult savePolicy(PolicyDto dto) {
        List<PolicyDto> list = sysPolicyInspectDao.getPolicyList(new PolicyDto(dto.getPolicyTitle()));
        if (list != null && list.size() > 0)
            return new JsonResult(null, 9001, "标题重复！");
        SysPolicyInspect policyInspect = new SysPolicyInspect();
        dto.setId(UUIDUtil.getUUID());
        ToJavaUtils.copyFields(dto, policyInspect);

        if (dto.getImgFile() != null && !dto.getImgFile().isEmpty()) {
            JSONObject json = UpUtils.uploadImage(dto.getImgFile(), "/certificate/");
            String resultCode = json.getString("resultCode");
            if (!"0".equals(resultCode))
                return new JsonResult(null, 9001, "图片上传失败");
            policyInspect.setPolicyUrl(ftpUrl + json.getString("path"));
        }

        Date row = new Date();
        policyInspect.setStatus("0");
        policyInspect.setCreateTime(row);
        policyInspect.setDeleteFlg("0");
        sysPolicyInspectDao.save(policyInspect);
        return new JsonResult();
    }

    //修改
    @Override
    public JsonResult updatePolicyInspect(PolicyDto dto) {
    	List<PolicyDto> list = sysPolicyInspectDao.getPolicyList(new PolicyDto(dto.getPolicyTitle()));
        if (list != null && list.size() > 0)
            return new JsonResult(null, 9001, "标题重复！");
        SysPolicyInspect policy = sysPolicyInspectDao.get(dto.getId());
        if (policy == null || "1".equals(policy.getDeleteFlg()))
            return updatePolicy(dto);
        if (!"0".equals(policy.getStatus()) && !"5".equals(policy.getStatus()) && !"6".equals(policy.getStatus()))
            return new JsonResult(null, 9001, "政策法规不能修改！");
        if (dto.getImgFile() != null && !dto.getImgFile().isEmpty()) {
            JSONObject json = UpUtils.uploadImage(dto.getImgFile(), ftpUrl + "/certificate/");
            String resultCode = json.getString("resultCode");
            if (!"0".equals(resultCode))
                return new JsonResult(null, 9001, "图片上传失败");
            policy.setPolicyUrl(json.getString("path"));
        }
        if ("5".equals(policy.getStatus())) {
            policy.setStatus("0");
        }
        policy.setPolicyTitle(dto.getPolicyTitle());
        policy.setType(dto.getType());
        policy.setPolicyLy(dto.getPolicyLy());
        policy.setReprintedFrom(dto.getReprintedFrom());
        policy.setPolicyContent(dto.getPolicyContent());
        sysPolicyInspectDao.save(policy);
        return new JsonResult();
    }

    @Override
    public JsonResult updatePolicy(PolicyDto dto) {
    	List<PolicyDto> list = sysPolicyInspectDao.getPolicyList(dto);
        if (list != null && list.size() > 0)
            return new JsonResult(null, 9001, "标题重复！");
    	SysPolicyInspect policy = sysPolicyInspectDao.get(dto.getId());
        if (policy == null)
            return new JsonResult(null, 9001, "政策法规不存在！");
        if (!"0".equals(policy.getStatus()) && !"5".equals(policy.getStatus()) && !"6".equals(policy.getStatus()))
            return new JsonResult(null, 9001, "政策法规不能修改！");
        if (dto.getImgFile() != null && !dto.getImgFile().isEmpty()) {
            JSONObject json = UpUtils.uploadImage(dto.getImgFile(), ftpUrl + "/certificate/");
            String resultCode = json.getString("resultCode");
            if (!"0".equals(resultCode))
                return new JsonResult(null, 9001, "图片上传失败");
            policy.setPolicyUrl(json.getString("path"));
        }
        if ("5".equals(policy.getStatus())) {
            policy.setStatus("0");
        }
        policy.setPolicyTitle(dto.getPolicyTitle());
        policy.setType(dto.getType());
        policy.setPolicyLy(dto.getPolicyLy());
        policy.setReprintedFrom(dto.getReprintedFrom());
        policy.setPolicyContent(dto.getPolicyContent());
        sysPolicyInspectDao.save(policy);
        return new JsonResult();
    }

    //操作政策法规：0 删除，1 提交，2 5 审核，3 发布，44 33 置顶和取消置顶，6 撤回
    @Override
    public JsonResult operate(PolicyDto dto) {
        if ("0".equals(dto.getStatus())) {
            return delPolicy(dto);//删除
        } else if ("1".equals(dto.getStatus())) {
            return submit(dto);//提交
        } else if ("2".equals(dto.getStatus()) || "5".equals(dto.getStatus())) {
            if ("5".equals(dto.getStatus()) && StringUtils.isBlank(dto.getReturnReasons()))
                return new JsonResult("", 9001, "退回原因不能为空！");
            return decide(dto);//审核
        } else if ("3".equals(dto.getStatus())) {
            return issue(dto);//发布
        } else if ("4".equals(dto.getStatus())) {
            return top(dto);//置顶
        } else if ("6".equals(dto.getStatus())) {
            return withdraw(dto);//撤回
        }
        return new JsonResult("", 9001, "状态[status]错误！");
    }

    //删除
    @Override
    public JsonResult delPolicy(PolicyDto dto) {
        SysPolicyInspect policy = sysPolicyInspectDao.get(dto.getId());
        if ("0".equals(policy.getStatus()) || "5".equals(policy.getStatus()) || "6".equals(policy.getStatus())) {
        	dto.setDeleteFlg("1");
            sysPolicyInspectDao.delPolicy(dto);
        }else {
        	 return new JsonResult(null, 9001, "政策法规不能删除！");
        }
        return new JsonResult();
    }

    //提交
    @Override
    public JsonResult submit(PolicyDto dto) {
        SysPolicyInspect policy = sysPolicyInspectDao.get(dto.getId());
        //政策法规提交
        if ("0".equals(policy.getStatus()) || "5".equals(policy.getStatus()) || "6".equals(policy.getStatus())) {
        	dto.setStatus("1");
            sysPolicyInspectDao.updateStatus(dto);
        }else {
        	return new JsonResult(null, 9001, "政策法规不能提交！");
        }
        return new JsonResult();
    }

    //审核
    @Override
    public JsonResult decide(PolicyDto dto) {
        SysPolicyInspect policy = sysPolicyInspectDao.get(dto.getId());
        //审核表审核
        if (!"1".equals(policy.getStatus()))
            return new JsonResult(null, 9001, "政策法规不能审核！");
        policy.setStatus(dto.getStatus());
        if("2".equals(dto.getStatus())) {
        	//SysPolicy sysPolicy = new SysPolicy();
           // ToJavaUtils.copyFields(policy, sysPolicy);
        	//sysPolicy.setStatus(dto.getStatus());
           // sysPolicyDao.save(sysPolicy);
        }else {
        	policy.setReturnReasons(dto.getReturnReasons());
        }
        sysPolicyInspectDao.save(policy);
        return new JsonResult();
    }

    //发布
    @Override
    public JsonResult issue(PolicyDto dto) {
        SysPolicyInspect policyInspect = sysPolicyInspectDao.get(dto.getId());
        Date row = new Date();
        
        //审核政策表发布
        if (!"2".equals(policyInspect.getStatus()) && !"4".equals(policyInspect.getStatus()))
            return new JsonResult(null, 9001, "政策法规不能发布！");
        //SysPolicy sysPolicy = sysPolicyDao.get(dto.getId());
        SysPolicy sysPolicy = new SysPolicy();
        ToJavaUtils.copyFields(policyInspect, sysPolicy);
        sysPolicy.setPubTime(row);
        sysPolicy.setStatus("3");
        sysPolicyDao.save(sysPolicy);

        policyInspect.setStatus("3");
        policyInspect.setPubTime(row);
        sysPolicyInspectDao.save(policyInspect);
        return new JsonResult();
    }

    //撤回
    @Override
    public JsonResult withdraw(PolicyDto dto) {
    	SysPolicyInspect policyInspect = sysPolicyInspectDao.get(dto.getId());
        if (policyInspect == null)
            return new JsonResult(null, 9001, "政策法规不存在！");
        if ("3".equals(policyInspect.getStatus()) || "4".equals(policyInspect.getStatus())) {
        	policyInspect.setStatus("6");
            sysPolicyInspectDao.save(policyInspect);
            sysPolicyDao.delete(dto.getId());
        }else {
        	return new JsonResult(null, 9001, "政策法规不能撤回！");
        }
        return new JsonResult();
    }

    //置顶
    @Override
    public JsonResult top(PolicyDto dto) {
    	SysPolicyInspect policyInspect = sysPolicyInspectDao.get(dto.getId());
        if (policyInspect == null)
            return new JsonResult(null, 9001, "政策法规不存在！");
        if ("3".equals(policyInspect.getStatus())) {
        	int count = sysPolicyInspectDao.queryTopNum();
        	if(count == 0) {
        		SysPolicy policy = sysPolicyDao.get(dto.getId());
        		policy.setTopUser(dto.getCreateUser());
        		policy.setTopTime(new Date());
        		policy.setStatus(dto.getStatus());
        		
        		policyInspect.setStatus(dto.getStatus());
            	sysPolicyInspectDao.save(policyInspect);
        	}else {
        		return new JsonResult(null, 9001, "已经有了置顶政策法规，请先取消已有置顶信息，才能再次进行置顶操作！！");
        	}
        }else {
        	return new JsonResult(null, 9001, "政策法规不能置顶或取消置顶！");
        }
        return new JsonResult();
    }
}
