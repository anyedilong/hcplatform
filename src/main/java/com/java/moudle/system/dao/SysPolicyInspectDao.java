package com.java.moudle.system.dao;

import com.java.moudle.system.dao.repository.SysPolicyInspectRepository;
import com.java.moudle.system.domain.SysPolicyInspect;
import com.java.moudle.system.dto.PolicyDto;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysPolicyInspectDao extends BaseDao<SysPolicyInspectRepository, SysPolicyInspect> {

    //分页查询政策法规
    public void getPolicyPage(PolicyDto dto, PageModel page) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.id,p.policy_title,p.type,p.reprinted_from, ");
        sql.append("   p.policy_ly,p.policy_content,p.status,p.create_time ");
        sql.append(" from sys_policy_inspect p ");
        sql.append(" where p.delete_flg = '0' ");
        if (StringUtils.isNotBlank(dto.getPolicyTitle())) {
            sql.append(" and p.policy_title like concat('%', concat(:policyTitle, '%')) ");
        }
        if (dto.getStartDate() != null) {
            sql.append(" and p.create_time >= :startDate ");
        }
        if (dto.getEndDate() != null) {
            sql.append(" and p.create_time < :endDate ");
        }
        if (StringUtils.isNotBlank(dto.getStatus())) {
            sql.append(" and p.status != :status ");
        }
        //权限roleType 1.超级管理员 2.管理员 3.普通用户
  		if("1".equals(dto.getRoleType())) {
  			sql.append(" and p.status in ('3', '4') ");
  		}else if("2".equals(dto.getRoleType())) {
  			sql.append(" and p.status in ('1', '2', '3', '4') ");
  			sql.append(" and p.create_user in ( ");
  			List<String> ids = dto.getUserIds();
  			for(int i = 0; i < ids.size(); i++) {
  				if(i == 0) {
  					sql.append(" '"+ids.get(i)+"' ");
  				}else {
  					sql.append(" ,'"+ids.get(i)+"' ");
  				}
  			}
  			sql.append(" ) ");
  		}else if("3".equals(dto.getRoleType())) {
  			String userId = dto.getUserIds().get(0);
  			sql.append(" and p.create_user = '"+userId+"' ");
  		}
        sql.append(" order by p.pub_time desc, p.create_time desc ");
        queryPageList(sql.toString(), dto, page, PolicyDto.class);
    }

    public List<PolicyDto> getPolicyList(PolicyDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.id,p.policy_title,p.type,p.reprinted_from, ");
        sql.append("   p.policy_ly,p.policy_content,p.status,p.create_time ");
        sql.append(" from sys_policy_inspect p ");
        sql.append(" where p.delete_flg = '0' ");
        if (StringUtils.isNotBlank(dto.getPolicyTitle())) {
            sql.append(" and p.policy_title = :policyTitle ");
        }
        if (StringUtils.isNotBlank(dto.getId())) {
            sql.append(" and p.id <> :id ");
        }

        return queryList(sql.toString(), dto, PolicyDto.class);
    }

    public int queryTopNum() {
    	StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) ");
        sql.append(" from sys_policy_inspect p ");
        sql.append(" where p.delete_flg = '0' and p.status = '4' ");
        return queryOne(sql.toString(), null, Integer.class);
    }
    
    //删除
    public void delPolicy(PolicyDto dto) {
        repository.delPolicy(dto.getId(), dto.getDeleteFlg());
    }

    //修改状态
    public void updateStatus(PolicyDto dto) {
        repository.updateStatus(dto.getId(), dto.getStatus());
    }
}
