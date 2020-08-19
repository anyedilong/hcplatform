package com.java.moudle.system.dto;

import java.io.Serializable;

import com.java.moudle.system.domain.SysRole;
import com.java.until.DictUtil;

public class LoginInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;//主键
	private String username;
	private String password;
	private String phone;
	private String name;
	private String sfzh;
	private String custormerUrl;
	private String gx;
	private String authorities;
	private String orgName;
	/** 银行卡类型 字典表 */
    private Integer bankCardType;
    private String bankCardTypeName;
    /** 银行卡号 */
    private String bankCardNumber;
    private String isRealName;//0.未实名认证 1.身份证认证 2.银行卡认证
    
    
	private SysRole role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getCustormerUrl() {
		return custormerUrl;
	}
	public void setCustormerUrl(String custormerUrl) {
		this.custormerUrl = custormerUrl;
	}
	public String getGx() {
		return gx;
	}
	public void setGx(String gx) {
		this.gx = gx;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public Integer getBankCardType() {
		return bankCardType;
	}
	public void setBankCardType(Integer bankCardType) {
		this.bankCardTypeName = DictUtil.getDictValue("BankCardType", bankCardType+"");
		this.bankCardType = bankCardType;
	}
	public String getBankCardTypeName() {
		return bankCardTypeName;
	}
	public void setBankCardTypeName(String bankCardTypeName) {
		this.bankCardTypeName = bankCardTypeName;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	
}
