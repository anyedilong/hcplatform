package com.java.moudle.system.service.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.system.dao.SysVisitStatDao;
import com.java.moudle.system.domain.VisitStat;
import com.java.moudle.system.dto.VisitLineChartsDto;
import com.java.moudle.system.dto.VisitStatDto;
import com.java.moudle.system.service.VisitStatService;
import com.java.until.DateUtils;
import com.java.until.StringUntil;
import com.java.until.UUIDUtil;

@Service
@Transactional
public class VisitStatServiceImpl implements VisitStatService {

	@Inject
	private SysVisitStatDao visitStatDao;
    
    //添加统计信息
	@SuppressWarnings("static-access")
	@Override
    public void add(String type, String userId, String hostIp) throws Exception {
    	
    	//String hostIp = InetAddress.getLocalHost().getHostAddress();
    	Date maxVisitDate = null;
    	if(!StringUntil.isNull(userId)) {
    		maxVisitDate = visitStatDao.getMaxVisitDate(userId, "");
    	}else {
    		maxVisitDate = visitStatDao.getMaxVisitDate("", hostIp);
    	}
    	if(maxVisitDate == null || this.getTime(maxVisitDate, "minute") > 1) {
    		VisitStat visitStat = new VisitStat();
        	visitStat.setId(UUIDUtil.getUUID());
            visitStat.setType(type);
            visitStat.setUserId(userId);
            visitStat.setVisitTime(new Date());
            visitStat.setUserIp(hostIp);
            visitStatDao.save(visitStat);
    	}
    }

    //查询访问量
	@Override
    public VisitStatDto visitStat(VisitStatDto dto) throws Exception {
    	VisitStatDto resp = visitStatDao.visitStat(dto);
        //int days = (int) DateUtils.pastDays(resp.getMinVisitTime());
        int days = (int) getTime(resp.getMinVisitTime(), "day");
        if (days == 0)
            resp.setAverageCount(resp.getTotalCount());
        else
            resp.setAverageCount(resp.getTotalCount() / days);
        resp.setRegisterCount(resp.getTotalCount() - resp.getUnregisteredCount());
        resp.setMinVisitTime(null);
        return resp;
    }
    
	//访问量统计charts
	@Override
	public Map<String, Object> visitCharts(VisitStatDto dto)throws Exception {
		List<String> xList = new ArrayList<>();
		List<String> yList = new ArrayList<>();
		List<VisitLineChartsDto> list = new ArrayList<>();
		if("2".equals(dto.getType())) {
			list = visitStatDao.visitChartsByUser(dto);
		}else {
			list = visitStatDao.visitCharts(dto);
		}

		Calendar cal = Calendar.getInstance();  
        //cal.get(Calendar.YEAR);  
		//填充月和日的空缺x轴坐标，默认为零
		if("2".equals(dto.getStatType())) {
			int month = cal.get(Calendar.MONTH) + 1;
			for(int i = 1; i <= month; i++) {
				xList.add(i+"月");
				yList.add("0");
			}
		}else if("3".equals(dto.getStatType())) {
			int day = cal.get(Calendar.DAY_OF_MONTH); 
			for(int i = 1; i <= day; i++) {
				if(i < 10) {
					xList.add("0"+i);
				}else {
					xList.add(i+"");
				}
				yList.add("0");
			}
		}
		
		if(list != null && list.size() > 0) {
			//年直接从查询数据中进行分离坐标值
			if("1".equals(dto.getStatType())) {
				for(int i = 0; i < list.size(); i++) {
					xList.add(list.get(i).getName());
					yList.add(list.get(i).getNum());
				}
			}else {
				//对有数据的月或日进行赋值
				for(int i = 0; i < xList.size(); i++) {
					for(int j = 0; j < list.size(); j++) {
						if("2".equals(dto.getStatType())) {
							if(list.get(j).getName().contains(xList.get(i))) {
								yList.set(i, list.get(j).getNum());
							}
						}else if("3".equals(dto.getStatType())) {
							String day = list.get(j).getName().substring(3);
							if(xList.get(i).equals(day)) {
								yList.set(i, list.get(j).getNum());
							}
						}
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("xlist", xList);
		map.put("yList", yList);
		return map;
	}
	
	//获取两个时间相差分钟数
    public static long getTime(Date oldTime, String type) throws Exception {
    	if(StringUntil.isNull(type)) {
    		return 0;
    	}
    	if("minute".equals(type)) {
    		return DateUtils.pastMinutes(DateUtils.parseDate(DateUtils.formatDate(oldTime, "yyyy-MM-dd HH:mm:ss")));
    	}else if("day".equals(type)) {
    		return DateUtils.pastDays(DateUtils.parseDate(DateUtils.formatDate(oldTime, "yyyy-MM-dd")));
    	}
		return 0;
    }
}
