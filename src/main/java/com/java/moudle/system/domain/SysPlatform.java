package com.java.moudle.system.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * 	平台信息表(sys_platform)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_platform")
public class SysPlatform extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -448721153664578687L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标识 */
    @Id
    private String id;

    /** 系统logo */
    private String logo;

    /** 机构名称 */
    private String orgName;

    /** 平台名称 */
    private String platName;
    
    /** 欢迎词 */
    private String welcomeContent;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取唯一标识
     * 
     * @return 唯一标识
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置唯一标识
     * 
     * @param id
     *          唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public String getWelcomeContent() {
		return welcomeContent;
	}

	public void setWelcomeContent(String welcomeContent) {
		this.welcomeContent = welcomeContent;
	}

    /* This code was generated by TableGo tools, mark 2 end. */
}