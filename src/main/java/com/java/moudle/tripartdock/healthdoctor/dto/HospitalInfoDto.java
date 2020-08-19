package com.java.moudle.tripartdock.healthdoctor.dto;

import java.io.Serializable;


public class HospitalInfoDto implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -49481198728589055L;

    /** 主键 */
    private String id;
    private String name;
    private String areaCode;//区域code
    private String type; //医院类型
    private String levelType;//医院等级
    private String recommend;//是否推荐 1.推荐 2.不推荐

    private String deptCodes;//多个科室字符串
    private String startDate;//开始日期
    private String endDate;//截止日期
    private String levelTypeText;//等级说明
    private String typeText;//类型说明
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getDeptCodes() {
		return deptCodes;
	}
	public void setDeptCodes(String deptCodes) {
		this.deptCodes = deptCodes;
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
	public String getLevelTypeText() {
		return levelTypeText;
	}
	public void setLevelTypeText(String levelTypeText) {
		this.levelTypeText = levelTypeText;
	}
	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

}