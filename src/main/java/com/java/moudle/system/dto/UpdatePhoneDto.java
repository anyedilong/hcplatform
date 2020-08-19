package com.java.moudle.system.dto;

import java.io.Serializable;

/**
 * @Description 修改手机号实体
 * @author ZhangWei
 * @Date: 2020-03-10 09:20
 **/
public class UpdatePhoneDto implements Serializable {
    private static final long serialVersionUID = 4L;

    /** 客户表id*/
    private String id;
    /** 旧手机号*/
    private String oldPhone;
    /** 新手机号*/
    private String newPhone;
    /** 旧手机验证码*/
    private String oldCode;
    /** 新手机验证码*/
    private String newCode;

    public String getOldPhone() {
        return oldPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }
}
