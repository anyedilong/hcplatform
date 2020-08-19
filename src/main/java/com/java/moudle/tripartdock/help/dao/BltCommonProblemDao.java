package com.java.moudle.tripartdock.help.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.tripartdock.help.admin.BltCommonProblem;
import com.java.moudle.tripartdock.help.dao.repository.BltCommonProblemRepository;
import com.java.until.dba.BaseDao;

@Named
public class BltCommonProblemDao extends BaseDao<BltCommonProblemRepository, BltCommonProblem>{

	public List<BltCommonProblem> getCommonProblem() {
		StringBuffer sql = new StringBuffer("select t.* from BLT_COMMON_PROBLEM t where t.status = '1' ");
		Map<String, String> param = new HashMap<String, String>();
		sql.append(" order by t.wtfl, t.create_time desc");
		return queryList(sql.toString(), param, BltCommonProblem.class);
	}

}
