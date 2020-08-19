package com.java.moudle.system.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * app更新控制(sys_app_version)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_app_version")
public class SysAppVersion extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = 343428754836226790L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标示 */
    @Id
    private String id;

    /** app的地址 */
    private String appUrl;
    private String version; //版本
    private String versionText;//版本说明
    private String type; //类型（0.可选 1.强制更新）
    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取唯一标示
     * 
     * @return 唯一标示
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置唯一标示
     * 
     * @param id
     *          唯一标示
     */
    public void setId(String id) {
        this.id = id;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionText() {
		return versionText;
	}

	public void setVersionText(String versionText) {
		this.versionText = versionText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    /* This code was generated by TableGo tools, mark 2 end. */
}