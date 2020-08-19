package com.java.moudle.system.dto;

import com.java.moudle.system.domain.SysFunction;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 16:03
 **/
public class MeunAnniuDto implements Serializable {
    private static final long serialVersionUID = 4491268366867910119L;

    /** 菜单名称*/
    private String name;

    /** 菜单id*/
    private String id;

    private String parentId;

    private String orderNum;

    private String status;

    private String type;

    private String url;

    private String icon;

    private int authority;

    /** 按钮*/
    private List<SysFunction> sysFunctionList;

    /** 角色*/
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public List<SysFunction> getSysFunctionList() {
        return sysFunctionList;
    }

    public void setSysFunctionList(List<SysFunction> sysFunctionList) {
        this.sysFunctionList = sysFunctionList;
    }
}
