package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class HospitalOutCostDto implements Serializable {

	private static final long serialVersionUID = 864646466L;
	
    private  String id;
    private  String hisId;
    private  String orgCode;
    private  String orgName;
    private  String arId;
    private  String receiptNum;
    private  String name;
    @JSONField(format="yyyy-MM-dd")
    private  Date settleDate;
    private  String chargeType;
    private  String insuranceNum;
    private  String totalAmount;
    private String advanceAmount;
    private  String ownAmount;
    @JSONField(format="yyyy-MM-dd")
    private  Date dischargeDate;
    private  String billStatus;
    private  String doctorCode;
    private  String doctorName;
    private  String depCode;
    private  String depName;
    private  String payType;
    private  String sfzh;

    private List<HospitalOutZdDto> diagnosisList;
    private List<HospitalOutMingXiDto> hospitalOutMingXiList;


    public List<HospitalOutZdDto> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<HospitalOutZdDto> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}


	public List<HospitalOutMingXiDto> getHospitalOutMingXiList() {
		return hospitalOutMingXiList;
	}


	public void setHospitalOutMingXiList(List<HospitalOutMingXiDto> hospitalOutMingXiList) {
		this.hospitalOutMingXiList = hospitalOutMingXiList;
	}


	public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOwnAmount() {
        return ownAmount;
    }

    public void setOwnAmount(String ownAmount) {
        this.ownAmount = ownAmount;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

	public String getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
}
