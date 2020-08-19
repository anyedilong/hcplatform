package com.java.moudle.system.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * 联系人表(sys_lnr)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
@Entity
@Table(name = "sys_lnr")
public class SysLnr extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -448721153294578687L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标识 */
    @Id
    private String id;

    /** 联系人姓名 */
    private String name;

    /** 联系人手机号 */
    private String phone;

    /** 联系人身份证号 */
    private String sfzh;

    /** 监护人姓名*/
    private String guardianName;

    /** 是否儿童 1儿童 2成人*/
    private String isChild;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

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
     * 获取联系人姓名
     * 
     * @return 联系人姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置联系人姓名
     * 
     * @param name
     *          联系人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系人手机号
     * 
     * @return 联系人手机号
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置联系人手机号
     * 
     * @param phone
     *          联系人手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系人身份证号
     * 
     * @return 联系人身份证号
     */
    public String getSfzh() {
        return this.sfzh;
    }

    /**
     * 设置联系人身份证号
     * 
     * @param sfzh
     *          联系人身份证号
     */
    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    /* This code was generated by TableGo tools, mark 2 end. */
}