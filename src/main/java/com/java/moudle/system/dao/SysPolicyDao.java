package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysPolicyRepository;
import com.java.moudle.system.domain.SysPolicy;
import com.java.moudle.system.dto.PolicyDto;
import com.java.until.StringUntil;
import com.java.until.StringUtils;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysPolicyDao extends BaseDao<SysPolicyRepository, SysPolicy> {

	
	public void getPolicyPage(SysPolicy policy, PageModel page) {
		Map<String, Object> map = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_policy u ");
		sql.append(" where 1 = 1 ");
		if(!StringUntil.isNull(policy.getPolicyTitle())) {
			sql.append(" and u.policy_title = :policyTitle ");
			map.put("policyTitle", policy.getPolicyTitle());
		}
		if(!StringUntil.isNull(policy.getStatus())) {
			sql.append(" and u.status = :status ");
			map.put("status", policy.getStatus());
		}
		sql.append(" order by u.status desc, u.pub_time desc ");
		queryPageList(sql.toString(), policy, page, SysPolicy.class);
	}
	
	public List<SysPolicy> getPolicyList(String searchContent) {
		return repository.getPolicyList(searchContent);
	}

	public PolicyDto getPolicyInfo(PolicyDto dto) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.*,r.role_type ");
		sql.append(" from sys_policy p ");
		sql.append(" join sys_user_role ur on ur.user_id = p.top_user ");
		sql.append(" join sys_role r on r.id = ur.role_id ");
		sql.append(" where r.status = '0' ");
		if(StringUtils.isNotBlank(dto.getTopUser())) {
			sql.append(" and p.top_user = :topUser ");
		}
		return queryOne(sql.toString(), dto, PolicyDto.class);
	}

}
