package com.java.moudle.system.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.annotation.JSONField;


public class SysGuideDto {

    private String id;

    /** 办事指南类别，指南类别表的主键 */
    private String guideType;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 状态 (0.未提交 1.提交-审核中 2.审核通过 5.退回 6.撤回)  */
    private String status;

    /** 创建时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    
    /** 创建人 */
    private String createUser;
    
    /** 封面url */
    private String guideUrl;
    
    @JSONField(format="yyyy-MM-dd")
    private Date pubTime;
    
    private String deleteFlg;//删除标识（0：正常 1.删除）
    
    /**
     * 封面文件
     */
    private MultipartFile file;

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置主键
     * 
     * @param id
     *          主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取办事指南类别，指南类别表的主键
     * 
     * @return 办事指南类别
     */
    public String getGuideType() {
        return this.guideType;
    }

    /**
     * 设置办事指南类别，指南类别表的主键
     * 
     * @param guideType
     *          办事指南类别
     */
    public void setGuideType(String guideType) {
        this.guideType = guideType;
    }

    /**
     * 获取标题
     * 
     * @return 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     * 
     * @param title
     *          标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
     * 获取状态  1正常 2冻结 3删除
     * 
     * @return 状态  1正常 2冻结 3删除
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态  (0.未提交 1.提交-审核中 2.审核通过 3发表 5.退回 6.撤回) 
     * 
     * @param status
     *          (0.未提交 1.提交-审核中 2.审核通过 3发表 5.退回 6.撤回) 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getGuideUrl() {
		return guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}