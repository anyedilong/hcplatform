package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

public class HospitalJcDto implements Serializable {

	private static final long serialVersionUID = 156465446L;
	
	@Id
	private String id;// 内码
	private String arId;// 入院登记内码
	private String itemType;// 测量项目类型
	private String itemCode;// 测量项目代码
	private String itemName;// 测量项目名称
	private String itemValue;// 测量项目值
	@JSONField(format = "yyyy-MM-dd")
	private Date diagnosisTime;// 测量项目时间
	private String itemUnit;// 测量项目代码
	private String minValue;// 最小范围值
	private String maxValue;// 最大范围值
	private String hisId;

	public String getHisId() {
		return hisId;
	}

	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Date getDiagnosisTime() {
		return diagnosisTime;
	}

	public void setDiagnosisTime(Date diagnosisTime) {
		this.diagnosisTime = diagnosisTime;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
}
