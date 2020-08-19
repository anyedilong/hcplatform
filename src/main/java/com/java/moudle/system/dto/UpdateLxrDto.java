package com.java.moudle.system.dto;

import java.io.Serializable;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-31 16:07
 **/
public class UpdateLxrDto implements Serializable {

    private static final long serialVersionUID = 3L;

    /** 联系人id*/
    private String id;

    /** 联系人姓名*/
    private String name;

    /** 与客户关系*/
    private String gx;

    /** 关系其他*/
    private String gxOther;

    /** 联系人手机号*/
    private String phone;

    /** 联系人身份证号*/
    private String lxrSfzh;

    /** 本人身份证号*/
    private String sfzh;

    /** 本人头像*/
    private String custormerUrl;

    /** 正面url*/
    private String zmUrl;

    /** 反面url*/
    private String fmUrl;

    /** 监护人姓名*/
    private String guardianName;

    /** 是否儿童 1儿童 2成人*/
    private String isChild;

    /** 客户id*/
    private String custormerId;

    public String getGxOther() {
        return gxOther;
    }

    public void setGxOther(String gxOther) {
        this.gxOther = gxOther;
    }

    public String getCustormerId() {
        return custormerId;
    }

    public void setCustormerId(String custormerId) {
        this.custormerId = custormerId;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getZmUrl() {
        return zmUrl;
    }

    public void setZmUrl(String zmUrl) {
        this.zmUrl = zmUrl;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    public String getLxrSfzh() {
        return lxrSfzh;
    }

    public void setLxrSfzh(String lxrSfzh) {
        this.lxrSfzh = lxrSfzh;
    }

    public String getCustormerUrl() {
        return custormerUrl;
    }

    public void setCustormerUrl(String custormerUrl) {
        this.custormerUrl = custormerUrl;
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

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    @Override
    public String toString() {
        return "UpdateLxrDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gx='" + gx + '\'' +
                ", phone='" + phone + '\'' +
                ", lxrSfzh='" + lxrSfzh + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", custormerUrl='" + custormerUrl + '\'' +
                ", zmUrl='" + zmUrl + '\'' +
                ", fmUrl='" + fmUrl + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", isChild='" + isChild + '\'' +
                '}';
    }
}
