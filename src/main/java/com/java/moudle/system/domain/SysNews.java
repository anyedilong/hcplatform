package com.java.moudle.system.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.dba.BaseDomain;

/**
 * 系统消息表(sys_news)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_news")
public class SysNews extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -4090762220608734217L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标识 */
    @Id
    private String id;

    /** 消息标题 */
    private String title;

    /** 消息简介 */
    private String synopsis;

    /** 消息内容 */
    private String content;
    
    /** 创建时间 */
    @Column(updatable = false)
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    
    /** 创建人 */
    @Column(updatable = false)
    private String createUser;
    
    /** 状态（1.未发布 2.撤回 3.发布 4.置顶 ） */
    private String status;
    
    /** 发布时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date pubTime;
    
    private String deleteFlg;//删除标识0.正常 1.删除

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

    /* This code was generated by TableGo tools, mark 2 end. */
}