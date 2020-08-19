package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class HospitalOutMingXiDto implements Serializable {

	private static final long serialVersionUID = 65456496496L;
	
    private  String id;
    private  String itemCode;
    private  String  drugClassCode;
    private  String drugCode;
    private  String drugName;
    private  String formName;
    private  String spec;
    private  String unit;
    private  String price;
    private  String quantity;
    private  String issueMark;
    private  String manufacturerName;
    @JSONField(format="yyyy-MM-dd")
    private  Date issueDate;
    private  String receivableAmount;
    private  String ownAmount;
    private  String hisId;
    private  String outId;
    private String itemType;//处方项类别01:西药 02:中成药 03:中草药 04:麻醉、一类精神药品 05:检查 06:化验 07:放射 08:治疗 09:手术  99:其他
    private String drugType;//等级分类 1.普通药品  2.贵重药品  3.麻醉药品 4.精神一类 5.毒性药品 6.特殊管理药 7.剧毒药品
    private String drugHeds;//基药分类：1.国家基药 2.国家常药 3.省挂网 4.非基药


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDrugClassCode() {
        return drugClassCode;
    }

    public void setDrugClassCode(String drugClassCode) {
        this.drugClassCode = drugClassCode;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIssueMark() {
        return issueMark;
    }

    public void setIssueMark(String issueMark) {
        this.issueMark = issueMark;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(String receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public String getOwnAmount() {
        return ownAmount;
    }

    public void setOwnAmount(String ownAmount) {
        this.ownAmount = ownAmount;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDrugHeds() {
		return drugHeds;
	}

	public void setDrugHeds(String drugHeds) {
		this.drugHeds = drugHeds;
	}
}
