package com.java.moudle.system.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.dba.BaseDomain;

/**
 * 办事指南表(sys_guide)
 * 
 * @author ljf
 * @version 1.0.0 2020-03-05
 */
@Entity
@Table(name = "sys_guide")
public class SysGuide extends BaseDomain implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5307167765664259731L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 主键 */
    @Id
    private String id;

    /** 办事指南类别，指南类别表的主键 */
    private String guideType;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 状态 (0.未提交 1.提交-审核中 2.审核通过 5.退回 6.撤回)  */
    private String status;

    /** 创建时间 */
    @Column(updatable = false)
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    
    /** 创建人 */
    @Column(updatable = false)
    private String createUser;
    
    /** 封面url */
    private String guideUrl;
    
    /** 发表时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date pubTime;
    
    // 查询开始时间
    @Transient
    private String startTime;
    
    // 查询结束时间
    @Transient
    private String endTime;
    
    // 退回原因
    private String remarks;
    
    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置主键
     * 
     * @param id
     *          主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取办事指南类别，指南类别表的主键
     * 
     * @return 办事指南类别
     */
    public String getGuideType() {
        return this.guideType;
    }

    /**
     * 设置办事指南类别，指南类别表的主键
     * 
     * @param guideType
     *          办事指南类别
     */
    public void setGuideType(String guideType) {
        this.guideType = guideType;
    }

    /**
     * 获取标题
     * 
     * @return 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     * 
     * @param title
     *          标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
     * 获取状态  1正常 2冻结 3删除
     * 
     * @return 状态  1正常 2冻结 3删除
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态  1正常 2冻结 3删除
     * 
     * @param status
     *          状态  1正常 2冻结 3删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getGuideUrl() {
		return guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}