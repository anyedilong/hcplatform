package com.java.moudle.system.domain;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * 客户表(sys_custormer)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_custormer")
public class SysCustormer extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -1954936998729684011L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标识 */
    @Id
    private String id;

    /** 身份证号 */
    private String sfzh;

    /** 姓名 */
    private String name;

    /** 电话 */
    private String phone;

    /** 密码 */
    private String password;

    /** 头像URL */
    private String custormerUrl;

    /** 状态（0.正常 1.禁用） */
    private String status;

    /** 实名制类型 0未实名 1 身份证实名认证 2银行卡实名认证 */
    private Integer isRealName;

    /** 银行卡类型 字典表 */
    private Integer bankCardType;

    /** 银行卡号 */
    private String bankCardNumber;

    /** 创建时间，实名认证时间 */
    private Date createTime;
    
    private String pwd;//密码明文
    private String username;//用户名
    private String openId;//微信账号唯一标识
    
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

    /**
     * 获取身份证号
     * 
     * @return 身份证号
     */
    public String getSfzh() {
        return this.sfzh;
    }

    /**
     * 设置身份证号
     * 
     * @param sfzh
     *          身份证号
     */
    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    /**
     * 获取姓名
     * 
     * @return 姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置姓名
     * 
     * @param name
     *          姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取电话
     * 
     * @return 电话
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置电话
     * 
     * @param phone
     *          电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像URL
     * 
     * @return 头像URL
     */
    public String getCustormerUrl() {
        return this.custormerUrl;
    }

    /**
     * 设置头像URL
     * 
     * @param custormerUrl
     *          头像URL
     */
    public void setCustormerUrl(String custormerUrl) {
        this.custormerUrl = custormerUrl;
    }

    /**
     * 获取状态（0.正常 1.禁用）
     * 
     * @return 状态（0
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态（0.正常 1.禁用）
     * 
     * @param status
     *          状态（0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取实名制类型 0未实名 1 身份证实名认证 2银行卡实名认证
     * 
     * @return 实名制类型 0未实名 1 身份证实名认证 2银行卡实名认证
     */
    public Integer getIsRealName() {
        return this.isRealName;
    }

    /**
     * 设置实名制类型 0未实名 1 身份证实名认证 2银行卡实名认证
     * 
     * @param isRealName
     *          实名制类型 0未实名 1 身份证实名认证 2银行卡实名认证
     */
    public void setIsRealName(Integer isRealName) {
        this.isRealName = isRealName;
    }

    /**
     * 获取银行卡类型 关联银行卡类型表
     * 
     * @return 银行卡类型 关联银行卡类型表
     */
    public Integer getBankCardType() {
        return this.bankCardType;
    }

    /**
     * 设置银行卡类型 关联银行卡类型表
     * 
     * @param bankCardType
     *          银行卡类型 关联银行卡类型表
     */
    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    /**
     * 获取银行卡号
     * 
     * @return 银行卡号
     */
    public String getBankCardNumber() {
        return this.bankCardNumber;
    }

    /**
     * 设置银行卡号
     * 
     * @param bankCardNumber
     *          银行卡号
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    /**
     * 获取创建时间，实名认证时间
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间，实名认证时间
     * 
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}