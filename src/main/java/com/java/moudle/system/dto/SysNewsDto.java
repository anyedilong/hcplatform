package com.java.moudle.system.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 系统消息表(sys_news)
 * 
 * @author tz
 * @version 1.0.0 2020-03-03
 */
public class SysNewsDto implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4090762220699734217L;


    private String xh;//序号
    private String id;
    /** 消息标题 */
    private String title;
    /** 消息简介 */
    private String synopsis;
    /** 消息内容 */
    private String content;
    /** 创建时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    /** 创建人 */
    private String createUser;
    private String name;//创建人姓名
    /** 状态（1.未发布 2.撤回 3.发布 4.置顶 ） */
    private String status;
    /** 发布时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date pubTime;
    private String orgName;
    
    private String startDate;
    private String endDate;
    private String type;//1.网站端 2.后台管理
    
    private String roleType;//1.超级管理员 2.管理员 3.普通用户
    private List<String> userIds;//用户标识集合
    
    
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
    
}