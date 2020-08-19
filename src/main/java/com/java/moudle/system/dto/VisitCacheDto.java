package com.java.moudle.system.dto;

import java.io.Serializable;
import java.util.Date;

public class VisitCacheDto implements Serializable {
   
	private static final long serialVersionUID = 1213443475444L;
	
    private String type;//访问类型  0 未登陆 2 惠民平台
    private String userId;//访问用户ID
    private String hostIp;//访问者的ip
    private Date visitTime;//访问时间
   
    
    public VisitCacheDto() {
    	
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

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
}
