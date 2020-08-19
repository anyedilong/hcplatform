package com.java.moudle.tripartdock.region.archive.dto;

import javax.persistence.Id;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @ClassName Shhjb
 * @Description 档案-生活环境
 * @author sen
 * @Date 2017年2月7日 上午10:43:33
 * @version 1.0.0
 */
public class ShhjbDto implements Serializable {

	private static final long serialVersionUID = 646416L;
	
	@Id
	private String id;//
	private String dabh;// 档案编号
	private String sfzh;// 身份证号
	private String cfpfcs;// 厨房排风措施
	private String rllx;// 燃料类型
	private String yslx;// 饮水类型
	private String cslx;// 厕所类型
	private String qxl;// 禽畜栏
	private String importDeviceNum;//导入设备编号
	@JSONField(format="yyyy-MM-dd")
	private Date  importTime;//导入时间
	private String importUser;//导入人
	private String importIp;//导入IP
	private String jmid; //居民id
	
	@Transient
	private String cfpfcsText;
	@Transient
	private String rllxText;
	@Transient
	private String yslxText;
	@Transient
	private String cslxText;
	@Transient
	private String qxlText;
	
	public String getImportDeviceNum() {
		return importDeviceNum;
	}

	public void setImportDeviceNum(String importDeviceNum) {
		this.importDeviceNum = importDeviceNum;
	}
	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public String getImportUser() {
		return importUser;
	}

	public void setImportUser(String importUser) {
		this.importUser = importUser;
	}

	public String getImportIp() {
		return importIp;
	}

	public void setImportIp(String importIp) {
		this.importIp = importIp;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getCfpfcs() {
		return this.cfpfcs;
	}

	public void setCfpfcs(String cfpfcs) {
		this.cfpfcs = cfpfcs;
	}

	public String getRllx() {
		return this.rllx;
	}

	public void setRllx(String rllx) {
		this.rllx = rllx;
	}

	public String getYslx() {
		return this.yslx;
	}

	public void setYslx(String yslx) {
		this.yslx = yslx;
	}

	public String getCslx() {
		return this.cslx;
	}

	public void setCslx(String cslx) {
		this.cslx = cslx;
	}

	public String getQxl() {
		return this.qxl;
	}

	public void setQxl(String qxl) {
		this.qxl = qxl;
	}

	public String getJmid() {
		return jmid;
	}

	public void setJmid(String jmid) {
		this.jmid = jmid;
	}

	public String getCfpfcsText() {
		return cfpfcsText;
	}

	public void setCfpfcsText(String cfpfcsText) {
		this.cfpfcsText = cfpfcsText;
	}

	public String getRllxText() {
		return rllxText;
	}

	public void setRllxText(String rllxText) {
		this.rllxText = rllxText;
	}

	public String getYslxText() {
		return yslxText;
	}

	public void setYslxText(String yslxText) {
		this.yslxText = yslxText;
	}

	public String getCslxText() {
		return cslxText;
	}

	public void setCslxText(String cslxText) {
		this.cslxText = cslxText;
	}

	public String getQxlText() {
		return qxlText;
	}

	public void setQxlText(String qxlText) {
		this.qxlText = qxlText;
	}

}
