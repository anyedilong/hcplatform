package com.java.moudle.system.service;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dto.PolicyDto;
import com.java.until.dba.PageModel;

public interface SysPolicyInspectService {

    void getPolicyPage(PolicyDto dto, PageModel page);

    PolicyDto getPolicyInfo(PolicyDto dto);

    JsonResult savePolicy(PolicyDto dto);

    JsonResult updatePolicyInspect(PolicyDto dto);

    JsonResult updatePolicy(PolicyDto dto);

    JsonResult operate(PolicyDto dto);

    JsonResult delPolicy(PolicyDto dto);

    JsonResult submit(PolicyDto dto);

    JsonResult decide(PolicyDto dto);

    JsonResult issue(PolicyDto dto);

    JsonResult withdraw(PolicyDto dto);

    JsonResult top(PolicyDto dto);
}
