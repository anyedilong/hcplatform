package com.java.moudle.system.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 11:50
 **/
public class AddLxrDto implements Serializable {
    private static final long serialVersionUID = 2L;

    /** 联系人姓名*/
    private String name;

    /** 身份证号*/
    private String sfzh;

    /** 联系人身份证号*/
    private String lxrSfzh;

    /** 手机号*/
    private String phone;

    /** 验证码*/
    private String code;

    /** 正面url*/
    private String zmUrl;

    /** 反面url*/
    private String fmUrl;

    /** 与客户关系*/
    private String gx;

    /** 关系其他*/
    private String gxOther;

    /** 正面身份证*/
    private MultipartFile zmImage;

    /** 反面身份证*/
    private MultipartFile fmImage;

    /** 监护人姓名*/
    private String guardianName;

    /** 是否儿童 1儿童 2成人*/
    private String isChild;

    /** 客户id */
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

    public MultipartFile getZmImage() {
        return zmImage;
    }

    public void setZmImage(MultipartFile zmImage) {
        this.zmImage = zmImage;
    }

    public MultipartFile getFmImage() {
        return fmImage;
    }

    public void setFmImage(MultipartFile fmImage) {
        this.fmImage = fmImage;
    }

    public String getLxrSfzh() {
        return lxrSfzh;
    }

    public void setLxrSfzh(String lxrSfzh) {
        this.lxrSfzh = lxrSfzh;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    @Override
    public String toString() {
        return "AddLxrDto{" +
                "name='" + name + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", lxrSfzh='" + lxrSfzh + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", zmUrl='" + zmUrl + '\'' +
                ", fmUrl='" + fmUrl + '\'' +
                ", gx='" + gx + '\'' +
                ", zmImage=" + zmImage +
                ", fmImage=" + fmImage +
                ", guardianName='" + guardianName + '\'' +
                ", isChild='" + isChild + '\'' +
                '}';
    }
}
