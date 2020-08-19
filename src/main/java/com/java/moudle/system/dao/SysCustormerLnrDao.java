package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysCustormerLnrRepository;
import com.java.moudle.system.domain.SysCustormerLnr;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.dto.LxrDto;
import com.java.moudle.system.dto.UpdateLxrDto;
import com.java.until.dba.BaseDao;

import javax.inject.Named;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:44
 **/
@Named
public class SysCustormerLnrDao extends BaseDao<SysCustormerLnrRepository, SysCustormerLnr> {

    public void updateLxrGx(String gx, String id, String custormerId) {
        repository.updateLxrGx(gx, id, custormerId);
    }

    /**
     * @Description  删除关联表
     * @Author zw
     * @Date 11:16 2020-04-09
     * @Param id 联系人id
     * @Param custormerId 当前登录客户id
     * @return void
     **/
    public void deleteSysCustormerLnr(String id, String custormerId) {
        repository.deleteSysCustormerLnr(id, custormerId);
    }

    public void updateLxrGxOther(UpdateLxrDto updateLxrDto) {
        repository.updateLxrGxOther(updateLxrDto);
    }
}
