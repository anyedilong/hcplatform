package com.java.moudle.system.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-18 13:42
 **/
public class SysCustormerDto implements Serializable {
    private static final long serialVersionUID = 5L;

    private String id;
    private String phone;
    private String status;
    private int pageNo;
    private int pageSize;

    /** 开始创建时间*/
    private Date startCreationTime;

    /** 结束创建时间*/
    private Date endCreationTime;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
