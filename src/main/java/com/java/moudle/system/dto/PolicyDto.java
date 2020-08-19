package com.java.moudle.system.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.validate.Validate;

/**
 * 政策法规
 * nyw
 */
public class PolicyDto {
    private String id;
    @Validate(message = "标题[policyTitle]不能为空！", required = true)
    private String policyTitle;//标题
    @Validate(message = "类型[type]不能为空！", required = true)
    private String type;//类型 1：公卫 2：家医
    @Validate(message = "来源[policyLy]不能为空！", required = true)
    private String policyLy;//来源 1：原创 2：转载
    private String reprintedFrom;//来源备注
    @Validate(message = "内容[policyContent]不能为空！", required = true)
    private String policyContent;//内容
    private String policyUrl;//图片地址
    private String status;//状态 (0.未提交 1.提交-审核中 2.审核通过 3.发表 4.置顶 5.退回 6.撤回)
    private String returnReasons;//退回原因
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;//创建时间
    private String createUser;//创建人
    private Date topTime;//置顶时间
    private String topUser;//置顶人
    private Date pubTime;//发表时间
    private String deleteFlg;//是否删除 （0.正常 1.删除）
    private MultipartFile imgFile;
    private Date startDate;
    private Date endDate;
    private Integer pageNo;
    private Integer pageSize;
    
    private String roleType;//1.超级管理员 2.管理员 3.普通用户
    private List<String> userIds;//用户标识集合

    public PolicyDto() {
    }

    public PolicyDto(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPolicyLy() {
        return policyLy;
    }

    public void setPolicyLy(String policyLy) {
        this.policyLy = policyLy;
    }

    public String getReprintedFrom() {
        return reprintedFrom;
    }

    public void setReprintedFrom(String reprintedFrom) {
        this.reprintedFrom = reprintedFrom;
    }

    public String getPolicyContent() {
        return policyContent;
    }

    public void setPolicyContent(String policyContent) {
        this.policyContent = policyContent;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnReasons() {
        return returnReasons;
    }

    public void setReturnReasons(String returnReasons) {
        this.returnReasons = returnReasons;
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

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }

    public String getTopUser() {
        return topUser;
    }

    public void setTopUser(String topUser) {
        this.topUser = topUser;
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
