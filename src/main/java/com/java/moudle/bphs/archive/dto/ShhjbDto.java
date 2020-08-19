package com.java.moudle.bphs.archive.dto;

import java.util.Date;

public class ShhjbDto {


	private String id;//
	private String dabh;// 档案编号
	private String sfzh;// 身份证号
	private String cfpfcs;// 厨房排风措施
	private String rllx;// 燃料类型
	private String yslx;// 饮水类型
	private String cslx;// 厕所类型
	private String qxl;// 禽畜栏
	private String importDeviceNum;//导入设备编号
	//@JSONField(format="yyyy-MM-dd")
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date  importTime;//导入时间
	private String importUser;//导入人
	private String importIp;//导入IP
	private String jmid; //居民id（关联字段）


	public String getJmid() {
		return jmid;
	}

	public void setJmid(String jmid) {
		this.jmid = jmid;
	}

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
//		String cfpfcs = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getKitchenExhaustType(), this.cfpfcs);
	    if (cfpfcs != null) {
	    	return cfpfcs;
	    } else {
	    	return this.cfpfcs;
	    }
	}

	public void setCfpfcs(String cfpfcs) {
		this.cfpfcs = cfpfcs;
	}

	public String getRllx() {
//    	String rllx = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getFuelType(), this.rllx);
        if (rllx != null) {
        	return rllx;
        } else {
        	return this.rllx;
        }
	}

	public void setRllx(String rllx) {
		this.rllx = rllx;
	}

	public String getYslx() {
//    	String yslx = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getWaterType(), this.yslx);
        if (yslx != null) {
        	return yslx;
        } else {
        	return this.yslx;
        }
	}

	public void setYslx(String yslx) {
		this.yslx = yslx;
	}

	public String getCslx() {
//    	String cslx = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getWCType(), this.cslx);
        if (cslx != null) {
        	return cslx;
        } else {
        	return this.yslx;
        }
	}

	public void setCslx(String cslx) {
		this.cslx = cslx;
	}

	public String getQxl() {
//    	String qxl = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getLivestockBarType(), this.qxl);
        if (qxl != null) {
        	return qxl;
        } else {
        	return this.qxl;
        }
	}

	public void setQxl(String qxl) {
		this.qxl = qxl;
	}


}
