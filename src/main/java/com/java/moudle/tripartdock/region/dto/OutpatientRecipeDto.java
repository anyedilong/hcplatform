package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;


public class OutpatientRecipeDto implements Serializable {

	private static final long serialVersionUID = 1234268545345L;
	
    private String  id;
    private String  hisId;
    private String orgCode;//医院组织机构代码
    private String orgName;//医院名称
    private String outpatientNum;//本次门诊标识号
    private String recipeNum;//处方编号
    private String recipeType;//处方类别（1大病处方2普通处方）
    private String name;//姓名
    @JSONField(format="yyyy-MM-dd")
    private Date prescribeDate;//开立日期
    private String depCode;//科室代码
    private String depName;//科室名称
    private String doctorCode;//医生代码
    private String doctorName;//医生名称
    private String sfzh;//身份证号
	private String medicalRecordId;// 门诊病历id

    private List<OutpatientRxMingxiDto>  recipelist;
    private List<OutpatientRxZdDto> diagnosislist;

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

    public String getOutpatientNum() {
        return outpatientNum;
    }

    public void setOutpatientNum(String outpatientNum) {
        this.outpatientNum = outpatientNum;
    }

    public String getRecipeNum() {
        return recipeNum;
    }

    public void setRecipeNum(String recipeNum) {
        this.recipeNum = recipeNum;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPrescribeDate() {
        return prescribeDate;
    }

    public void setPrescribeDate(Date prescribeDate) {
        this.prescribeDate = prescribeDate;
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

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public List<OutpatientRxMingxiDto> getRecipelist() {
		return recipelist;
	}

	public void setRecipelist(List<OutpatientRxMingxiDto> recipelist) {
		this.recipelist = recipelist;
	}

	public List<OutpatientRxZdDto> getDiagnosislist() {
		return diagnosislist;
	}

	public void setDiagnosislist(List<OutpatientRxZdDto> diagnosislist) {
		this.diagnosislist = diagnosislist;
	}
}
