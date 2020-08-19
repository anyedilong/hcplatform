package com.java.moudle.system.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysCustomerDto {

    private String id;

    /** 手机号 */
    private String phone;

    /** 密码 */
    private String password;
    private String username;
    private String code;//手机验证码
    private String authorities;
    private String accessToken;
    private String custormerUrl;//头像地址
    private String orgName;
    private String again;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;
    private String clientIp;

    /** 绑定ID */
    private String jmid;
    private String sfzh;
    private String name;
    private String xb;

    /** 盐值（修改密码更新,用于加密） */
    private String salt;

    /** 授权码（登录修改） */
    private String authorizationKey;

    /** 安全码（修改密码更新） */
    private String securityKey;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerTime;

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String mailbox;

    /** 签名 */
    private String autograph;

    /** 状态  1 正常  2 冻结 3 注销 */
    private String status;

    /** 最后一次登录时间 */
    private Date lastLoginTime;

    /** 最后一个登录设备标识 */
    private String lastLoginDevice;

    /** 最后一次登陆IP */
    private String lastLoginIp;

    /** 消息推送ID */
    private String jpushId;

    /** 环信ID */
    private String easemobId;

    private String openId;//微信唯一标识

    public String getAgain() {
        return again;
    }

    public void setAgain(String again) {
        this.again = again;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }


    public String getCustormerUrl() {
		return custormerUrl;
	}

	public void setCustormerUrl(String custormerUrl) {
		this.custormerUrl = custormerUrl;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmid() {
        return jmid;
    }

    public void setJmid(String jmid) {
        this.jmid = jmid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginDevice() {
        return lastLoginDevice;
    }

    public void setLastLoginDevice(String lastLoginDevice) {
        this.lastLoginDevice = lastLoginDevice;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getEasemobId() {
        return easemobId;
    }

    public void setEasemobId(String easemobId) {
        this.easemobId = easemobId;
    }

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
