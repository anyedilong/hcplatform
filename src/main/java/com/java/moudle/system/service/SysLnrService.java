package com.java.moudle.system.service;

import com.java.moudle.common.service.BaseService;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:32
 **/
public interface SysLnrService extends BaseService<SysLnr> {
    
    /**
     * @Description 获取联系人详情 
     * @Author zw
     * @Date 08:57 2020-04-26
     * @Param id
     * @return 
     **/
    AddLxrDto getSysLnrDetails(String id);
    
    int isExist(String lxrSfzh, String customeerId);
    
    SysLnr getLnrInfo(String sfzh);
}
