package com.java.moudle.system.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDomain;

/**
 * 政策法规(sys_policy)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_policy")
public class SysPolicy extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -273877394697587320L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标示 */
    @Id
    private String id;

    /** 政策法规标题 */
    private String policyTitle;

    /** 类型 1：公卫 2：家医 */
    private String type;

    /** 政策法规来源 */
    private String policyLy;

    /** 转载来源 **/
    private String reprintedFrom;

    /** 政策法规内容 */
    private String policyContent;

    /** 状态(0.未提交 1.提交-审核中 2.审核通过 3.发表 4.置顶 5.退回 6.撤回)  */
    private String status;

    /** 退回原因 **/
    private String returnReasons;

    /** 政策法规封面URL */
    private String policyUrl;

    /** 创建时间 */
    @Column(updatable = false)
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    
    /** 创建人 */
    @Column(updatable = false)
    private String createUser;

    /** 置顶时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date topTime;

    /** 置顶人 */
    private String topUser;
    
    /** 发布时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date pubTime;
    
    @Transient
    private String subTitle;

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

    /**
     * 获取政策法规标题
     * 
     * @return 政策法规标题
     */
    public String getPolicyTitle() {
        return this.policyTitle;
    }

    /**
     * 设置政策法规标题
     * 
     * @param policyTitle
     *          政策法规标题
     */
    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    /**
     * 获取政策法规来源
     * 
     * @return 政策法规来源
     */
    public String getPolicyLy() {
        return this.policyLy;
    }

    /**
     * 设置政策法规来源
     * 
     * @param policyLy
     *          政策法规来源
     */
    public void setPolicyLy(String policyLy) {
        this.policyLy = policyLy;
    }

    /**
     * 获取政策法规内容
     * 
     * @return 政策法规内容
     */
    public String getPolicyContent() {
        return this.policyContent;
    }

    /**
     * 设置政策法规内容
     * 
     * @param policyContent
     *          政策法规内容
     */
    public void setPolicyContent(String policyContent) {
    	if(StringUntil.isNotBlank(policyContent)) {
    		String reg = "[^\u4e00-\u9fa5]";
    		String temp = policyContent.replaceAll(reg, "");
			this.subTitle = temp.substring(0, (temp.length() > 60) ? 60 : temp.length());
    	}
        this.policyContent = policyContent;
    }

    /**
     * 获取状态
     * 
     * @return 状态
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态
     * 
     * @param status
     *          状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取政策法规封面URL
     * 
     * @return 政策法规封面URL
     */
    public String getPolicyUrl() {
        return this.policyUrl;
    }

    /**
     * 设置政策法规封面URL
     * 
     * @param policyUrl
     *          政策法规封面URL
     */
    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
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

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReprintedFrom() {
        return reprintedFrom;
    }

    public void setReprintedFrom(String reprintedFrom) {
        this.reprintedFrom = reprintedFrom;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }

    public String getTopUser() {
        return topUser;
    }

    public void setTopUser(String topUser) {
        this.topUser = topUser;
    }

    public String getReturnReasons() {
        return returnReasons;
    }

    public void setReturnReasons(String returnReasons) {
        this.returnReasons = returnReasons;
    }
    /* This code was generated by TableGo tools, mark 2 end. */

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
}