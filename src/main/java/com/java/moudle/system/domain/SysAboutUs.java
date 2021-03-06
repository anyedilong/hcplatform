package com.java.moudle.system.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * 关于我们表(sys_about_us)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_about_us")
public class SysAboutUs extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = 3434288154836226790L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标示 */
    @Id
    private String id;

    /** 内容 */
    private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    

    /* This code was generated by TableGo tools, mark 2 end. */
}