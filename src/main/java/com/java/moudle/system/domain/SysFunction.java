package com.java.moudle.system.domain;

import com.java.until.dba.BaseDomain;

import javax.persistence.*;
import java.util.List;


/**
 * 功能菜单(SYS_FUNCTION)
 * 
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "SYS_FUNCTION")
public class SysFunction extends BaseDomain {
    
    /** 版本号 */
    private static final long serialVersionUID = 4491268366867910154L;

    /** 唯一标示 */
    @Id
    @Column(name = "ID")
    private String id;

    /** 菜单名称 */
    @Column(name = "NAME")
    private String name;

    /** 上级ID */
    @Column(name = "PARENT_ID")
    private String parentId;

    /** 序号 */
    @Column(name = "ORDER_NUM")
    private String orderNum;

    /** 状态 */
    @Column(name = "STATUS")
    private String status;

    /** 类型(1.菜单 2.按钮) */
    @Column(name = "TYPE")
    private String type;

    /** 路径 */
    @Column(name = "URL")
    private String url;

    /** 图标 */
    @Column(name = "ICON")
    private String icon;

    /** 权限值 */
    @Transient
    private int authority;

    @Transient
    private List<SysFunction> sysFunctionList;

    public List<SysFunction> getSysFunctionList() {
        return sysFunctionList;
    }

    public void setSysFunctionList(List<SysFunction> sysFunctionList) {
        this.sysFunctionList = sysFunctionList;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

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
     * 获取菜单名称
     * 
     * @return 菜单名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置菜单名称
     * 
     * @param name
     *          菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级ID
     * 
     * @return 上级ID
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 设置上级ID
     * 
     * @param parentId
     *          上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取序号
     * 
     * @return 序号
     */
    public String getOrderNum() {
        return this.orderNum;
    }

    /**
     * 设置序号
     * 
     * @param orderNum
     *          序号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
     * 获取类型(1.菜单 2.按钮)
     * 
     * @return 类型(1
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置类型(1.菜单 2.按钮)
     * 
     * @param type
     *          类型(1
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取路径
     * 
     * @return 路径
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置路径
     * 
     * @param url
     *          路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取图标
     * 
     * @return 图标
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * 设置图标
     * 
     * @param icon
     *          图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}