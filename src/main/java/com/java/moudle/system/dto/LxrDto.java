package com.java.moudle.system.dto;

import com.java.until.cache.CacheUntil;

import java.io.Serializable;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 16:28
 **/
public class LxrDto implements Serializable {

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
    private String lnrSfzh;

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

    public String getGxOther() {
        return gxOther;
    }

    public void setGxOther(String gxOther) {
        this.gxOther = gxOther;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
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

    public String getLnrSfzh() {
        return lnrSfzh;
    }

    public void setLnrSfzh(String lnrSfzh) {
        this.lnrSfzh = lnrSfzh;
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
        this.gx = CacheUntil.getValue("brGx", gx);
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
        return "LxrDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gx='" + gx + '\'' +
                ", phone='" + phone + '\'' +
                ", lnrSfzh='" + lnrSfzh + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", custormerUrl='" + custormerUrl + '\'' +
                ", zmUrl='" + zmUrl + '\'' +
                ", fmUrl='" + fmUrl + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", isChild='" + isChild + '\'' +
                '}';
    }
}
