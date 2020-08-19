package com.java.moudle.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 09:18
 **/
public class OrganizationUserDto implements Serializable {
    private static final long serialVersionUID = 4491268366867910151L;

    private String id;

    /** 手机号 */
    private String phone;

    /** 开始创建时间*/
    private Date startCreationTime;

    /** 结束创建时间*/
    private Date endCreationTime;

    /** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date CreationTime;;

    /** 0启用 1禁止标识*/
    private String status;

    /** 姓名*/
    private String name;

    /** 权限*/
    private String role;

    /** 管理*/
    private String manage;

    /** 机构名称*/
    private String orgName;

    /** 用户名称*/
    private String userName;

    /** 用户名称*/
    private String roleName;

    /** 用户id*/
    private String userId;

    /** 分页*/
    private int pageNo;
    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartCreationTime() {
        return startCreationTime;
    }

    public void setStartCreationTime(Date startCreationTime) {
        this.startCreationTime = startCreationTime;
    }

    public Date getEndCreationTime() {
        return endCreationTime;
    }

    public void setEndCreationTime(Date endCreationTime) {
        this.endCreationTime = endCreationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }
}
