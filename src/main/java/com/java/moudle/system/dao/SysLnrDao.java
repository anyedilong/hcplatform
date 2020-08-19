package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysLnrRepository;
import com.java.moudle.system.domain.SysLnr;
import com.java.moudle.system.dto.AddLxrDto;
import com.java.moudle.system.dto.LxrDto;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 14:33
 **/
@Named
public class SysLnrDao extends BaseDao<SysLnrRepository, SysLnr> {

    public void getLxrList(String custormerId, PageModel pageModel) {
        Map<String, String> map = new HashMap<>(16);
        map.put("custormerId", custormerId);
        StringBuffer sql = new StringBuffer();
        sql.append(" select l.id, l.name, cl.gx, cl.gx_other, l.phone, l.sfzh, l.is_child, l.guardian_name, c.custormer_url from sys_custormer_lnr cl ");
        sql.append(" join sys_lnr l on  l.id = cl.lnr_id ");
        sql.append(" left join sys_custormer c on c.id = cl.custormer_id ");
        sql.append(" where cl.custormer_id = :custormerId order by gx ");

        queryPageList(sql.toString(), map, pageModel, LxrDto.class);
    }

    public void delLxr(String id) {
        repository.delLxr(id);
    }

    public AddLxrDto getSysLnrDetails(String id) {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", id);

        StringBuffer sql = new StringBuffer();
        sql.append("select l.name, l.sfzh lxr_Sfzh, l.phone, l.is_child, l.guardian_name, lf.sfzhzm_url zm_Url, lf.sfzhfm_url fm_Url, cl.gx, cl.gx_other from sys_lnr l ");
        sql.append(" join sys_custormer_lnr cl on cl.lnr_id = l.id  ");
        sql.append(" left join sys_lnr_file lf on lf.lnr_id = l.id ");
        sql.append(" where l.id = :id ");

        return queryOne(sql.toString(), map, AddLxrDto.class);
    }
    
    public int isExist(String lxrSfzh, String customerId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(cl.id) ");
        sql.append(" from sys_lnr r  ");
        sql.append(" join sys_custormer_lnr cl on r.id = cl.lnr_id ");
        sql.append(" where r.sfzh = :lxrSfzh and cl.custormer_id = :customerId ");

        Map<String, String> map = new HashMap<>();
        map.put("lxrSfzh", lxrSfzh);
        map.put("customerId", customerId);
        
        return queryOne(sql.toString(), map, Integer.class);
    }
    
    public SysLnr getLnrInfo(String sfzh) {
    	StringBuffer sql = new StringBuffer();
        sql.append(" select r.* ");
        sql.append(" from sys_lnr r  ");
        sql.append(" join sys_custormer_lnr cl on r.id = cl.lnr_id ");
        sql.append(" where r.sfzh = :sfzh and cl.gx = '1' ");
        Map<String, String> map = new HashMap<>();
        map.put("sfzh", sfzh);
        return queryOne(sql.toString(), map, SysLnr.class);
    }
    
    public SysLnr getLnrInfoByCon(String customerId, String phone) {
    	StringBuffer sql = new StringBuffer();
        sql.append(" select r.* ");
        sql.append(" from sys_lnr r  ");
        sql.append(" join sys_custormer_lnr l on r.id = l.lnr_id ");
        sql.append(" where l.custormer_id = :customerId and r.phone = :phone ");
        Map<String, String> map = new HashMap<>();
        map.put("customerId", customerId);
        map.put("phone", phone);
        return queryOne(sql.toString(), map, SysLnr.class);
    }
}
