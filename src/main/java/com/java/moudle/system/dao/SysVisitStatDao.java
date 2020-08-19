package com.java.moudle.system.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.moudle.system.dao.repository.SysVisitStatRepository;
import com.java.moudle.system.domain.VisitStat;
import com.java.moudle.system.dto.VisitLineChartsDto;
import com.java.moudle.system.dto.VisitStatDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;

@Repository
public class SysVisitStatDao extends BaseDao<SysVisitStatRepository, VisitStat> {

	/**
	 * @Description: 通过ip查询最近的时间
	 * @param @param dto
	 * @param @return
	 * @return VisitStatDto
	 * @throws
	 */
	public Date getMaxVisitDate(String userId, String hostIp) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" select t.visit_time ");
        sql.append(" from blt_visit_stat t ");
        sql.append(" where 1 = 1 ");
        if(!StringUntil.isNull(userId)) {
        	sql.append(" and t.user_id = :userId ");
        	paramMap.put("userId", userId);
        }
        if(!StringUntil.isNull(hostIp)) {
        	sql.append(" and t.user_ip = :hostIp ");
        	paramMap.put("hostIp", hostIp);
        }
        return queryOne(sql.toString(), paramMap, Date.class);
    }
	
    //访问量统计
    public VisitStatDto visitStat(VisitStatDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append("   count(case when to_char(sysdate,'yyyy-MM-dd') = to_char(t.visit_time,'yyyy-MM-dd') then 1 else null end) today_count, ");
        sql.append("   min(t.visit_time) min_visit_time, ");
        sql.append("   max(case when 1=1 then (select c ");
        sql.append("     from (select c,order_num from (select c,row_number() over(order by c desc) order_num from (select count(1) c ");
        sql.append("     from blt_visit_stat vs group by to_char(vs.visit_time,'yyyy-MM-dd'))  ) where order_num = 1) d) else null end) max_count, ");
        sql.append("   count(1) total_count, ");
        sql.append("   count(case when t.type = '0' then 1 else null end) unregistered_count ");
        sql.append(" from blt_visit_stat t ");
        sql.append(" where 1=1 ");
        if (dto.getVisitTime() != null) {
            sql.append(" and to_char(t.visit_time,'yyyy-MM-dd') = to_char(:visitTime,'yyyy-MM-dd') ");
        }
        return queryOne(sql.toString(), dto, VisitStatDto.class);
    }

    public List<VisitLineChartsDto> visitChartsByUser(VisitStatDto dto){
    	StringBuilder sql = new StringBuilder();
    	
    	if("1".equals(dto.getStatType())) {
    		sql.append(" select concat(name, '年') as name, num  "); 
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" select concat(to_char(to_date(name, 'yyyy-mm'), 'mm'), '月') as name, num  "); 
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" select  to_char(to_date(name, 'yyyy-mm-dd'), 'mm-dd') as name, num  "); 
    	}
    	sql.append(" from ( select "); 
    	if("1".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy') as name, ");
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy-mm') as name, ");
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy-mm-dd') as name, ");
    	}
    	sql.append(" count(a.user_id) as num ");
    	sql.append(" from blt_visit_stat a ");
    	sql.append(" where 1 = 1 ");
    	if(!StringUntil.isNull(dto.getType()) && "2".equals(dto.getType())) {
    		sql.append(" and a.type = :type ");
    	}
    	if("1".equals(dto.getStatType())) {
    		sql.append(" group by to_char(a.visit_time, 'yyyy') ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy') ");
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" and to_char(a.visit_time, 'yyyy') = to_char(sysdate, 'yyyy') ");
    		sql.append(" group by to_char(a.visit_time, 'yyyy-mm')  ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy-mm') ");
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" and to_char(a.visit_time, 'yyyy-mm') = to_char(sysdate, 'yyyy-mm')  ");
    		sql.append(" group by to_char(a.visit_time, 'yyyy-mm-dd') ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy-mm-dd') ");
    	}
    	sql.append(" ) s "); 
    	
    	return queryList(sql.toString(), dto, VisitLineChartsDto.class);
    }
    
    public List<VisitLineChartsDto> visitCharts(VisitStatDto dto){
    	StringBuilder sql = new StringBuilder();
    	
    	if("1".equals(dto.getStatType())) {
    		sql.append(" select concat(name, '年') as name, num  "); 
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" select concat(to_char(to_date(name, 'yyyy-mm'), 'mm'), '月') as name, num  "); 
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" select  to_char(to_date(name, 'yyyy-mm-dd'), 'mm-dd') as name, num  "); 
    	}
    	sql.append(" from ( select "); 
    	if("1".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy') as name, ");
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy-mm') as name, ");
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" to_char(a.visit_time, 'yyyy-mm-dd') as name, ");
    	}
    	sql.append(" count(a.user_ip) as num ");
    	sql.append(" from blt_visit_stat a ");
    	sql.append(" where 1 = 1 ");
    	if("1".equals(dto.getStatType())) {
    		sql.append(" group by to_char(a.visit_time, 'yyyy') ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy') ");
    	}else if("2".equals(dto.getStatType())) {
    		sql.append(" and to_char(a.visit_time, 'yyyy') = to_char(sysdate, 'yyyy')  ");
    		sql.append(" group by to_char(a.visit_time, 'yyyy-mm') ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy-mm') ");
    	}else if("3".equals(dto.getStatType())) {
    		sql.append(" and to_char(a.visit_time, 'yyyy-mm') = to_char(sysdate, 'yyyy-mm')  ");
    		sql.append(" group by to_char(a.visit_time, 'yyyy-mm-dd') ");
    		sql.append(" order by  to_char(a.visit_time, 'yyyy-mm-dd') ");
    	}
    	sql.append(" ) s "); 
    	
    	return queryList(sql.toString(), dto, VisitLineChartsDto.class);
    }
   
}
