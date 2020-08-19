package com.java.moudle.physicalExamination.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.java.until.dba.BaseDomain;

/**
 * blt_tj_base_data
 * 
 * @author ljf
 * @version 1.0.0 2020-03-18
 */
@Entity
@Table(name = "blt_tj_base_data")
public class BltTjBaseData extends BaseDomain implements java.io.Serializable, Cloneable{
    /** 版本号 */
    private static final long serialVersionUID = 8129095330119394958L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** id */
    @Id
    private String id;

    /** 名称 */
    private String name;

    /** 上级id */
    private String parentId;

    /** 等级 1 2 */
    private String grade;

    /** 更新时间 */
    private Date updateTime;

    /** 对应数据库字段 */
    private String databaseField;
    
    /** 对应实体类属性 */
    private String attribute;
    
    @Transient
    private List<BltTjBaseData> nextData;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级id
     * 
     * @return 上级id
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 设置上级id
     * 
     * @param parentId
     *          上级id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取等级 1 2 3
     * 
     * @return 等级 1 2 3
     */
    public String getGrade() {
        return this.grade;
    }

    /**
     * 设置等级 1 2 3
     * 
     * @param grade
     *          等级 1 2 3
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * 
     * @param updateTime
     *          更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取对应数据库字段
     * 
     * @return 对应数据库字段
     */
    public String getDatabaseField() {
        return this.databaseField;
    }

    /**
     * 设置对应数据库字段
     * 
     * @param databaseField
     *          对应数据库字段
     */
    public void setDatabaseField(String databaseField) {
        this.databaseField = databaseField;
    }
    
    public List<BltTjBaseData> getNextData() {
		return nextData;
	}

	public void setNextData(List<BltTjBaseData> nextData) {
		this.nextData = nextData;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /* This code was generated by TableGo tools, mark 2 end. */
}