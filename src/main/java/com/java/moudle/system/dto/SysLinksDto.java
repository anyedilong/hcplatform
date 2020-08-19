package com.java.moudle.system.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-18 15:55
 **/
public class SysLinksDto implements Serializable {
    private static final long serialVersionUID = 6L;

    /** 链接名称*/
    private String name;

    /** 开始创建时间*/
    private Date startCreationTime;

    /** 结束创建时间*/
    private Date endCreationTime;

    private int pageNo;
    private int pageSize;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}



