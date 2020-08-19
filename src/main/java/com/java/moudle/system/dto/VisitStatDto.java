package com.java.moudle.system.dto;

import java.io.Serializable;
import java.util.Date;

public class VisitStatDto implements Serializable {
   
	private static final long serialVersionUID = 12134434234L;
	
    private String id;
    private String type;//访问类型 0 未登陆 1 后台管理 2 惠民平台
    private String userId;//访问用户ID
    private String visitPath;//访问路径
    private Date visitTime;//访问时间
    private Date minVisitTime;//最早访问时间
    
    private String startDate;//开始时间
    private String endDate; //结束时间
    private String statType; //统计类型 1.年 2.月 3.日

    private Integer todayCount;//当天访问量
    private Integer averageCount;//平均访问量
    private Integer maxCount;//日最高访问量
    private Integer totalCount;//总访问量
    private Integer unregisteredCount;//未注册数量
    private Integer registerCount;//已注册数量

    public VisitStatDto() {
    }

    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitPath() {
        return visitPath;
    }

    public void setVisitPath(String visitPath) {
        this.visitPath = visitPath;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Integer todayCount) {
        this.todayCount = todayCount;
    }

    public Integer getAverageCount() {
        return averageCount;
    }

    public void setAverageCount(Integer averageCount) {
        this.averageCount = averageCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUnregisteredCount() {
        return unregisteredCount;
    }

    public void setUnregisteredCount(Integer unregisteredCount) {
        this.unregisteredCount = unregisteredCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Date getMinVisitTime() {
        return minVisitTime;
    }

    public void setMinVisitTime(Date minVisitTime) {
        this.minVisitTime = minVisitTime;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getStatType() {
		return statType;
	}


	public void setStatType(String statType) {
		this.statType = statType;
	}
}
