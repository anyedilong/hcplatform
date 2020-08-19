package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.MainRepository;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;
@Named
public class MainDao extends BaseDao<MainRepository, SysCustormer>{

    public SysCustomerDto getCustomer(SysCustomerDto dto) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.id,c.phone,c.password,c.sfzh,c.name,c.custormer_url custormerUrl");
//        sql.append("   (f.url || '/' || f.file_name) url ");
        sql.append(" from sys_custormer c ");
//        sql.append(" left join blt_sign s on s.jmid = c.jmid ");
//        sql.append(" left join blt_file f on f.relation_id = c.id and f.type = '1' ");
        sql.append(" where 1=1 ");
        if (StringUtils.isNotBlank(dto.getPhone()))
            sql.append(" and c.phone = :phone ");
        if (StringUtils.isNotBlank(dto.getSfzh())) {
            sql.append(" and c.sfzh = :sfzh ");
        }
       // sql.append(" and c.status = '0' ");
        Map<String, String> map = new HashMap<>(16);
        map.put("phone", dto.getPhone());
        return queryOne(sql.toString(), map, SysCustomerDto.class);
    }
    
    public SysCustomerDto getCustomers(SysCustomerDto dto) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.id, c.phone,c.password");
//        sql.append("   (f.url || '/' || f.file_name) url ");
        sql.append(" from sys_custormer c ");
//        sql.append(" left join blt_sign s on s.jmid = c.jmid ");
//        sql.append(" left join blt_file f on f.relation_id = c.id and f.type = '1' ");
        sql.append(" where 1=1 ");
        if (StringUtils.isNotBlank(dto.getPhone()))
            sql.append(" and c.phone = :phone ");
        if (StringUtils.isNotBlank(dto.getSfzh())) {
            sql.append(" and c.sfzh = :sfzh ");
        }
        Map<String, String> map = new HashMap<>(16);
        sql.append(" and c.status = '0' ");
        map.put("phone", dto.getPhone());
        return queryOne(sql.toString(), map, SysCustomerDto.class);
    }
    public SysCustormer getSysCustomerByJmid(String jmid) {
        Map<String, String> map = new HashMap<>(16);
        map.put("jmid", jmid);
        String sql = "select * from sys_customer where jmid = :jmid";
        return queryOne(sql, map, SysCustormer.class);
    }

    public SysCustormer getCustomerDetail(String phone) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.* ");
        sql.append(" from sys_custormer c ");
        sql.append(" where c.phone = :phone ");
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        return queryOne(sql.toString(), map, SysCustormer.class);
    }
}
