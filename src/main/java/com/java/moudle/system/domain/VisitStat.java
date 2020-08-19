package com.java.moudle.system.domain;

import com.java.until.dba.BaseDomain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 访问量统计
 *
 * @author nyw
 * @version 1.0.0 2020-03-019
 */
@Entity
@Table(name = "blt_visit_stat")
public class VisitStat extends BaseDomain {
   
	private static final long serialVersionUID = 16464661648631368L;
	
	@Id
    private String id;
    private String type;//访问类型 0 未登陆 1 后台管理 2 惠民平台
    private String userId;//访问用户ID
    private String userIp;//访问用户Ip
    private String visitPath;//访问路径
    private Date visitTime;//访问时间

    public VisitStat() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
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
}
