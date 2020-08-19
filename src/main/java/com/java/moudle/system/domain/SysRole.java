package com.java.moudle.system.domain;
import com.java.until.dba.BaseDomain;

import javax.persistence.*;

/**
 * 角色表(SYS_ROLE)
 * 
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -7029644935479556599L;

    /** 唯一标识 */
    @Id
    @Column(name = "ID")
    private String id;

    /** 角色CODE */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /** 角色名称 */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /** 状态(0.正常 1.删除) */
    @Column(name = "STATUS")
    private String status;

    /** 角色类型  1.超级管理员 2.管理员 3.普通用户 */
    @Column(name = "ROLE_TYPE")
    private String roleType;

    /** 角色id */
    @Column(name = "USER_ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取角色CODE
     * 
     * @return 角色CODE
     */
    public String getRoleCode() {
        return this.roleCode;
    }

    /**
     * 设置角色CODE
     * 
     * @param roleCode
     *          角色CODE
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     * 
     * @return 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置角色名称
     * 
     * @param roleName
     *          角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取状态(0.正常 1.删除)
     * 
     * @return 状态(0
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态(0.正常 1.删除)
     * 
     * @param status
     *          状态(0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     * 
     * @return 角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     */
    public String getRoleType() {
        return this.roleType;
    }

    /**
     * 设置角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     * 
     * @param roleType
     *          角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
