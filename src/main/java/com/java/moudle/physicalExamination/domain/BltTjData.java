package com.java.moudle.physicalExamination.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.java.until.dba.BaseDomain;

/**
 * 体检关键数据关联表(blt_tj_data)
 * 
 * @author ljf
 * @version 1.0.0 2020-03-18
 */
@Entity
@Table(name = "blt_tj_data")
public class BltTjData extends BaseDomain implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3549824444555961913L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** id */
    @Id
    private String id;

    /** BLT_TJ_BASE_DATA表的id */
    private String basedataId;

    /** BLT_TJ_INFO表的id */
    private String tjInfoId;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = true)
    @JoinColumn(name = "basedataId", referencedColumnName = "id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private BltTjBaseData baseData;

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
     * 获取BLT_TJ_BASE_DATA表的id
     * 
     * @return BLT_TJ_BASE_DATA表的id
     */
    public String getBasedataId() {
        return this.basedataId;
    }

    /**
     * 设置BLT_TJ_BASE_DATA表的id
     * 
     * @param basedataId
     *          BLT_TJ_BASE_DATA表的id
     */
    public void setBasedataId(String basedataId) {
        this.basedataId = basedataId;
    }

    /**
     * 获取BLT_TJ_INFO表的id
     * 
     * @return BLT_TJ_INFO表的id
     */
    public String getTjInfoId() {
        return this.tjInfoId;
    }

    /**
     * 设置BLT_TJ_INFO表的id
     * 
     * @param tjInfoId
     *          BLT_TJ_INFO表的id
     */
    public void setTjInfoId(String tjInfoId) {
        this.tjInfoId = tjInfoId;
    }

	public BltTjBaseData getBaseData() {
		return baseData;
	}

	public void setBaseData(BltTjBaseData baseData) {
		this.baseData = baseData;
	}
}