package com.java.moudle.system.service;

import java.util.Map;

import com.java.moudle.system.dto.VisitStatDto;

public interface VisitStatService {

	//保存
    void add(String type, String userId, String hostIp) throws Exception;
    //访问量统计
    VisitStatDto visitStat(VisitStatDto dto) throws Exception ;
    //访问量统计charts
    Map<String, Object> visitCharts(VisitStatDto dto)throws Exception ;
}
