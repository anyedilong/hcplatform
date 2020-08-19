package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysLnrFileRepository;
import com.java.moudle.system.domain.SysLnrFile;
import com.java.until.dba.BaseDao;

import javax.inject.Named;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:42
 **/
@Named
public class SysLnrFileDao extends BaseDao<SysLnrFileRepository, SysLnrFile> {
    public SysLnrFile getSysLnrFileByLnrId(String id) {
        return repository.getSysLnrFileByLnrId(id);
    }
}
